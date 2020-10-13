package com.gql.utils;

import com.gql.common.model.PrincipalDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>安全上下文静态工具类。</p>
 *
 * @author gql
 */
public final class SecurityContext {
    private SecurityContext() {
    }

    /**
     * <p>取得当前用户的身份标识，在未验证的情况下返回  <code>null</code>。</p>
     *
     * @return 当前用户的身份标识
     */
    public static PrincipalDetails getPrincipal() {
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }

    /**
     * <p>判断当前会话是否是一个已验证的会话。</p>
     *
     * @return <code>true</code> 表示是一个已验证会话，否则返回 <code>false</code>
     */
    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() == null ? false : SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }
}
