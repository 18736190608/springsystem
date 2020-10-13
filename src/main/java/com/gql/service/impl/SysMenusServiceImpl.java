package com.gql.service.impl;

import com.gql.dao.SysRoleMenusDao;
import com.gql.entity.SysMenusEntity;
import com.gql.service.SysMenusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gql
 * @date 2020/8/13
 **/
@Service
public class SysMenusServiceImpl implements SysMenusService {
    private static Logger logger = LoggerFactory.getLogger(SysMenusServiceImpl.class);
    @Autowired
    private SysRoleMenusDao menusDao;

    @Override
    public List<Map<String, Object>> sysMenusName(String username) {
        List<Map<String, Object>> treeMap = new ArrayList<>();
        if (! "".equalsIgnoreCase(username)) {
            List<SysMenusEntity> roleMenus = menusDao.getRoleMenus(username);
            if (roleMenus != null && roleMenus.size() > 0) {
                List<Integer> rootIds = new ArrayList<Integer>();
                Map<Integer, Map<String, Object>> nodeMap = new HashMap<Integer, Map<String, Object>>();
                for (SysMenusEntity menus : roleMenus) {
                    Integer id = menus.getId();
                    Integer parentId = menus.getParentId();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", id);
                    map.put("parentId", parentId);
                    map.put("name", menus.getMenuName());
                    map.put("menuUrl",menus.getMenuUrl());
                    map.put("menuType", menus.getMenuType());
                    map.put("children", new ArrayList<Map<String, Object>>());
                    if (parentId == null) {
                        rootIds.add(id);
                    }
                    nodeMap.put(id, map);
                }
                for (SysMenusEntity sysDict : roleMenus) {
                    Integer id = sysDict.getId();
                    Integer parentId = sysDict.getParentId();
                    Map<String, Object> tempNodeMap = nodeMap.get(id);
                    if (parentId != null && parentId > 0) {
                        Map<String, Object> parentNode = nodeMap.get(parentId);
                        if (parentNode != null) {
                            ((List<Map<String, Object>>) parentNode.get("children")).add(tempNodeMap);
                        }
                    }
                }
                for (Integer rootId : rootIds) {
                    treeMap.add(nodeMap.get(rootId));
                }

            }
        }
        logger.info("sysmenus size treemap:{}",treeMap.size());
        return treeMap;
    }

    public static void main(String[] args) {
        Map<Integer, Map<String, Object>> nodeMap = new HashMap<Integer, Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "sadfasdf");
        map.put("parentId", "parentId");
        nodeMap.put(1, map);

        Map<String, Object> x = nodeMap.get(1);
        System.out.println(x);
        System.out.println();
    }
}
