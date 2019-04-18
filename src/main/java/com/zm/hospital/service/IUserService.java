package com.zm.hospital.service;

import com.zm.hospital.model.User;

/**
 * 用户服务接口
 * Created by ange on 2016/9/14.
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 增加用户的角色
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     */
    void addRoles(Integer userId, Integer[] roleIds);

    /**
     * 更新用户的角色
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     */
    void updateRoles(Integer userId, Integer[] roleIds);

    /**
     * 更新用户,包括用户资料和角色
     * @param users 用户bean
     * @param roleIds 角色id数组
     */
    void updateUser(User user, Integer[] roleIds);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户bean
     */
    User findUserByLoginName(String username);

}
