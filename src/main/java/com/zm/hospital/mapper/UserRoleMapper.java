package com.zm.hospital.mapper;

import com.zm.hospital.model.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int insertSelective(UserRole record);

    /**
     * 删除用户的所有角色
     * @param userId 用户id
     * @return
     */
    int deleteAllUserRoles(Integer userId);

    /**
     * 根据用户id找到该用户的所有角色id
     * @param userId 用户id
     * @return 角色id list
     */
    List<Integer> findRoleIdListByUserId(@Param("userId") Integer userId);
}