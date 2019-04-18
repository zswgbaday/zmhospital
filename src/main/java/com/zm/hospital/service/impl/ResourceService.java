package com.zm.hospital.service.impl;

import com.zm.hospital.common.exception.ServiceException;
import com.zm.hospital.common.result.Menu;
import com.zm.hospital.common.result.Node;
import com.zm.hospital.common.utils.PageInfo;
import com.zm.hospital.mapper.ResourceMapper;
import com.zm.hospital.mapper.RoleResourceMapper;
import com.zm.hospital.model.Resource;
import com.zm.hospital.service.IResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 资源服务
 * Created by Administrator on 2016-09-30.
 */
@Service("resourceService")
public class ResourceService extends BaseService<Resource> implements IResourceService {

    private final ResourceMapper resourceMapper;

    private final RoleResourceMapper roleResourceMapper;

    @Autowired
    public ResourceService(ResourceMapper resourceMapper, RoleResourceMapper roleResourceMapper) {
        this.resourceMapper = resourceMapper;
        this.roleResourceMapper = roleResourceMapper;
    }

    public void getList(PageInfo<Resource> pageInfo) {
        //排序
        Map<String, String> orderby = new HashMap<String, String>();
        orderby.put("id", "desc");
        pageInfo.setOrderby(orderby);

        pageInfo.setTotal(resourceMapper.findPageCount(pageInfo));
        pageInfo.setList(resourceMapper.findPageCondition(pageInfo));
    }

    public boolean add(Resource resource) {
        int insertid = resourceMapper.insertSelective(resource);
        if (insertid <= 0) {
            throw new ServiceException("增加失败!");
        }
        return insertid > 0;
    }

    public boolean update(Resource resource) {
        int rows = resourceMapper.updateByPrimaryKeySelective(resource);
        if (rows < 0) {
            throw new ServiceException("修改失败!");
        }
        return rows > 0;
    }

