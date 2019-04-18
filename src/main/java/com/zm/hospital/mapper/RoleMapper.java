package com.zm.hospital.mapper;

import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Role;
import com.zm.hospital.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    /**
     * 获得经过分页限制和查询条件的列表
     * @param pageInfo
     * @return 角色列表
     */
    List<Role> findRolePageCondition(PageInfo pageInfo);

    /**
     * 获得数量
     * @param pageInfo
     * @return 总数
     */
    int findRolePageCount(PageInfo pageInfo);

    /**
     * 获得有所有的角色的列表
     * @return
     */
    List<Role> getAllRoleList();

    /**
     * 根据角色id查找角色
     * @param id id
     * @return 角色
     */
    Role findRoleById(@Param("id") long id);

    /**
     * 根据用户id查找该用户的所有角色实体
     *
     * @param userId 用户id
     * @return
     */
    List<Role> findRolesByUserId(@Param("userId") Integer userId);
}