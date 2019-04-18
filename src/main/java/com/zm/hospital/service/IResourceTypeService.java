package com.zm.hospital.service;

import com.zm.hospital.model.ResourceType;

import java.util.List;

/**
 * 资源类型接口
 * Created by Administrator on 2016-09-30.
 */
public interface IResourceTypeService extends IBaseService<ResourceType> {
    /**
     * 获得所有资源类型列表
     *
     * @return
     */
    List<ResourceType> getAllResourceTypeList();

}
