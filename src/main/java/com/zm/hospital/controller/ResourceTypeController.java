package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.ResourceType;
import com.zm.hospital.service.IResourceTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 资源控制器
 * Created by Administrator on 2016-09-26.
 */
@Controller("resourceTypeController")
@RequestMapping("/resourcetype")
public class ResourceTypeController extends BaseController {

    private final IResourceTypeService resourceTypeService;


    @Autowired
    public ResourceTypeController(IResourceTypeService resourceTypeService) {
        this.resourceTypeService = resourceTypeService;
    }

    /**
     * 列表
     *
     * @param page 当前页数
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String search_name, Integer page, Integer pagesize, Model model) {
        PageInfo<ResourceType> pageInfo = new PageInfo<ResourceType>(page, 4);

        //搜索条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if (search_name != null) {
            if (StringUtils.isNoneBlank(search_name)) {
                condition.put("search_name", search_name);
            }
        }
        pageInfo.setCondition(condition);
        resourceTypeService.getList(pageInfo);

        model.addAttribute("pageinfo", pageInfo);
        return "resourcetype/tables";
    }

    /**
     * 增加页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        ResourceType resourceType = new ResourceType();
        model.addAttribute("resourcetype", resourceType);
        model.addAttribute("form_url", this.getContextPath() + "/resourcetype/doadd");
        model.addAttribute("action", "add");
        return "resourcetype/edit";
    }

    /**
     * 处理增加用户
     *
     * @param resourceType 资源类型
     * @return
     */
    @RequestMapping(value = "/doadd", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doadd(ResourceType resourceType) {
        try {
            resourceTypeService.add(resourceType);
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
        ResourceType resourceType = resourceTypeService.findById(id);
        model.addAttribute("resourcetype", resourceType);
        model.addAttribute("form_url", this.getContextPath() + "/resourcetype/doedit");
        model.addAttribute("action", "edit");
        return "resourcetype/edit";
    }

    /**
     * 处理编辑
     *
     * @return
     */
    @RequestMapping(value = "/doedit", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doedit(ResourceType resourceType) {
        try {
            resourceTypeService.update(resourceType);
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
            resourceTypeService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }
        return renderSuccess("删除成功!");
    }
}
