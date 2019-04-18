package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.result.Result;
import com.zm.hospital.common.shiro.filter.ShiroFilterUtils;
import com.zm.hospital.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 登录控制器
 * Created by Administrator on 2016-10-10.
 */
@Controller
public class LoginController extends BaseController {

    private AbstractSessionDAO sessionDAO;

    @Autowired
    public LoginController(AbstractSessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    /**
     * 登录页面
     *
     * @return {String}
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String error, Model model) {
        logger.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }

        String msg = "";
        if (error != null) {
            if (error.equals(ShiroFilterUtils.STATUS_ABORT)) {
                msg = "您的帐号在另一个地方登入";
            } else if (error.equals(ShiroFilterUtils.STATUS_KICK)) {
                msg = "您被管理员踢出";
            }
        }

        model.addAttribute("msg", msg);
        //登录页面
        return "login";
    }

    /**
     * 登录请求ajax
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object loginPost(String username, String password) {
        logger.info("POST请求登录");

        if (StringUtils.isBlank(username)) {
            return renderError("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return renderError("密码不能为空");
        }

//        String[] legalLoginType = {"1", "2"};//1是普通用户,2是管理员
//        if (!ArrayUtils.contains(legalLoginType, login_type)) {
//            return renderError("登录类型不合法");
//        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());//md5加密密码
        //后台用户不记住我,前台注册用户才记住我
//        token.setRememberMe(false);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            logger.error("账号不存在：{}", e);
            return renderError("账号不存在");
        } catch (DisabledAccountException e) {
            logger.error("账号未启用：{}", e);
            return renderError("账号未启用");
        } catch (IncorrectCredentialsException e) {
            logger.error("密码错误：{}", e);
            return renderError("密码错误");
        } catch (RuntimeException e) {
            logger.error("未知错误,请联系管理员：{}", e);
            return renderError("未知错误,请联系管理员");
        }

        Result result = new Result();
        result.setSuccess(true);
        result.setMsg("登录成功");

        //跳转地址
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = null;
        if (null != savedRequest) {
            url = savedRequest.getRequestUrl();
        }
        //如果登录之前没有地址，那么就跳转到首页。
        if (StringUtils.isBlank(url)) {
            url = request.getContextPath() + "/index";
        }


        //单点登录

        //设置session默认是在线状态
        Session curSession = subject.getSession();
        //curSession.setAttribute(ShiroFilterUtils.STATUS_SESSION_KEY, ShiroFilterUtils.STATUS_ONLINE);

        //检查相同帐号是否之前被登录
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {

            if (session.getId() == curSession.getId()) {
                //必须不是自己
                continue;
            }

            //是否登录
            Boolean islogin = (Boolean) session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);

            if (islogin != null && islogin) {

                //登录用户
                PrincipalCollection pc = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

                if (pc != null) {
                    User userx = (User) pc.getPrimaryPrincipal();
                    if (username.equals(userx.getLoginname())) {
                        session.setAttribute(ShiroFilterUtils.STATUS_SESSION_KEY, ShiroFilterUtils.STATUS_ABORT);
                        break;
                    }
                }

            }

        }


        //返回json
        result.setObj(url);
        return result;
    }

    /**
     * 提示没有权限页面
     *
     * @return {String}
     */
    @RequestMapping(value = "/unauth")
    public String unauth() {
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/login";//没有登录的跳转到登录页面
        }
        //提示没有权限
        return "unauth";
    }

    /**
     * 退出
     *
     * @return {Result}
     */
    @RequestMapping(value = "/logout", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object logout() {
        logger.info("登出");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        Result result = new Result();
        result.setSuccess(true);
        result.setMsg("登出成功");
        result.setObj(getContextPath() + "/index");//链接到首页
        return result;
    }
    
    
    
    public static void main (String[] args) {
    	System.out.println(DigestUtils.md5Hex("test").toCharArray());
    }
}
