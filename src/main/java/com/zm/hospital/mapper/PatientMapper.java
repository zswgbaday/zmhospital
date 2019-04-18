package com.zm.hospital.mapper;

import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Patient;
import com.zm.hospital.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Patient record);

    /**
     * 获得经过分页限制和查询条件的列表
     * @param pageInfo 分页信息
     * @return 列表
     */
    List<Patient> findPageCondition(PageInfo pageInfo);

    /**
     * 获得数量
     * @param pageInfo 分页信息
     * @return 总数
     */
    int findPageCount(PageInfo pageInfo);
}