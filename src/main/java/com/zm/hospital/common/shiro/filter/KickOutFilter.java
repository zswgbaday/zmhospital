package com.zm.hospital.common.shiro.filter;

import com.zm.hospital.common.result.Result;
import com.zm.hospital.common.utils.JsonUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 踢出过滤器
 * Created by ange on 2016/11/23.
 */
public class KickOutFilter extends AccessControlFilter {


    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();

        String status = (String) session.getAttribute(ShiroFilterUtils.STATUS_SESSION_KEY);
        if (status != null && subject.getPrincipal() != null) {
            if (status.equals(ShiroFilterUtils.STATUS_ABORT)) {
                if (ShiroFilterUtils.isAjax(request)) {
                    //返回ajax
                    Result result = new Result();
                    result.setSuccess(false);
                    result.setMsg("您的帐号在另一个地方登入");
                    JsonUtils.out(response, result);
                } else {
                    WebUtils.issueRedirect(request, response, getLoginUrl() + "?error=" + ShiroFilterUtils.STATUS_ABORT);
                }
                subject.logout();

                return false;
            }
            if (status.equals(ShiroFilterUtils.STATUS_KICK)) {
                if (ShiroFilterUtils.isAjax(request)) {
                    //返回ajax
                    Result result = new Result();
                    result.setSuccess(false);
                    result.setMsg("您被管理员踢出");
                    JsonUtils.out(response, result);
                } else {
                    WebUtils.issueRedirect(request, response, getLoginUrl() + "?error=" + ShiroFilterUtils.STATUS_KICK);
                }

                subject.logout();
                return false;
            }
        }

        return true;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        return false;
    }
}
