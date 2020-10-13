package com.gql.common.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * @author gql
 * @date 2020/8/1
 **/
public class PrincipalDetails implements UserDetails {
    // 用户标识
    private String id;
    // 用户登录账号
    private String account;
    // 用户密码
    private String password;
    // 用户名称
    private String name;
    // 用户所属部门标识
    private String unitId;
    // 用户所属部门名称
    private String unitName;
    // 是否需要修改密码
    private boolean needChangePwd;
    // 账号是否未过期
    private boolean accountNonExpired;
    // 账号是否未锁定
    private boolean accountNonLocked;
    // 账号密码是否未过期
    private boolean credentialsNonExpired;
    // 账号是否激活
    private boolean enabled;
    // 角色集合
    private Collection<String> roles;
    // 用户权限集合
    private Collection<GrantedAuthority> authorities;

    /**
     * <p>初始化一个用户身份标识实例。</p>
     *
     * @param id                    用户标识
     * @param account               用户登录账号
     * @param password              用户密码
     * @param name                  用户名称
     * @param unitId                用户所属部门标识
     * @param unitName              用户所属部门名称
     * @param needChangePwd         是否需要修改密码
     * @param accountNonExpired     账号是否未过期
     * @param accountNonLocked      账号是否未锁定
     * @param credentialsNonExpired 账号密码是否未过期
     * @param enabled               账号是否激活
     * @param roles                  角色集合
     * @param authorities           权限集合
     */
    public PrincipalDetails( final String id, final String account, final String password, final String name, final String unitId, final String unitName, final boolean needChangePwd, final boolean accountNonExpired,
                             final boolean accountNonLocked, final boolean credentialsNonExpired, final boolean enabled, final Collection<String> roles, final Collection<GrantedAuthority> authorities )
    {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.unitId = unitId;
        this.unitName = unitName;
        this.needChangePwd = needChangePwd;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.roles = roles;
        this.authorities = authorities;
    }

    /**
     * <p>获取用户权限集合。</p>
     *
     * @return 用户权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    /**
     * <p>获取用户标识</p>
     *
     * @return 用户标识
     */
    public String getId()
    {
        return id;
    }

    /**
     * <p>获取用户账号</p>
     *
     * @return 用户账号
     */
    @Override
    public String getUsername()
    {
        return account;
    }

    /**
     * <p>获取用户密码</p>
     *
     * @return 用户密码
     */
    @Override
    public String getPassword()
    {
        return password;
    }

    /**
     * <p>获取用户名称</p>
     *
     * @return 用户名称
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>获取用户所属部门标识</p>
     *
     * @return 用户所属部门标识
     */
    public String getUnitId()
    {
        return unitId;
    }

    /**
     * <p>获取用户所属部门名称</p>
     *
     * @return 用户所属部门名称
     */
    public String getUnitName()
    {
        return unitName;
    }

    /**
     * <p>获取是否需要修改密码。</p>
     * @return 是否需要修改密码
     */
    public boolean isNeedChangePwd()
    {
        return needChangePwd;
    }

    /**
     * <p>账号是否未过期</p>
     *
     * @return 账号是否未过期
     */
    @Override
    public boolean isAccountNonExpired()
    {
        return accountNonExpired;
    }

    /**
     * <p>账号是否未锁定</p>
     *
     * @return 账号是否未锁定
     */
    @Override
    public boolean isAccountNonLocked()
    {
        return accountNonLocked;
    }

    /**
     * <p>账号密码是否未过期</p>
     *
     * @return 账号密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired()
    {
        return credentialsNonExpired;
    }

    /**
     * <p>账号是否激活</p>
     *
     * @return 账号是否激活
     */
    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    public Collection<String> getRoles()
    {
        return roles;
    }

    public boolean canAccessLink( String url )
    {
        if( null == url || "" == url )
            return false;
        if( this.authorities == null || this.authorities.isEmpty() )
            return false;
        boolean isAccess = false;
        for( GrantedAuthority grantedAuthority : this.authorities )
        {
            PrincipalAuthority authority = (PrincipalAuthority)grantedAuthority;
            String authorityUrl = authority.getUrl();
            AntPathMatcher matcher = new AntPathMatcher();
            isAccess = matcher.match( authorityUrl, url );
            if( isAccess )
                break;
        }
        return isAccess;
    }
}
