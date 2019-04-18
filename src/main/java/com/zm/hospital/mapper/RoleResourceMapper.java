package com.zm.hospital.mapper;

import com.zm.hospital.model.Resource;
import com.zm.hospital.model.RoleResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResourceMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("resourceId") Integer resourceId);

    int insertSelective(RoleResource record);

    /**
     * 批量插入资源
     * @param roleId 角色id
     * @param resIds 资源id数组
     * @return
     */
    int insertAllResource(@Param("roleId") Integer roleId,@Param("resIds") Integer[] resIds);

    /**
     * 删除角色的所有分配的资源
     * @param roleId 角色id
     * @return 影响行数
     */
    int deleteAllRoleResources(Integer roleId);

    /**
     * 根据角色id找到该角色的所有资源id
     * @param roleId 角色id
     * @return  资源 list
     */
    List<Integer> findResourcesByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色id找到该角色的所有资源(bean类型)
     * @param roleId 角色id
     * @return 资源bean list
     */
    List<Resource> findResourcesEntityByRoleId(@Param("roleId") Integer roleId);
}