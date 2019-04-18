package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Patient;
import com.zm.hospital.service.IPatientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 病人控制器
 * Created by Administrator on 2016-11-16.
 */
@Controller
@RequestMapping("/patient")
public class PatientController extends BaseController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }


    /**
     * 列表
     *
     * @param page 当前页数
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String search_name, Integer page, Integer pagesize, Model model) {
        PageInfo<Patient> pageInfo = new PageInfo<Patient>(page, pagesize);

        //搜索条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if (search_name != null) {
            if (StringUtils.isNoneBlank(search_name)) {
                condition.put("search_name", search_name);
            }
        }
        pageInfo.setCondition(condition);

        patientService.getList(pageInfo);

        model.addAttribute("pageinfo", pageInfo);
        model.addAttribute("add_url", this.getContextPath() + "/patient/add");
        model.addAttribute("edit_url", this.getContextPath() + "/patient/edit");
        model.addAttribute("delete_url", this.getContextPath() + "/patient/delete");
        return "patient/tables";
    }

    /**
     * 增加页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        Patient patient = new Patient();
        patient.setSex(true);
        model.addAttribute("patient", patient);
        model.addAttribute("form_url", this.getContextPath() + "/patient/doadd");
        model.addAttribute("action", "add");
        return "patient/edit";
    }

    /**
     * 处理增加
     *
     * @return
     */
    @RequestMapping(value = "/doadd", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doadd(Patient patient) {
        try {
            patientService.add(patient);
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
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("form_url", this.getContextPath() + "/patient/doedit");
        model.addAttribute("action", "edit");
        return "patient/edit";
    }

    /**
     * 处理编辑
     *
     * @return
     */
    @RequestMapping(value = "/doedit", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doedit(Patient patient) {
        try {
            patientService.update(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }
        return renderSuccess("修改成功！");
    }

    /**
     * @param id id
     * @return
     */
    @RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object delete(Integer id) {
        try {
            patientService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }
        return renderSuccess("删除成功!");
    }

    /**
     * 选择病人页面
     * @return
     */
    @RequestMapping("/select")
    public String selectPatient(String search_name, Integer page, Integer pagesize, Model model){
        PageInfo<Patient> pageInfo = new PageInfo<Patient>(page, pagesize);

        //搜索条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if (search_name != null) {
            if (StringUtils.isNoneBlank(search_name)) {
                condition.put("search_name", search_name);
            }
        }
        pageInfo.setCondition(condition);

        patientService.getList(pageInfo);

        model.addAttribute("pageinfo", pageInfo);
        return "patient/select";
    }
}

