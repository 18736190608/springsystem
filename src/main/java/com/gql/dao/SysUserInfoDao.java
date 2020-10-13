package com.gql.dao;

import com.gql.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gql
 * @date 2020/8/1
 **/
@Repository
public interface SysUserInfoDao {

    UserInfoEntity getUserInfo(@Param("username") String username);

    UserInfoEntity getRoleUser(String username);

    void userRegister(UserInfoEntity userInfoEntity);

    List<UserInfoEntity> getAllUserInfo(int pageIndex, int offset);

}
