package com.zm.hospital.service;

import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Role;
import com.zm.hospital.model.User;

import java.util.List;
import java.util.Set;

/**
 *
 * Created by ange on 2016/9/14.
 */
public interface IRoleService extends IBaseService<Role>{
    /**
     * 获得所有的角色列表
     * @return
     */
    List<Role> getAllRoleList();

    /**
     * 根据用户查询id查询该用户所拥有的角色id列表
     *
     * @param userId 用户id
     * @return
     */
    List<Integer> findRoleIdListByUserId(Integer userId);

    /**
     * 根据用户id,查询该用户的所有角色
     * @param userId 用户id
     * @return
     */
    List<Role> findRolesByUserId(Integer userId);

    /**
     * 为角色分配资源
     * @param roleId 角色id
     * @param resIds 资源id数组
     */
    void grant(Integer roleId, Integer[] resIds);

    /**
     * 从资源实体列表中提取出各个资源的url,组成Set集合
     *
     * @param roles 角色list
     * @return
     */
    Set<String> getRoleNameSet(List<Role> roles);
}
