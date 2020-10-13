package com.gql.service;

import com.gql.entity.UserInfoEntity;
import com.gql.response.Result;

import java.util.List;

/**
 * @author gql
 * @date 2020/8/1
 **/
public interface SysUserInfoService {
    /**
     * 获取user信息
     * @param username
     * @return
     */
    UserInfoEntity getSysUserInfo(String username);

    /**
     * 获取权限信息
     * @param username
     * @return
     */
    UserInfoEntity getRoleUserInfo(String username);

    /**
     * 登陆请求
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public Result login(String username, String password) throws Exception;

    /**
     * 用户注册
     * @param userInfoEntity
     */
    public void register(UserInfoEntity userInfoEntity);

    /**
     * 所有用户
     * @param pageIndex
     * @param offset
     * @return
     * @throws Exception
     */
    List<UserInfoEntity> getAllUserInfo(int pageIndex, int offset) throws Exception;
}
