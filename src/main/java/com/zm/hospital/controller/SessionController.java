package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.shiro.filter.ShiroFilterUtils;
import com.zm.hospital.model.User;
import com.zm.hospital.model.bo.SessionBo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 管理在线用户
 * Created by ange on 2016/11/20.
 */
@Controller
@RequestMapping("/session")
public class SessionController extends BaseController {

    private final AbstractSessionDAO sessionDAO;

    @Autowired
    public SessionController(AbstractSessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }


    @RequestMapping("/list")
    public String list(Integer page, Integer pagesize, Model model) {
        //PageInfo<Session> pageInfo = new PageInfo<Session>(page, pagesize);
        Subject subject = SecurityUtils.getSubject();

        Collection<Session> sessions = sessionDAO.getActiveSessions();
        int total = sessions.size();

        List<SessionBo> sessionBos = new ArrayList<SessionBo>();
        for (Session session : sessions) {
            //新建自定义sessionBo
            SessionBo sessionBo = new SessionBo();
            sessionBo.setId(session.getId());
            sessionBo.setStartTimestamp(session.getLastAccessTime());
            sessionBo.setLastAccessTime(session.getLastAccessTime());
            sessionBo.setTimeout(session.getTimeout());
            sessionBo.setHost(session.getHost());

            //获得登录名
            PrincipalCollection pc = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (pc != null) {
                User user = (User) pc.getPrimaryPrincipal();
                sessionBo.setLoginName(user.getLoginname());
            } else {
                sessionBo.setLoginName("");
            }

            //是否登录
            Boolean islogin = (Boolean) session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
            if (islogin != null) {
                sessionBo.setLogin(islogin);
            } else {
                sessionBo.setLogin(false);
            }

            String status = (String) session.getAttribute(ShiroFilterUtils.STATUS_SESSION_KEY);
            sessionBo.setStatus("在线");
            if (status != null) {
                if (status.equals(ShiroFilterUtils.STATUS_ABORT)) {
                    sessionBo.setStatus("登录踢出");
                }
                if (status.equals(ShiroFilterUtils.STATUS_KICK)) {
                    sessionBo.setStatus("管理员踢出");
                }
            }

            sessionBos.add(sessionBo);
        }

        model.addAttribute("curSession", subject.getSession());
        model.addAttribute("sessions", sessionBos);
        model.addAttribute("total", total);

        return "session/tables";
    }

    @RequestMapping(value = "/kick", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object kick(String sid) {

        Collection<Session> sessions = sessionDAO.getActiveSessions();
        List<SessionBo> sessionBos = new ArrayList<SessionBo>();

        boolean success = false;
        for (Session session : sessions) {
            if (sid.equals(session.getId())) {
                //设置踢出状态
                session.setAttribute(ShiroFilterUtils.STATUS_SESSION_KEY, ShiroFilterUtils.STATUS_KICK);
                success = true;
                break;
            }
        }

        //返回json
        if (success) {
            return renderSuccess("操作成功");
        }
        return renderError("操作失败");
    }
}
