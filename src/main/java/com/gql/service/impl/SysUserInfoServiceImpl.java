package com.gql.service.impl;

import com.gql.config.TokenProperties;
import com.gql.dao.SysUserInfoDao;
import com.gql.entity.UserInfoEntity;
import com.gql.response.Result;
import com.gql.service.SysUserInfoService;
import com.gql.utils.JwtUtils;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gql
 * @date 2020/8/1
 **/
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserInfoServiceImpl.class);
    @Autowired
    private SysUserInfoDao sysUserInfoDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProperties tokenProperties;
    private long expiration;

    @Override
    public UserInfoEntity getSysUserInfo(String username) {

        UserInfoEntity userInfo = null;
        try {
            userInfo = sysUserInfoDao.getUserInfo(username);
        } catch (Exception e) {
            LOGGER.error("get userinfo exception", e);
        }
        return userInfo;
    }

    @Override
    public UserInfoEntity getRoleUserInfo(String username) {
        return sysUserInfoDao.getRoleUser(username);
    }


    @Override
    public Result login(String username, String password) throws Exception {

        expiration = 1000 * 60 * 60 * 24;
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserInfoEntity sysUserInfo = this.getSysUserInfo(username);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String jwtToken = JwtUtils.createToken(tokenProperties.getSecret(), sysUserInfo.getUsername(), sysUserInfo.getPassword(), expiration, "", "", "", SignatureAlgorithm.HS256);
        resultMap.put("token", jwtToken);
        resultMap.put("username", username);
        return Result.success(resultMap);
    }

    @Override
    public void register(UserInfoEntity userInfoEntity) {
        UserInfoEntity userInfo = null;
        try {
            userInfo = sysUserInfoDao.getUserInfo(userInfoEntity.getUsername());
            if (userInfo != null) {
                sysUserInfoDao.userRegister(userInfoEntity);
            }
        } catch (Exception e) {
            LOGGER.error("insert error", e);
        }

    }

    @Override
    public List<UserInfoEntity> getAllUserInfo(int pageIndex, int pageSize) throws Exception {

        int offset = 0;
        int preIndex = 0;
        if (pageSize == 0) {
            pageSize = 20;
        }
         offset = pageSize * pageIndex;
         preIndex = offset - pageSize;
        return sysUserInfoDao.getAllUserInfo(preIndex, offset);

    }
}
