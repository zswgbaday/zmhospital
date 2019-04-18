package com.zm.hospital.service;

import com.zm.hospital.common.utils.PageInfo;

/**
 * 基础服务
 * Created by ange on 2016/9/26.
 */
public interface IBaseService<T> {
    
    /**
     * 获得有分页的列表
     * @param pageInfo 分页信息类
     */
    void getList(PageInfo<T> pageInfo);

    /**
     * 增加
     * @return 是否成功
     */
    boolean add(T t);

    /**
     * 更新信息
     * @param t
     * @return 是否成功
     */
    boolean update(T t);

    /**
     * 通过id找到该bean
     * @param id id
     * @return bean
     */
    T findById(Integer id);

    /**
     * 根据id删除bean
     *
     * @param id id
     */
    boolean deleteById(Integer id);
}
