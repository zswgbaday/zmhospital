package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Department;
import com.zm.hospital.model.Register;
import com.zm.hospital.model.User;
import com.zm.hospital.model.bo.RegisterBo;
import com.zm.hospital.service.IDepartmentService;
import com.zm.hospital.service.impl.IRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 挂号控制器
 * Created by ange on 2016/11/17.
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {

    private final IRegisterService registerService;

    private final IDepartmentService departmentService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    public RegisterController(IRegisterService registerService, IDepartmentService departmentService) {
        this.registerService = registerService;
        this.departmentService = departmentService;
    }

    /**
     * 列表
     *
     * @param page 当前页数
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String search_name, String search_doctor, String search_user, String search_depart, Integer page, Integer pagesize, Model model) {
        PageInfo<RegisterBo> pageInfo = new PageInfo<RegisterBo>(page, pagesize);

        //搜索条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if (search_name != null && StringUtils.isNoneBlank(search_name)) {
            condition.put("search_name", search_name);
        }
        if (search_doctor != null && StringUtils.isNoneBlank(search_doctor)) {
            condition.put("search_doctor", search_doctor);
        }
        if (search_user != null && StringUtils.isNoneBlank(search_user)) {
            condition.put("search_user", search_user);
        }
        if (search_depart != null && StringUtils.isNoneBlank(search_depart)) {
            condition.put("search_depart", search_depart);
        }
        pageInfo.setCondition(condition);

        registerService.getListBo(pageInfo);

        //科室列表
        List<Department> departments = departmentService.getAllList();

        model.addAttribute("pageinfo", pageInfo);
        model.addAttribute("departments", departments);
        model.addAttribute("add_url", this.getContextPath() + "/register/add");
        model.addAttribute("edit_url", this.getContextPath() + "/register/edit");
        model.addAttribute("delete_url", this.getContextPath() + "/register/delete");
        return "register/tables";
    }

    /**
     * 增加页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        RegisterBo registerBo = new RegisterBo();
        registerBo.setTime(new Date());//默认时间

        //科室列表
        List<Department> departments = departmentService.getAllList();

        model.addAttribute("registerBo", registerBo);
        model.addAttribute("departments", departments);
        model.addAttribute("form_url", this.getContextPath() + "/register/doadd");
        model.addAttribute("action", "add");
        return "register/edit";
    }

    /**
     * 处理增加
     *
     * @return
     */
    @RequestMapping(value = "/doadd", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doadd(Register register) {
        //初始化某些变量
        register.setTime(new Date());
        register.setStatus((byte) 0);

        //当前用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        register.setUserId(user.getId());

        try {
            registerService.add(register);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }
        return renderSuccess("增加成功!");
    }

    /**
     * 编辑页面
     *
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Integer id, Model model) {
        RegisterBo registerBo = registerService.findBoById(id);

        //科室列表
        List<Department> departments = departmentService.getAllList();

        model.addAttribute("registerBo", registerBo);
        model.addAttribute("departments", departments);
        model.addAttribute("form_url", this.getContextPath() + "/register/doedit");
        model.addAttribute("action", "edit");
        return "register/edit";
    }

    /**
     * 处理编辑
     *
     * @return
     */
    @RequestMapping(value = "/doedit", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doedit(Register register) {
        try {
            registerService.update(register);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }
        return renderSuccess("修改成功！");
    }

    /**
     * 处理删除
     *
     * @param id id
     * @return
     */
    @RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object delete(Integer id) {
        try {
            registerService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }
        return renderSuccess("删除成功!");
    }

}
