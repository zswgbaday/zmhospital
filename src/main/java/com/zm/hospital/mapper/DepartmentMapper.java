package com.zm.hospital.mapper;

import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Department;
import com.zm.hospital.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    /**
     * 获得经过分页限制和查询条件的列表
     * @param pageInfo 分页信息
     * @return 列表
     */
    List<Department> findPageCondition(PageInfo pageInfo);

    /**
     * 获得数量
     * @param pageInfo 分页信息
     * @return 总数
     */
    int findPageCount(PageInfo pageInfo);

    /**
     * 获得所有科室列表
     *
     * @return
     */
    List<Department> getAllDepartments();
}