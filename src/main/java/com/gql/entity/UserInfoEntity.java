package com.gql.entity;

import com.gql.baseabstract.BaseEntity;

import java.util.List;

/**
 * @author gql
 * @date 2020/7/28
 **/
public class UserInfoEntity extends BaseEntity {


    private String username;
    private String password;
    private String email;
    private String mobile;
    private int enabled;
    private int isOline;
    private int delFlag;

    private List<RoleEntity> roleList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getIsOline() {
        return isOline;
    }

    public void setIsOline(int isOline) {
        this.isOline = isOline;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public List<RoleEntity> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleEntity> roleList) {
        this.roleList = roleList;
    }
}
