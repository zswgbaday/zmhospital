package com.zm.hospital.common.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * spring mvc自定义过滤器filter实现对请求参数编解码
 * Created by Administrator on 2016-09-19.
 */
public class RequestFilter extends OncePerRequestFilter {

    public String filter(HttpServletRequest request, String input) {
        String ret = input;
        //ios客户端请求参数值可能为(null)服务端过滤掉当null处理即可
        if (input == null || input.trim().equals("(null)")) {
            return null;
        }
        final String userAgent = request.getHeader("User-Agent");
        final String method = request.getMethod();
        //该处可以实现各种业务的自定义的过滤机制
        if (method.equalsIgnoreCase("get")
                || userAgent.toLowerCase().contains("android")) {
            try {
                if (ret.equals(new String(ret.getBytes("iso8859-1"), "iso8859-1"))) {
                    ret = new String(input.getBytes("ISO8859-1"), "utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        chain.doFilter(new HttpServletRequestWrapper(request) {
            @Override
            public String getParameter(String name) {
                String value = super.getParameter(name);
                return filter(this, value);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values = super.getParameterValues(name);
                if (values == null) {
                    return null;
                }
                for (int i = 0; i < values.length; i++) {
                    values[i] = filter(this, values[i]);
                }
                return values;
            }

        }, response);

    }
}
