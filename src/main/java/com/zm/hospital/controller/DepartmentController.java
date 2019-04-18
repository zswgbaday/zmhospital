package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Department;
import com.zm.hospital.model.Patient;
import com.zm.hospital.service.IDepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 科室
 * Created by ange on 2016/11/16.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    private final IDepartmentService departmentService;

    @Autowired
    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 列表
     *
     * @param page 当前页数
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String search_name ,Integer page, Integer pagesize, Model model) {
        PageInfo<Department> pageInfo = new PageInfo<Department>(page, pagesize);

        //搜索条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if (search_name != null && StringUtils.isNoneBlank(search_name)) {
            condition.put("search_name", search_name);
        }
        pageInfo.setCondition(condition);

        departmentService.getList(pageInfo);

        model.addAttribute("pageinfo", pageInfo);
        model.addAttribute("add_url", this.getContextPath() + "/department/add");
        model.addAttribute("edit_url", this.getContextPath() + "/department/edit");
        model.addAttribute("delete_url", this.getContextPath() + "/department/delete");
        return "department/tables";
    }

    /**
     * 增加页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("form_url", this.getContextPath() + "/department/doadd");
        model.addAttribute("action", "add");
        return "department/edit";
    }

    /**
     * 处理增加
     *
     * @return
     */
    @RequestMapping(value = "/doadd", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doadd(Department department) {
        try {
            departmentService.add(department);
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
        Department department = departmentService.findById(id);
        model.addAttribute("patient", department);
        model.addAttribute("form_url", this.getContextPath() + "/department/doedit");
        model.addAttribute("action", "edit");
        return "department/edit";
    }

    /**
     * 处理编辑
     *
     * @return
     */
    @RequestMapping(value = "/doedit", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doedit(Department department) {
        try {
            departmentService.update(department);
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
            departmentService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }
        return renderSuccess("删除成功!");
    }
}
