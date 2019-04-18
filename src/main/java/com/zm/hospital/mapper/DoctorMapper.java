package com.zm.hospital.mapper;

import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Department;
import com.zm.hospital.model.Doctor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Doctor record);

    /**
     * 获得经过分页限制和查询条件的列表
     * @param pageInfo 分页信息
     * @return 列表
     */
    List<Doctor> findPageCondition(PageInfo pageInfo);

    /**
     * 获得数量
     * @param pageInfo 分页信息
     * @return 总数
     */
    int findPageCount(PageInfo pageInfo);

    /**
     * 根据科室id获得该所有的医生
     * @return 医生list
     */
    List<Doctor> findDoctorsByDepartId(@Param("departmentId") Integer departmentId);
}