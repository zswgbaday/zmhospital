package com.zm.hospital.common.controller;

import com.zm.hospital.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础控制器
 * Created by Administrator on 2016-09-19.
 */
public abstract class BaseController {
    //日志工具
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获得当前url
     *
     * @return
     */
    protected String makeUrl() {
        return request.getRequestURL().toString() + "?" + request.getQueryString();
    }

    public BaseController() {

    }

    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    protected Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    protected Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * 创建一个新的ajax返回对象
     * @return
     */
    protected Result renderResult(){
        //其它功能待添加
        return new Result();
    }
    /**
     * 获得上下文路径
     * @return
     */
    protected String getContextPath(){
        return request.getContextPath();
    }
}
