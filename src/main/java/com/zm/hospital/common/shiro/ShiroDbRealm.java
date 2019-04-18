package com.zm.hospital.common.shiro;

import com.zm.hospital.model.User;
import com.zm.hospital.service.IResourceService;
import com.zm.hospital.service.IRoleService;
import com.zm.hospital.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 自定义的指定Shiro验证用户登录的类
 * Created by Administrator on 2016-10-10.
 */
public class ShiroDbRealm extends AuthorizingRealm {

    private static Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private AbstractSessionDAO sessionDAO;

    /**
     * 验证当前登录的Subject
     * 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        LOGGER.info("Shiro开始登录认证");

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.findUserByLoginName(token.getUsername());

        // 账号不存在
        if (user == null) {
            return null;
        }

        // 账号未启用
        if (user.getStatus() == 1) {
            throw new DisabledAccountException("帐号已经禁止登录！");
        }


        //使用自定义的shirouser,没必要
        //List<Integer> roleList = roleService.findRoleIdListByUserId(user.getId());
        //ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginname(), user.getName(), roleList);

        // 认证缓存信息
        return new SimpleAuthenticationInfo(user, user.getPassword().toCharArray(), getName());
    }

    /**
     * 为当前登录的Subject授予角色和权限
     * 该方法的调用时机为需授权资源被访问时
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加角色
        Set<String> roleSet;
        //添加资源
        Set<String> urlSet;

        if (user.isSuperAdmin()) {
            //超级管理员特殊处理,总是获得所有角色和资源
            info.addRole("superAdmin");
            LOGGER.debug("用户{}被授予超级管理员", user);
            roleSet = roleService.getRoleNameSet(roleService.getAllRoleList());
            urlSet = resourceService.getResourcesUrlSet(resourceService.getAllResourceList());
        } else {
            //根据用户id获得相应的角色和权限
            roleSet = roleService.getRoleNameSet(roleService.findRolesByUserId(user.getId()));
            urlSet = resourceService.getResourcesUrlSet(resourceService.findResourcesByUserId(user.getId()));
        }

        info.setStringPermissions(roleSet);
        info.setStringPermissions(urlSet);

        LOGGER.debug("用户{}被授予的角色{}", user, roleSet);
        LOGGER.debug("用户{}被授予的资源url{}", user, urlSet);

        return info;
    }

}
