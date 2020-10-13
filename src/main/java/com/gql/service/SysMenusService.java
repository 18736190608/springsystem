package com.gql.service;

import java.util.List;
import java.util.Map;

/**
 * @author gql
 * @date 2020/8/13
 **/
public interface SysMenusService {
    /**
     * 菜单列表
     * @param username
     * @return
     */
    List<Map<String,Object>> sysMenusName(String username);
}
