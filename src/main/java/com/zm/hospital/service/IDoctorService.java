package com.zm.hospital.service;

import com.zm.hospital.model.Doctor;

import java.util.List;

/**
 *
 * Created by Administrator on 2016-11-17.
 */
public interface IDoctorService extends IBaseService<Doctor>{

    /**
     * 根据科室id获得该所有的医生
     * @return
     */
    List<Doctor> getDoctorsByDepartId(Integer departmentId);
}
