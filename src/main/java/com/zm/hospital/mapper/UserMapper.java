package com.zm.hospital.mapper;

import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);


    /**
     * 直接执行sql语句查询list
     * @param sql
     * @return
     */
    List<Map> querylist(@Param("paramsql") String sql);

    /**
     * 获得用户数量
     * @param pageInfo
     * @return
     */
    int findUserPageCount(PageInfo pageInfo);

    /**
     * 获得经过分页限制和查询条件的用户列表
     * @param pageInfo
     * @return
     */
    List<User> findUserPageCondition(PageInfo pageInfo);

    /**
     * 根据用户id查找用户
     * @param id
     * @return
     */
    User findUserById(@Param("id") long id);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User findUserByLoginName(@Param("username") String username);
}