package com.zm.hospital.model.bo;

import com.zm.hospital.model.*;

/**
 * 挂号
 * Created by ange on 2016/11/18.
 */
public class RegisterBo extends Register {

    private Patient patient;
    private Doctor doctor;
    private Department department;
    private User user;


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
