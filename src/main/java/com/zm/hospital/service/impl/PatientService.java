package com.zm.hospital.service.impl;

import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.mapper.PatientMapper;
import com.zm.hospital.model.Patient;
import com.zm.hospital.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 病人服务
 * Created by Administrator on 2016-11-16.
 */
@Service("patientService")
public class PatientService extends BaseService<Patient> implements IPatientService {

    private final PatientMapper patientMapper;

    @Autowired
    public PatientService(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    public void getList(PageInfo<Patient> pageInfo) {
        //排序
        Map<String, String> orderby = new HashMap<String, String>();
        orderby.put("id", "desc");
        pageInfo.setOrderby(orderby);

        pageInfo.setTotal(patientMapper.findPageCount(pageInfo));
        pageInfo.setList(patientMapper.findPageCondition(pageInfo));
    }

    public boolean add(Patient patient) {
        int insertid = patientMapper.insertSelective(patient);
        if (insertid <= 0) {
            throw new ServiceException("增加失败");
        }
        return insertid > 0;
    }

    public boolean update(Patient patient) {
        int rows = patientMapper.updateByPrimaryKeySelective(patient);
        if (rows <= 0) {
            throw new ServiceException("修改失败!");
        }
        return rows > 0;
    }

    public Patient findById(Integer id) {
        return patientMapper.selectByPrimaryKey(id);
    }

    public boolean deleteById(Integer id) {
        int rows = patientMapper.deleteByPrimaryKey(id);
        if (rows <= 0) {
            throw new ServiceException("删除失败!");
        }
        return rows > 0;
    }

    /*以下方法特有*/

}
