package com.zm.hospital.model;

import java.io.Serializable;

public class RoleResource implements Serializable {
    private Integer roleId;

    private Integer resourceId;

    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", roleId=" + roleId +
                ", resourceId=" + resourceId +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}