package com.gql.service.impl;

import com.gql.entity.RoleEntity;
import com.gql.entity.UserInfoEntity;
import com.gql.service.SysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gql
 * @date 2020/8/1
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
   private SysUserInfoService sysUserInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfoEntity roleUserInfo = sysUserInfoService.getRoleUserInfo(username);
        if (roleUserInfo != null) {
            List<RoleEntity> roleList = roleUserInfo.getRoleList();

            List<SimpleGrantedAuthority> list = new ArrayList<>();
            for (RoleEntity val : roleList){

                list.add(new SimpleGrantedAuthority(val.getRoleName()));
            }
            UserDetails userDetails = new User(roleUserInfo.getUsername(),roleUserInfo.getPassword(),list);
            return userDetails;
        }

        return null;
    }
}
