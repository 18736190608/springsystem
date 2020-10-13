package com.gql.common.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author gql
 * @date 2020/8/1
 **/
public class PrincipalAuthority implements GrantedAuthority {

    private String authority;
    private String url;
    private String permission;

    public PrincipalAuthority(String url, String permission)
    {
        this.url = url;
        this.permission = permission;
    }

    @Override
    public String getAuthority()
    {
        return authority;
    }

    public String getUrl()
    {
        return url;
    }

    public String getPermission()
    {
        return permission;
    }

}