    public Resource findById(Integer id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    public boolean deleteById(Integer id) {
        int rows = resourceMapper.deleteByPrimaryKey(id);
        if (rows <= 0) {
            throw new ServiceException("删除失败!");
        }
        return rows > 0;
    }

    public List<Resource> getAllResourceList() {
        return resourceMapper.getAllResourceList();
    }

    /**
     * 从所有资源中找到(所有这个父资源的子资源),并且从所有资源中移除(所有这个父资源的子资源)并排序
     *
     * @param pResource
     * @param allList
     * @return
     */
    private List<Resource> getChilds(Resource pResource, List<Resource> allList) {
        List<Resource> childList = new ArrayList<Resource>();

        Iterator<Resource> it = allList.iterator();
        while (it.hasNext()) {
            Resource resource1 = it.next();
            if (resource1.getPid().equals(pResource.getId())) {
                childList.add(resource1);
                it.remove();//从(所有列表)中移除
            }
        }

        //根据字段的seq排序
        Collections.sort(childList, new Comparator<Resource>() {
            /**
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             * */
            public int compare(Resource o1, Resource o2) {
                if (o1.getSeq() > o2.getSeq()) {
                    return 1;
                } else if (o1.getSeq().equals(o2.getSeq())) {
                    return 0;
                }
                return -1;
            }
        });

        return childList;
    }

    /**
     * 获得资源列表中顶级父资源id
     *
     * @param resources
     * @return
     */
    private int findTopParentId(List<Resource> resources) {
        int max = 999999;
        for (Resource resource : resources) {
            int pid = resource.getPid();
            if (pid < max) {
                max = pid;
            }
        }
        return max;
    }

    public List<Node<Resource>> findALLTree(List<Resource> resources) {
        //要返回的新列表
        List<Node<Resource>> retList = new ArrayList<Node<Resource>>();
        if (resources.size() > 0) {//有数据再进行操作
            //根资源
            Resource resource = new Resource();
            int topId = findTopParentId(resources);
            resource.setId(topId);
            //处理节点
            handleResourceNode(resource, resources, retList, 1);
        }
        return retList;
    }

    /**
     * 处理节点
     *
     * @param pResource 根资源
     * @param allList   所有列表
     * @param nodeList  目前的node列表
     * @param depth     当前深度
     */
    private void handleResourceNode(Resource pResource, List<Resource> allList, List<Node<Resource>> nodeList, int depth) {
        //找到它的所有子节点
        List<Resource> childList = getChilds(pResource, allList);

        //
        for (Resource resource1 : childList) {
            //新建节点
            Node<Resource> node = new Node<Resource>();
            node.setItem(resource1);//绑定对象
            node.setDepth(depth);//设置深度
            //加入
            nodeList.add(node);

            handleResourceNode(resource1, allList, nodeList, depth + 1);
        }
    }


    public List<Menu> findAllMenu(List<Resource> allResources) {
        //新建父资源
        Resource resource1 = new Resource();
        resource1.setId(0);

        //找到这个父资源的所有子节点
        List<Resource> childList = getChilds(resource1, allResources);

        //最终菜单
        List<Menu> menus = handleSubMenu(childList, allResources, 1);
        return menus;
    }

    /**
     * @param pResources 父资源list
     * @param allList    所有资源list
     * @param depth      深度
     * @return
     */
    private List<Menu> handleSubMenu(List<Resource> pResources, List<Resource> allList, int depth) {
        //将为父资源list的每一个父资源都创建一个菜单,然后加入menus
        List<Menu> menus = new ArrayList<Menu>();

        for (Resource resource1 : pResources) {
            //创建新菜单

            Menu menu = new Menu();
            menu.setDepth(depth);
            menu.setName(resource1.getName());
            menu.setUrl(resource1.getUrl());
            menus.add(menu);

            //找到这个父资源的所有子节点
            List<Resource> childList = getChilds(resource1, allList);

            //设置子菜单
            if (childList.size() > 0) {
                List<Menu> childMenus = handleSubMenu(childList, allList, depth + 1);//把子节点资源当作父资源重新载入
                menu.setSubMenus(childMenus);
            }
        }
        return menus;
    }

    public List<Integer> findResourceIdListByRoleId(Integer roleId) {
        return roleResourceMapper.findResourcesByRoleId(roleId);
    }

    /**
     * 根据角色id查询角色所拥有的资源列表
     *
     * @param roleId 角色id
     * @return
     */
    public List<Resource> findResourceListByRoleId(Integer roleId) {
        return roleResourceMapper.findResourcesEntityByRoleId(roleId);
    }

    public List<Resource> findResourcesByUserId(Integer userId) {
        return resourceMapper.getUserResources(userId);
    }

    /**
     * 根据用户id找到该用户所拥有的所有菜单资源
     *
     * @param userId 用户id
     * @return
     */
    public List<Resource> findMenusByUserId(Integer userId) {
        return resourceMapper.getUserMenus(userId);
    }

    /**
     * 获得所有菜单资源[超级管理员用]
     *
     * @return
     */
    public List<Resource> findMenusAll() {
        return resourceMapper.getAllMenus();
    }

    /**
     * 从资源实体列表中提取出各个资源的url,组成Set集合
     *
     * @param resources 资源list
     * @return
     */
    public Set<String> getResourcesUrlSet(List<Resource> resources) {
        Set<String> urlSet = new HashSet<String>();
        for (Resource resource : resources) {
            String url = resource.getUrl();
            if (StringUtils.isNoneBlank(url)) {
                urlSet.add(url);
            }
        }
        return urlSet;
    }

    /**
     * 一键创建某个控制器的增删查改url
     *
     * @param controller_name 控制器名称
     */
    public void autoCreateCRUD(String controller_name, String descript) {
        Resource resource = new Resource();
        resource.setUrl("/" + controller_name + "");
        resource.setName(descript + "管理");
        resource.setCreateTime(new Date());
        resource.setDescription("");
        resource.setSeq(0);
        resource.setStatus((byte) 1);
        resource.setPid(0);
        resource.setTypeId(1);//菜单
        int rows = resourceMapper.insertSelective(resource);
        if (rows <= 0) {
            throw new ServiceException("创建管理失败!");
        }

        int last_insert_id = resource.getId();

        resource.setPid(last_insert_id);

        resource.setId(null);
        resource.setUrl("/" + controller_name + "/list");
        resource.setName(descript + "列表");
        resource.setTypeId(1);//菜单
        rows = resourceMapper.insertSelective(resource);
        if (rows <= 0) {
            throw new ServiceException("创建list失败!");
        }

        resource.setId(null);
        resource.setUrl("/" + controller_name + "/add");
        resource.setName(descript + "新建");
        resource.setTypeId(1);//菜单
        rows = resourceMapper.insertSelective(resource);
        if (rows <= 0) {
            throw new ServiceException("创建add失败!");
        }

        resource.setId(null);
        resource.setUrl("/" + controller_name + "/doadd");
        resource.setName("处理新建");
        resource.setTypeId(3);//ajax
        rows = resourceMapper.insertSelective(resource);
        if (rows <= 0) {
            throw new ServiceException("创建dooadd失败!");
        }

        resource.setId(null);
        resource.setUrl("/" + controller_name + "/edit");
        resource.setName(descript + "编辑");
        resource.setTypeId(4);//页面
        rows = resourceMapper.insertSelective(resource);
        if (rows <= 0) {
            throw new ServiceException("创建edit失败!");
        }

        resource.setId(null);
        resource.setUrl("/" + controller_name + "/doedit");
        resource.setName("处理编辑");
        resource.setTypeId(3);//ajax
        rows = resourceMapper.insertSelective(resource);
        if (rows <= 0) {
            throw new ServiceException("创建doedit失败!");
        }

        resource.setId(null);
        resource.setUrl("/" + controller_name + "/delete");
        resource.setName(descript + "删除");
        resource.setTypeId(3);//ajax
        rows = resourceMapper.insertSelective(resource);
        if (rows <= 0) {
            throw new ServiceException("创建doedit失败!");
        }
    }
}
