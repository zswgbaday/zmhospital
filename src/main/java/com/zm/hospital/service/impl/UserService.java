package com.zm.hospital.service.impl;

import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.mapper.UserMapper;
import com.zm.hospital.mapper.UserRoleMapper;
import com.zm.hospital.model.User;
import com.zm.hospital.model.UserRole;
import com.zm.hospital.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务
 * Created by ange on 2016/9/14.
 */
@Service("userService")
public class UserService extends BaseService<User> implements IUserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserService(UserMapper userMapper, UserRoleMapper userRoleMapper) {
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
    }

    /**
     * 增加
     *
     * @param user 用户bean
     * @return 是否成功
     */
    public boolean add(User user) {
        //插入后的id
        int insertId = userMapper.insertSelective(user);
        //角色分配
        return insertId > 0;
    }

    /**
     * 更新信息
     *
     * @param user 用户bean
     * @return 是否成功
     */
    public boolean update(User user) {
        int rows = userMapper.updateByPrimaryKeySelective(user);
        return rows > 0;
    }

    /**
     * 通过id找到该bean
     *
     * @param id id
     * @return bean
     */
    public User findById(Integer id) {
        return userMapper.findUserById(id);

    }

    /**
     * 删除用户
     *
     * @param id id
     */
    public boolean deleteById(Integer id) {
        int rows = userMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }

    public void getList(PageInfo<User> pageInfo) {
        //排序
        Map<String, String> orderby = new HashMap<String, String>();
        orderby.put("id", "desc");
        pageInfo.setOrderby(orderby);

        pageInfo.setTotal(userMapper.findUserPageCount(pageInfo));
        pageInfo.setList(userMapper.findUserPageCondition(pageInfo));
        //List<User> list= userMapper.getList();
        //return list;
    }

    public void addRoles(Integer userId, Integer[] roleIds) {
        if (roleIds != null) {
            //遍历传递过来的角色数组
            for (Integer roleId : roleIds) {
                //新建[用户角色bean]
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                //插入
                int insertId = userRoleMapper.insertSelective(userRole);
                if (insertId <= 0) {
                    throw new ServiceException("角色插入失败");
                }
            }
        }
    }

    public void updateRoles(Integer userId, Integer[] roleIds) {
        boolean result;
        //先删除该用户的所有角色
        int rows = userRoleMapper.deleteAllUserRoles(userId);
        if (rows < 0) {
            throw new ServiceException("更新前先删除用户的角色失败!");
        }
        //return rows>0;
        //重新插入
        this.addRoles(userId, roleIds);
    }

    public void updateUser(User user, Integer[] roleIds) {
        //封装到一个事务中
        //更新用户
        this.update(user);
        //更新角色
        this.updateRoles(user.getId(), roleIds);
    }

    public User findUserByLoginName(String username) {
        return userMapper.findUserByLoginName(username);
    }

}
