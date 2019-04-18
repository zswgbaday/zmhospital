package com.zm.hospital.mapper;

import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    /**
     * 获得经过分页限制和查询条件的列表
     * @param pageInfo 分页信息
     * @return 角色列表
     */
    List<Resource> findPageCondition(PageInfo pageInfo);

    /**
     * 获得数量
     * @param pageInfo 分页信息
     * @return 总数
     */
    int findPageCount(PageInfo pageInfo);

    /**
     * 获得所有的资源列表
     * @return
     */
    List<Resource> getAllResourceList();

    /**
     * 获得用户所有能访问的资源
     *
     * @return
     */
    List<Resource> getUserResources(@Param("userId") Integer userId);

    /**
     * 获得用户所有关于菜单的资源
     *
     * @param userId 用户id
     * @return
     */
    List<Resource> getUserMenus(@Param("userId") Integer userId);

    /**
     * 获得所有菜单,超级管理员用
     *
     * @return
     */
    List<Resource> getAllMenus();
}