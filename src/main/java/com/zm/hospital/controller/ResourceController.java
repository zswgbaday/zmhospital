package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.exception.ControllerException;
import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.result.Menu;
import com.zm.hospital.common.result.Node;
import com.zm.hospital.common.result.Result;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Resource;
import com.zm.hospital.model.ResourceType;
import com.zm.hospital.model.User;
import com.zm.hospital.service.IResourceService;
import com.zm.hospital.service.IResourceTypeService;
import com.zm.hospital.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源控制器
 * Created by Administrator on 2016-09-26.
 */
@Controller("resourceController")
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private final IRoleService roleService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IResourceTypeService resourceTypeService;


    @Autowired
    public ResourceController(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 列表
     *
     * @param page 当前页数
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String search_name, String search_res_type, Integer page, Model model) {
        PageInfo<Resource> pageInfo = new PageInfo<Resource>(page, 9999);

        //搜索条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if (search_name != null) {//资源名称
            if (StringUtils.isNoneBlank(search_name)) {
                condition.put("search_name", search_name);
            }
        }
        if (search_res_type != null) {//资源类型
            if (StringUtils.isNoneBlank(search_res_type)) {
                condition.put("search_res_type", search_res_type);
            }
        }
        pageInfo.setCondition(condition);
        resourceService.getList(pageInfo);

        //资源类型
        List<ResourceType> resTypes = resourceTypeService.getAllResourceTypeList();

        //树形列表
        List<Resource> all = pageInfo.getList();// resourceService.getAllResourceList();
        List<Node<Resource>> tree = resourceService.findALLTree(all);

        model.addAttribute("resourceTree", tree);
        model.addAttribute("pageinfo", pageInfo);
        model.addAttribute("resTypes", resTypes);
        return "resource/tables";
    }

    /**
     * 增加页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Integer pid, Model model) {
        //资源
        Resource resource = new Resource();
        resource.setPid(pid);
        resource.setStatus((byte) 1);//默认为1

        //所有资源列表,用于选择父资源
        List<Resource> resources = resourceService.getAllResourceList();
        List<Node<Resource>> allTree = resourceService.findALLTree(resources);

        //资源类型列表
        List<ResourceType> restypes = resourceTypeService.getAllResourceTypeList();


        model.addAttribute("resource", resource);
        model.addAttribute("allTree", allTree);
        model.addAttribute("restypes", restypes);
        model.addAttribute("form_url", this.getContextPath() + "/resource/doadd");
        model.addAttribute("action", "add");
        return "resource/edit";
    }

    /**
     * 处理增加用户
     *
     * @param resource
     * @return
     */
    @RequestMapping(value = "/doadd", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doadd(Resource resource) {
        resource.setCreateTime(new Date());
        resource.setSeq(1);
        try {
            resourceService.add(resource);
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
        //资源
        Resource resource = resourceService.findById(id);

        //所有资源列表
        List<Resource> resources = resourceService.getAllResourceList();
        List<Node<Resource>> allTree = resourceService.findALLTree(resources);

        //资源类型列表
        List<ResourceType> restypes = resourceTypeService.getAllResourceTypeList();

        model.addAttribute("resource", resource);
        model.addAttribute("allTree", allTree);
        model.addAttribute("restypes", restypes);
        model.addAttribute("form_url", this.getContextPath() + "/resource/doedit");
        model.addAttribute("action", "edit");
        return "resource/edit";
    }

    /**
     * 处理编辑
     *
     * @return
     */
    @RequestMapping(value = "/doedit", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doedit(Resource resource) {
        try {
            resourceService.update(resource);
        } catch (ServiceException e) {
            e.printStackTrace();
            return renderError("修改失败！");
        }
        return renderSuccess("修改成功！");
    }

    /**
     * ajax获取菜单
     *
     * @return
     */
    @RequestMapping(value = "/getmenu", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object menu() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Result result = new Result();
        if (user != null) {
            List<Menu> tree = getMenuTree(user);

            result.setSuccess(true);
            result.setObj(tree);
            result.setMsg("获取菜单成功");

        } else {
            result.setSuccess(false);
            result.setMsg("登录后才能获取菜单");
        }
        return result;
    }

    @Cacheable(value = "menuCache", key = "#user.id")
    public List<Menu> getMenuTree(User user) {
        List<Resource> resources;
        if (user.isSuperAdmin()) {
            resources = resourceService.findMenusAll();
        } else {
            resources = resourceService.findMenusByUserId(user.getId());
        }

        //转化为树形菜单
        List<Menu> menus = resourceService.findAllMenu(resources);

        //List<Node<Resource>> tree = resourceService.findALLTree(menus);

        return menus;
    }

    /**
     * @param id id
     * @return
     */
    @RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object delete(Integer id) {
        try {
            resourceService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("删除失败!");
        }
        return renderSuccess("删除成功!");
    }

    /**
     * 一键创建增删查改资源
     *
     * @return
     */
    @RequestMapping(value = "/autocreate")
    public Object autoCreate(String controller_name, String descript) throws ControllerException {
        try {
            resourceService.autoCreateCRUD(controller_name, descript);
        }catch (ServiceException e){
            e.printStackTrace();
            throw new ControllerException(e.getMessage());//交给spring分发到错误页面
            //return renderError(e.getMessage());
        }
        return "redirect:list";
        //return renderSuccess("一键创建成功!");
    }
}
