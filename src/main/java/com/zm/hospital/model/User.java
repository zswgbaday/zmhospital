package com.zm.hospital.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class User implements Serializable {
    private Integer id;

    private String loginname;

    private String email;

    private String name;

    private String password;

    private Byte sex;

    private Byte age;

    private Integer usertype;

    private Byte status;

    private Integer organizationId;

    private Date createdate;

    private String phone;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

//自定义方法

    /**
     * 判断用户是否超级管理员
     *
     * @return
     */
    public boolean isSuperAdmin() {
        return this.usertype == 1;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", loginname=" + loginname +
                ", email=" + email +
                ", name=" + name +
                ", password=" + password +
                ", sex=" + sex +
                ", age=" + age +
                ", usertype=" + usertype +
                ", status=" + status +
                ", organizationId=" + organizationId +
                ", createdate=" + createdate +
                ", phone=" + phone +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}