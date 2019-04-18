package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页控制器
 * Created by Administrator on 2016-11-16.
 */
@Controller
public class PageController extends BaseController {

    /**
     * 根
     * @return
     */
    @RequestMapping("/")
    public String tindex() {
        return "/index";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/index/index";
    }

    /**
     * 前台挂号页面[未做]
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "";
    }

}
