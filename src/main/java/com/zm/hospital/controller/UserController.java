package com.zm.hospital.controller;

import com.zm.hospital.common.controller.BaseController;
import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.result.Result;
import com.zm.hospital.common.result.Option;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Role;
import com.zm.hospital.model.User;
import com.zm.hospital.service.IRoleService;
import com.zm.hospital.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 用户控制器
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {

    private final IUserService userService;

    private final IRoleService roleService;

    @Autowired
    public UserController(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    /**
     * 用户列表
     *
     * @param page 当前页数
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String search_name, Integer page, Integer pagesize, Model model) {
        PageInfo<User> pageInfo = new PageInfo<User>(page, 4);

        //搜索条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if (search_name != null) {
            if (StringUtils.isNoneBlank(search_name)) {
                condition.put("search_name", search_name);
            }
        }
        pageInfo.setCondition(condition);

        //查询用户列表,结果都保存在pageinfo里面了
        userService.getList(pageInfo);

        //所有角色列表,用于用户分配角色
        List<Role> roles = roleService.getAllRoleList();

        //ModelAndView mv = new ModelAndView();
        //mv.setViewName("user/tables");//设置返回模板名称
        //mv.addObject("user", users);//设置参数值，在前台页面可以通过获取到

        //返回视图
        model.addAttribute("pageinfo", pageInfo);
        model.addAttribute("roles", roles);
//        model.addAttribute("cur_url", makeUrl());
        return "user/tables";
    }

    /**
     * 增加页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("form_url", this.getContextPath() + "/user/doadd");
        model.addAttribute("action", "add");
        return "user/edit";
    }

    /**
     * 处理增加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/doadd", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doadd(User user, @RequestParam(value = "roles", required = false) Integer[] roleIds) {
        user.setCreatedate(new Date());
        if (userService.add(user)) {
            //继续分配角色
            userService.addRoles(user.getId(), roleIds);
            return renderSuccess("增加成功!");
        }
        return renderError("增加失败!");
    }

    /**
     * 编辑页面
     *
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Integer id, Model model) {
        User user = userService.findById(id);

        //本用户的所有角色
        List<Integer> userRoleIds = roleService.findRoleIdListByUserId(id);

        //所有角色列表,用于用户分配角色
        List<Role> roles = roleService.getAllRoleList();

        //生成option,将返回到视图
        List<Option> options = new ArrayList<Option>();
        for (Role role : roles) {
            Option option = new Option();
            option.setValue(role.getId() + "");
            option.setName(role.getName());
            String selected = "";//是否选中
            for (Integer uRoleId : userRoleIds) {
                //如果有id
                if (role.getId().equals(uRoleId)) {
                    selected = "checked";//设置选中
                    break;
                }
            }
            option.setSelected(selected);
            options.add(option);
        }

        //返回视图
        model.addAttribute("user", user);

        model.addAttribute("roleOptions", options);
//        model.addAttribute("userRoleIds", userRoleIds);
//        model.addAttribute("roles", roles);

        model.addAttribute("form_url", this.getContextPath() + "/user/doedit");
        model.addAttribute("action", "edit");
        return "user/edit";
    }

    /**
     * 处理编辑
     *
     * @return
     */
    @RequestMapping(value = "/doedit", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object doedit(User user, @RequestParam(value = "roles", required = false) Integer[] roleIds) {
        User user2 = userService.findUserByLoginName(user.getLoginname());
        if (user2 != null && !user.getId().equals(user2.getId())) {
            return renderError("用户名已存在!");
        }

        String pwd = user.getPassword();
        if (pwd!=null && !StringUtils.isBlank(pwd)) {
            user.setPassword(DigestUtils.md5Hex(pwd));//md5密码加密
        }

        //result是将要返回的ajax结果
        Result result = new Result();
        try {
            //更新用户,封装到了一个事务中
            userService.updateUser(user, roleIds);
//            userService.update(user);
//            userService.updateRoles(user.getId(), roleIds);
            return renderSuccess("修改成功！");
        } catch (ServiceException e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param id id
     * @return
     */
    @RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object delete(Integer id) {
        if (userService.deleteById(id)) {
            return renderSuccess("删除成功!");
        }
        return renderError("删除失败!");
    }
}
