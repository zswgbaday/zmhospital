package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.result.Result;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Department;
import com.zm.hospital.model.Doctor;
import com.zm.hospital.service.IDepartmentService;
import com.zm.hospital.service.IDoctorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生控制器
 * Created by Administrator on 2016-11-17.
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController extends BaseController {

    private final IDoctorService doctorService;

    private final IDepartmentService departmentService;

    @Autowired
    public DoctorController(IDoctorService doctorService, IDepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    /**
     * 列表
     *
     * @param page 当前页数
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String search_name, String search_depart, Integer page, Integer pagesize, Model model) {
        PageInfo<Doctor> pageInfo = new PageInfo<Doctor>(page, pagesize);

        //搜索条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if (search_name != null && StringUtils.isNoneBlank(search_name)) {
            condition.put("search_name", search_name);
        }

        if (search_depart != null && StringUtils.isNoneBlank(search_depart)) {
            condition.put("search_depart", search_depart);
        }

        pageInfo.setCondition(condition);

        doctorService.getList(pageInfo);

        //科室列表
        List<Department> departments = departmentService.getAllList();

        model.addAttribute("pageinfo", pageInfo);
        model.addAttribute("departments", departments);
        model.addAttribute("add_url", this.getContextPath() + "/doctor/add");
        model.addAttribute("edit_url", this.getContextPath() + "/doctor/edit");
        model.addAttribute("delete_url", this.getContextPath() + "/doctor/delete");
        return "doctor/tables";
    }

    /**
     * 增加页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        Doctor doctor = new Doctor();
        doctor.setSex(true);

        //科室列表
        List<Department> departments = departmentService.getAllList();

        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departments);
        model.addAttribute("form_url", this.getContextPath() + "/doctor/doadd");
        model.addAttribute("action", "add");
        return "doctor/edit";
    }

    /**
     * 处理增加
     *
     * @return
     */
    @RequestMapping(value = "/doadd", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doadd(Doctor doctor) {
        try {
            doctorService.add(doctor);
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
        Doctor doctor = doctorService.findById(id);

        List<Department> departments = departmentService.getAllList();

        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departments);
        model.addAttribute("form_url", this.getContextPath() + "/doctor/doedit");
        model.addAttribute("action", "edit");
        return "doctor/edit";
    }

    /**
     * 处理编辑
     *
     * @return
     */
    @RequestMapping(value = "/doedit", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doedit(Doctor doctor) {
        try {
            doctorService.update(doctor);
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
            doctorService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }
        return renderSuccess("删除成功!");
    }

    /**
     * ajax获得某科室的所有医生
     *
     * @param id id
     * @return
     */
    @RequestMapping(value = "/getdepartall", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getDepartAll(Integer id) {
        List<Doctor> doctors;
        try {
            doctors = doctorService.getDoctorsByDepartId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }

        //返回ajax
        Result result = new Result();
        result.setMsg("获取成功");
        result.setSuccess(true);
        result.setObj(doctors);

        return result;
    }
}
