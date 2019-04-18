package com.zm.hospital.mapper;

import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.ResourceType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ResourceType record);

    ResourceType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceType record);

    /**
     * 获得经过分页限制和查询条件的列表
     * @param pageInfo 分页信息
     * @return 角色列表
     */
    List<ResourceType> findPageCondition(PageInfo pageInfo);

    /**
     * 获得数量
     * @param pageInfo 分页信息
     * @return 总数
     */
    int findPageCount(PageInfo pageInfo);

    /**
     * 获得所有资源类型列表
     * @return
     */
    List<ResourceType> getAllResourceTypeList();
}