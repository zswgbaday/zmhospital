package com.zm.hospital.common.shiro.filter;

import com.zm.hospital.common.result.Result;
import com.zm.hospital.common.utils.JsonUtils;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义登录filter
 * Created by ange on 2016/11/14.
 */
public class SimpleAuthFilter extends AccessControlFilter {

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        boolean flag = false;
        if (subject.isAuthenticated()) {
            //已经验证登录
            flag = true;

        } else {
            if (subject.getPrincipal() != null) {
                //使用了记住我功能

                //提示验证超时
                /*
                try {
                    Session session = subject.getSession(false);
                    session.getStartTimestamp();
                } catch (ExpiredSessionException e) {
                    // 超时

                }
                */
            }
        }
        return flag;
    }


    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (ShiroFilterUtils.isAjax(request)) {
            //返回ajax
            Result result = new Result();
            result.setSuccess(false);
            result.setMsg("请登录后操作");
            JsonUtils.out(response, result);
        } else {
            saveRequestAndRedirectToLogin(request, response);
        }
        return false;
    }
}
