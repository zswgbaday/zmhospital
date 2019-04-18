package com.zm.hospital.service.impl;

import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.mapper.ResourceTypeMapper;
import com.zm.hospital.model.ResourceType;
import com.zm.hospital.service.IResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源类型服务
 * Created by Administrator on 2016-09-30.
 */
@Service("resourceTypeService")
public class ResourceTypeService extends BaseService<ResourceType> implements IResourceTypeService {

    private final ResourceTypeMapper resourceTypeMapper;

    @Autowired
    public ResourceTypeService(ResourceTypeMapper resourceTypeMapper) {
        this.resourceTypeMapper = resourceTypeMapper;
    }

    public void getList(PageInfo<ResourceType> pageInfo) {
        //排序
        Map<String, String> orderby = new HashMap<String, String>();
        orderby.put("id", "desc");
        pageInfo.setOrderby(orderby);

        pageInfo.setTotal(resourceTypeMapper.findPageCount(pageInfo));
        pageInfo.setList(resourceTypeMapper.findPageCondition(pageInfo));
    }

    public boolean add(ResourceType resourceType) {
        int insertid = resourceTypeMapper.insertSelective(resourceType);
        if (insertid <= 0) {
            throw new ServiceException("增加失败");
        }
        return insertid > 0;
    }

    public boolean update(ResourceType resourceType) {
        int rows = resourceTypeMapper.updateByPrimaryKeySelective(resourceType);
        if (rows <= 0) {
            throw new ServiceException("修改失败!");
        }
        return rows > 0;
    }

    public ResourceType findById(Integer id) {
        return resourceTypeMapper.selectByPrimaryKey(id);
    }

    public boolean deleteById(Integer id) {
        int rows = resourceTypeMapper.deleteByPrimaryKey(id);
        if (rows <= 0) {
            throw new ServiceException("删除失败!");
        }
        return rows > 0;
    }

    /*以下方法特有*/
    public List<ResourceType> getAllResourceTypeList() {
        return resourceTypeMapper.getAllResourceTypeList();
    }

}
