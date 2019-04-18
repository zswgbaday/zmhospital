package com.zm.hospital.service.impl;

import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.mapper.RoleMapper;
import com.zm.hospital.mapper.RoleResourceMapper;
import com.zm.hospital.mapper.UserRoleMapper;
import com.zm.hospital.model.Resource;
import com.zm.hospital.model.Role;
import com.zm.hospital.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 角色服务
 * Created by ange on 2016/9/26.
 */
@Service("roleService")
public class RoleService extends BaseService<Role> implements IRoleService {


    private final RoleMapper roleMapper;

    private final UserRoleMapper userRoleMapper;

    private final RoleResourceMapper roleResourceMapper;

    @Autowired
    public RoleService(RoleMapper roleMapper, UserRoleMapper userRoleMapper, RoleResourceMapper roleResourceMapper) {
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleResourceMapper = roleResourceMapper;
    }


    public void getList(PageInfo<Role> pageInfo) {
        //排序
        Map<String, String> orderby = new HashMap<String, String>();
        orderby.put("id", "desc");
        pageInfo.setOrderby(orderby);

        pageInfo.setTotal(roleMapper.findRolePageCount(pageInfo));
        pageInfo.setList(roleMapper.findRolePageCondition(pageInfo));
    }

    public boolean add(Role role) {
        int insertId = roleMapper.insertSelective(role);
        return insertId > 0;
    }

    public boolean update(Role role) {
        int rows = roleMapper.updateByPrimaryKeySelective(role);
        return rows > 0;
    }

    public Role findById(Integer id) {
        return roleMapper.findRoleById(id);
    }

    /*以下方法特有*/

    /**
     * 删除用户
     *
     * @param id id
     */
    public boolean deleteById(Integer id) {
        int rows = roleMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }

    public List<Role> getAllRoleList() {
        return roleMapper.getAllRoleList();
    }

    public List<Integer> findRoleIdListByUserId(Integer userId) {
        return userRoleMapper.findRoleIdListByUserId(userId);
    }

    /**
     * 根据用户id,查询该用户的所有角色
     *
     * @param userId 用户id
     * @return
     */
    public List<Role> findRolesByUserId(Integer userId) {
        return roleMapper.findRolesByUserId(userId);
    }

    public void grant(Integer roleId, Integer[] resIds) {
        //先删除该用户的所有角色
        int rows = roleResourceMapper.deleteAllRoleResources(roleId);
        if (rows < 0) {
            throw new ServiceException("更新前先删除角色的所有资源失败!");
        }

        if (resIds != null && resIds.length > 0) {
            rows = roleResourceMapper.insertAllResource(roleId, resIds);
            if (rows <= 0) {
                throw new ServiceException("角色的资源插入失败");
            }
            //遍历传递过来的角色数组
//            for (Integer resourceId : resIds) {
//                //新建[角色资源bean]
//                RoleResource roleResource = new RoleResource();
//                roleResource.setRoleId(roleId);
//                roleResource.setResourceId(resourceId);
//                //插入
//                int insertId = roleResourceMapper.insertSelective(roleResource);
//                if (insertId <= 0) {
//                    throw new ServiceException("角色的资源插入失败");
//                }
//            }
        }
    }

    /**
     * 从资源实体列表中提取出各个资源的url,组成Set集合
     *
     * @param roles 角色list
     * @return
     */
    public Set<String> getRoleNameSet(List<Role> roles) {
        Set<String> roleSet = new HashSet<String>();
        for (Role role : roles) {
            String name = role.getName();
            if (StringUtils.isNotBlank(name)) {
                roleSet.add(role.getName());
            }
        }
        return roleSet;
    }
}
