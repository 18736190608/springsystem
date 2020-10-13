package com.gql.config;

import com.gql.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author gql
 * @date 2020/8/15
 **/
@Configuration
public   class AuthenticationConfigurationConfig extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsS​​ervice;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsS​​ervice).passwordEncoder(passwordEncoder);
    }
}
