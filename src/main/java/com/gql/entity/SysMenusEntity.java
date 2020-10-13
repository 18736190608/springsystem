package com.gql.entity;

import com.gql.baseabstract.BaseEntity;

import java.util.List;

/**
 * @author gql
 * @date 2020/8/13
 **/
public class SysMenusEntity extends BaseEntity {

    private Integer parentId;
    private String menuName;
    private String menuType;
    private String menuUrl;

    List<SysMenusEntity> children;

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public List<SysMenusEntity> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenusEntity> children) {
        this.children = children;
    }
}
