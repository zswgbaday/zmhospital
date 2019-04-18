package com.zm.hospital.service;

import com.zm.hospital.model.Department;
import com.zm.hospital.service.impl.BaseService;

import java.util.List;

/**
 *
 * Created by ange on 2016/11/16.
 */
public interface IDepartmentService extends IBaseService<Department>{

    /**
     * 获得所有部门列表
     * @return
     */
    List<Department> getAllList();

}
