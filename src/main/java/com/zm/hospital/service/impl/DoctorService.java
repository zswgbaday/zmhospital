package com.zm.hospital.service.impl;

import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.mapper.DoctorMapper;
import com.zm.hospital.model.Doctor;
import com.zm.hospital.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生服务
 * Created by Administrator on 2016-11-17.
 */
@Service("doctorService")
public class DoctorService extends BaseService<Doctor> implements IDoctorService {

    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorService(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    /**
     * 获得有分页的列表
     *
     * @param pageInfo 分页信息类
     */
    public void getList(PageInfo<Doctor> pageInfo) {
        //排序
        Map<String, String> orderby = new HashMap<String, String>();
        orderby.put("id", "desc");
        pageInfo.setOrderby(orderby);

        pageInfo.setTotal(doctorMapper.findPageCount(pageInfo));
        pageInfo.setList(doctorMapper.findPageCondition(pageInfo));
    }


    public boolean add(Doctor doctor) {
        int rows = doctorMapper.insertSelective(doctor);
        if (rows <= 0) {
            throw new ServiceException("增加失败");
        }
        return rows > 0;
    }


    public boolean update(Doctor doctor) {
        int rows = doctorMapper.updateByPrimaryKeySelective(doctor);
        if (rows <= 0) {
            throw new ServiceException("修改失败!");
        }
        return rows > 0;
    }


    public Doctor findById(Integer id) {
        return doctorMapper.selectByPrimaryKey(id);
    }


    public boolean deleteById(Integer id) {
        int rows = doctorMapper.deleteByPrimaryKey(id);
        if (rows <= 0) {
            throw new ServiceException("删除失败!");
        }
        return rows > 0;
    }


    /*以下方法特有*/

    /**
     * 根据科室id获得该所有的医生
     *
     * @return
     */
    public List<Doctor> getDoctorsByDepartId(Integer departmentId) {
        return doctorMapper.findDoctorsByDepartId(departmentId);
    }
}
