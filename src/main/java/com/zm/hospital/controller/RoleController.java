package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.result.Node;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Resource;
import com.zm.hospital.model.Role;
import com.zm.hospital.service.IResourceService;
import com.zm.hospital.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 * Created by Administrator on 2016-09-26.
 */
@Controller("roleController")
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final IRoleService roleService;

    private final IResourceService resourceService;

    @Autowired
    public RoleController(IRoleService roleService, IResourceService resourceService) {
        this.roleService = roleService;
        this.resourceService = resourceService;
    }

    /**
     * 角色列表
     *
     * @param page 当前页数
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String search_name, Integer page, Integer pagesize, Model model) {
        PageInfo<Role> pageInfo = new PageInfo<Role>(page, 4);

        //搜索条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if (search_name != null) {
            if (StringUtils.isNoneBlank(search_name)) {
                condition.put("search_name", search_name);
            }
        }
        pageInfo.setCondition(condition);
        roleService.getList(pageInfo);

        model.addAttribute("pageinfo", pageInfo);
        return "role/tables";
    }

    /**
     * 编辑页面
     *
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Integer id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        model.addAttribute("form_url", this.getContextPath() + "/role/doedit");
        model.addAttribute("action", "edit");
        return "role/edit";
    }

    /**
     * 处理编辑
     *
     * @return
     */
    @RequestMapping(value = "/doedit", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doedit(Role role) {
        boolean result = roleService.update(role);
        if (result) {
            return renderSuccess("修改成功！");
        }
        return renderError("修改失败！");
    }

    /**
     * 增加页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        Role role = new Role();
        role.setStatus((byte)1);

        model.addAttribute("role", role);
        model.addAttribute("form_url", this.getContextPath() + "/role/doadd");
        model.addAttribute("action", "add");
        return "role/edit";
    }

    /**
     * 处理增加用户
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/doadd", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doadd(Role role) {
        role.setCreateTime(new Date());
        if (roleService.add(role)) {
            return renderSuccess("增加成功!");
        }
        return renderError("增加失败!");
    }

    /**
     * 角色分配资源页面
     *
     * @return
     */
    @RequestMapping("/grant")
    public String grant(Integer id, Model model) {
        List<Resource> all = resourceService.getAllResourceList();

        //构造资源树
        List<Node<Resource>> resTree = resourceService.findALLTree(all);//所有的资源列表
        List<Integer> resourceIds = resourceService.findResourceIdListByRoleId(id);
        if (resourceIds.size() > 0) {
            for (Node<Resource> node : resTree) {
                if (resourceIds.contains(node.getItem().getId())) {
                    node.setChecked(true);//设置选中
                }
            }
        }

        //角色
        Role role = roleService.findById(id);

        model.addAttribute("role", role);
        model.addAttribute("resTree", resTree);
        return "role/grant";
    }

    /**
     * 处理角色分配资源
     *
     * @return
     */
    @RequestMapping(value = "/dogrant", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object dogrant(Integer roleId, @RequestParam(value = "resIds", required = false) Integer[] resIds) {
        try {
            roleService.grant(roleId, resIds);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("分配失败!");
        }
        return renderSuccess("分配成功!");
    }

    /**
     * @param id id
     * @return
     */
    @RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object delete(Integer id) {
        if (roleService.deleteById(id)) {
            return renderSuccess("删除成功!");
        }
        return renderError("删除失败!");
    }
}
