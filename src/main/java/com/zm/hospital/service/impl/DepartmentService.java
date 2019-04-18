package com.zm.hospital.service.impl;

import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.mapper.DepartmentMapper;
import com.zm.hospital.model.Department;
import com.zm.hospital.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 科室服务
 * Created by ange on 2016/11/16.
 */
@Service("departmentService")
public class DepartmentService extends BaseService<Department> implements IDepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 获得有分页的列表
     *
     * @param pageInfo 分页信息类
     */
    public void getList(PageInfo<Department> pageInfo) {
        //排序
        Map<String, String> orderby = new HashMap<String, String>();
        orderby.put("id", "desc");
        pageInfo.setOrderby(orderby);

        pageInfo.setTotal(departmentMapper.findPageCount(pageInfo));
        pageInfo.setList(departmentMapper.findPageCondition(pageInfo));
    }

    /**
     * 增加
     *
     * @param department 科室
     * @return 是否成功
     */
    public boolean add(Department department) {
        int insertid = departmentMapper.insertSelective(department);
        if (insertid <= 0) {
            throw new ServiceException("增加失败");
        }
        return insertid > 0;
    }

    /**
     * 更新信息
     *
     * @param department 科室
     * @return 是否成功
     */
    public boolean update(Department department) {
        int rows = departmentMapper.updateByPrimaryKeySelective(department);
        if (rows <= 0) {
            throw new ServiceException("修改失败!");
        }
        return rows > 0;
    }

    /**
     * 通过id找到该bean
     *
     * @param id id
     * @return bean
     */
    public Department findById(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id删除bean
     *
     * @param id id
     */
    public boolean deleteById(Integer id) {
        int rows = departmentMapper.deleteByPrimaryKey(id);
        if (rows <= 0) {
            throw new ServiceException("删除失败!");
        }
        return rows > 0;
    }


    /*以下方法特有*/

    public List<Department> getAllList() {
        return departmentMapper.getAllDepartments();
    }

}

