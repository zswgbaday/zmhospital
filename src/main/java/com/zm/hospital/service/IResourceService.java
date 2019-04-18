package com.zm.hospital.service;

import com.zm.hospital.common.result.Menu;
import com.zm.hospital.common.result.Node;
import com.zm.hospital.model.Resource;

import java.util.List;
import java.util.Set;

/**
 * 资源接口
 * Created by Administrator on 2016-09-30.
 */
public interface IResourceService extends IBaseService<Resource>{

    /**
     * 获得所有的资源列表
     * @return
     */
    List<Resource> getAllResourceList();

    /**
     * 找到普通列表的树
     * @param resources
     * @return
     */
    List<Node<Resource>> findALLTree(List<Resource> resources);

    /**
     * 根据角色id查询该角色所拥有的资源id列表
     * @param roleId
     * @return
     */
    List<Integer> findResourceIdListByRoleId(Integer roleId);

    /**
     * 根据角色id查询角色所拥有的资源列表
     * @param roleId
     * @return
     */
    List<Resource> findResourceListByRoleId(Integer roleId);

    /**
     * 根据用户id找到他所拥有的所有资源
     *
     * @param userId 用户id
     * @return
     */
    List<Resource> findResourcesByUserId(Integer userId);

    /**
     * 根据用户id找到该用户所拥有的所有菜单资源
     *
     * @param userId 用户id
     * @return
     */
    List<Resource> findMenusByUserId(Integer userId);

    /**
     * 获得所有菜单资源[超级管理员用]
     *
     * @return
     */
    List<Resource> findMenusAll();

    /**
     * 将资源list转化为menu的list
     *
     * @param allResources 资源list
     * @return 菜单list
     */
    List<Menu> findAllMenu(List<Resource> allResources);

    /**
     * 从资源实体列表中提取出各个资源的url,组成Set集合
     *
     * @param resources 资源list
     * @return
     */
    Set<String> getResourcesUrlSet(List<Resource> resources);

    /**
     * 一键创建某个控制器的增删查改url
     *
     * @param controller_name 控制器名称
     */
    void autoCreateCRUD(String controller_name,String descript);
}
