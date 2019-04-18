package com.zm.hospital.common.result;

import java.util.List;

/**
 * 菜单
 * Created by ange on 2016/11/14.
 */
public class Menu implements java.io.Serializable {

    private int depth;//深度,第几级
    private String name;//名字
    private String url;//链接

    private List<Menu> subMenus;//子菜单

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }
}
