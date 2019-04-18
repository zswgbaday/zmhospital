package com.zm.hospital.common.exception;

import com.zm.hospital.common.result.Result;
import com.zm.hospital.common.utils.JsonUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义全局异常处理
 * 待完善
 * Created by ange on 2016/11/13.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    /**
     * Try to resolve the given exception that got thrown during handler execution,
     * returning a {@link ModelAndView} that represents a specific error page if appropriate.
     * <p>The returned {@code ModelAndView} may be {@linkplain ModelAndView#isEmpty() empty}
     * to indicate that the exception has been resolved successfully but that no view
     * should be rendered, for instance by setting a status code.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the executed handler, or {@code null} if none chosen at the
     *                 time of the exception (for example, if multipart resolution failed)
     * @param ex       the exception that got thrown during handler execution
     * @return a corresponding {@code ModelAndView} to forward to, or {@code null}
     * for default processing
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if (isAjax(request)) {
            Result result = new Result();
            String msg = ex.getMessage();
            if (msg != null) {
                result.setMsg(ex.getMessage());
            }else {
                result.setMsg(ex.toString());
            }
            result.setSuccess(false);
            JsonUtils.out(response, result);
            return null;
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("errors/error");//设置返回模板名称
        mv.addObject("msg", ex.getMessage());//设置参数值，在前台页面可以通过获取到

        return mv;
    }


    private boolean isAjax(ServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }
}
