package com.zm.hospital.common.utils;

import java.util.List;
import java.util.Map;

/**
 * 分页信息类
 * Created by ange on 2016/9/17.
 */
public class PageInfo<T> {

    private final static int PAGESIZE = 10; //默认显示的记录数

    private int total; // 总记录数量
    private List<T> list; //显示的记录

    private int from;//数据库sql语句查询用,开始记录

    private int size;//数据库sql语句查询用,查询多少

    private int nowpage; // 当前页

    private int pagesize; // 每页显示的记录数

    private Map<String, Object> condition; //查询条件

    private Map<String,String> orderby;//排序

    private int totalPage;//总页数,根据总记录数,每页显示数计算

    public PageInfo() {
    }

    //构造方法
    public PageInfo(Integer nowpage, Integer pagesize) {
        if (nowpage != null) {
            //计算当前页
            if (nowpage <= 0) {
                this.nowpage = 1;
            } else {
                //当前页
                this.nowpage = nowpage;
            }
        } else {
            this.nowpage = 1;
        }

        if (pagesize != null) {
            //记录每页显示的记录数
            if (pagesize < 1) {
                this.pagesize = PAGESIZE;
            } else {
                this.pagesize = pagesize;
            }
        } else {
            this.pagesize = PAGESIZE;
        }

        //计算开始的记录和结束的记录
        this.from = (this.nowpage - 1) * this.pagesize;
        this.size = this.pagesize;
    }

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return (total + pagesize - 1) / pagesize;
    }

    public Map<String, String> getOrderby() {
        return orderby;
    }

    public void setOrderby(Map<String, String> orderby) {
        this.orderby = orderby;
    }
}
