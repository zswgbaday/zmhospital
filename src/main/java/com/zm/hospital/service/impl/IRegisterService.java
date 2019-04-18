package com.zm.hospital.service.impl;

import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.model.Register;
import com.zm.hospital.model.bo.RegisterBo;
import com.zm.hospital.service.IBaseService;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by ange on 2016/11/17.
 */
public interface IRegisterService extends IBaseService<Register>{
    /**
     * BO分页
     *
     * @param pageInfo 分页信息类
     */
     void getListBo(PageInfo<RegisterBo> pageInfo);

    /**
     * 通过id找到挂号BO
     * @param id
     * @return
     */
    RegisterBo findBoById(Integer id);
}
