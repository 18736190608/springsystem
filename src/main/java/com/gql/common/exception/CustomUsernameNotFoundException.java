package com.gql.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author gql
 * @date 2020/7/31
 **/
public class CustomUsernameNotFoundException extends AuthenticationException {
    public CustomUsernameNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomUsernameNotFoundException(String msg) {
        super(msg);
    }
}
