package com.zm.hospital.common.result;

/**
 * 这个类的主要作用是打印它的变量到视图的select中去
 * 公用,减少麻烦
 * 封装到select中,可以共用
 * Created by Administrator on 2016-09-30.
 */
public class Option {

    private String selected;//选中的字符串,selected或者为空
    private String value; //option的值
    private String name; //option名字

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
