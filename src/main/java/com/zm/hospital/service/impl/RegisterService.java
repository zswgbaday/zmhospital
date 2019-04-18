package com.zm.hospital.service.impl;

import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.mapper.RegisterMapper;
import com.zm.hospital.model.Register;
import com.zm.hospital.model.bo.RegisterBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 挂号服务
 * Created by ange on 2016/11/17.
 */
@Service("registerService")
public class RegisterService extends BaseService<Register> implements IRegisterService {

    private final RegisterMapper registerMapper;

    @Autowired
    public RegisterService(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

    /**
     * BO分页
     *
     * @param pageInfo 分页信息类
     */
    public void getListBo(PageInfo<RegisterBo> pageInfo) {
        //排序
        Map<String, String> orderby = new HashMap<String, String>();
        orderby.put("id", "desc");
        pageInfo.setOrderby(orderby);

        pageInfo.setTotal(registerMapper.findPageCount(pageInfo));
        pageInfo.setList(registerMapper.findPageCondition(pageInfo));
    }

    /**
     * 通过id找到挂号BO
     *
     * @param id id
     * @return
     */
    public RegisterBo findBoById(Integer id) {
        return registerMapper.findBoById(id);
    }

    /**
     * 获得有分页的列表
     *
     * @param pageInfo 分页信息类
     */
    public void getList(PageInfo<Register> pageInfo) {

    }

    /**
     * 增加
     *
     * @param register
     * @return 是否成功
     */
    public boolean add(Register register) {
        int rows = registerMapper.insertSelective(register);
        if (rows <= 0) {
            throw new ServiceException("增加失败");
        }
        return rows > 0;
    }

    /**
     * 更新信息
     *
     * @param register
     * @return 是否成功
     */
    public boolean update(Register register) {
        int rows = registerMapper.updateByPrimaryKeySelective(register);
        if (rows <= 0) {
            throw new ServiceException("修改失败!");
        }
        return rows > 0;
    }

    /**
     * 通过id找到该bean
     *
     * @param id id
     * @return bean
     */
    public Register findById(Integer id) {
        return registerMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id删除bean
     *
     * @param id id
     */
    public boolean deleteById(Integer id) {
        int rows = registerMapper.deleteByPrimaryKey(id);
        if (rows <= 0) {
            throw new ServiceException("删除失败!");
        }
        return rows > 0;
    }

            /*以下方法特有*/

}
