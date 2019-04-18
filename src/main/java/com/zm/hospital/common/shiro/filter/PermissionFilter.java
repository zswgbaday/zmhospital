package com.zm.hospital.common.shiro.filter;

import com.zm.hospital.common.result.Result;
import com.zm.hospital.common.utils.JsonUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义权限过滤器
 */
public class PermissionFilter extends AccessControlFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(PermissionFilter.class);

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        Subject subject = getSubject(request, response);

        if (subject.hasRole("superAdmin")) {
            //超级管理员角色
            return Boolean.TRUE;
        }

        //先判断带参数的权限判断
        // mappedValue的值就是上面spring-shiro.xml 过滤器链定义中roles["user,admin"] 括号里面的值
        if (null != mappedValue) {
            String[] arra = (String[]) mappedValue;
            for (String permission : arra) {
                if (subject.isPermitted(permission)) {
                    return Boolean.TRUE;
                }
            }
        }

        HttpServletRequest httpRequest = ((HttpServletRequest) request);

        /*
         * 此处是改版后，为了兼容项目不需要部署到root下，也可以正常运行，但是权限没设置目前必须到root 的URI，
         * 原因：如果你把这个项目叫 ShiroDemo，那么路径就是 /ShiroDemo/xxxx.shtml ，那另外一个人使用，又叫Shiro_Demo,那么就要这么控制/Shiro_Demo/xxxx.shtml
         * 理解了吗？
         * 所以这里替换了一下，使用根目录开始的URI
         */

        //获取URI
        String uri = httpRequest.getRequestURI();

        //获取basePath
        String basePath = httpRequest.getContextPath();
        if (null != uri && uri.startsWith(basePath) && !basePath.equals("")) {
            uri = uri.replace(basePath, "");
        }
        if (subject.isPermitted(uri)) {
            return Boolean.TRUE;
        }
        if (ShiroFilterUtils.isAjax(request)) {//Ajax请求就返回json
            LOGGER.debug("当前用户没有权限访问，并且是Ajax请求！");

            Result result = new Result();
            result.setSuccess(false);
            result.setMsg("当前用户没有权限访问！");

            JsonUtils.out(response, result);
        }
        return Boolean.FALSE;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (null == subject.getPrincipal()) {//表示没有登录，重定向到登录页面
            saveRequest(request);
            WebUtils.issueRedirect(request, response, ShiroFilterUtils.LOGIN_URL);
        } else {
            if (StringUtils.hasText(ShiroFilterUtils.UNAUTHORIZED)) {//如果有未授权页面跳转过去
                WebUtils.issueRedirect(request, response, ShiroFilterUtils.UNAUTHORIZED);
            } else {//否则返回401未授权状态码
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return Boolean.FALSE;
    }
}
