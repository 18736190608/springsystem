package com.gql.config;

import com.gql.filter.AuthenticationTokenFilter;
import com.gql.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * security 配置类
 *
 * @author gql
 * @date 2020/7/28
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //   加密方式
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public AuthenticationTokenFilter authenticationTokenFilter() throws Exception {
        return new AuthenticationTokenFilter();
    }




    @Bean
    public AuthenticationTokenFilter setAuthenticationToken() {
        return new AuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);  // 先关闭跨域
        http.authorizeRequests().
                antMatchers("/user/role").hasAnyRole("ADMIN", "support").
                anyRequest().permitAll()  //  忽略其它所有的请求
                /*anyRequest().authenticated()*/
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");  //其它请求需要登陆状态
        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/static/favicon.ico", "/api/**","/validata/code", "/picture/**", "/layui/**", "/user/login", "/login", "/register/**", "/css/**", "/static/**", "/js/**", "/images/**");
        web.ignoring().regexMatchers("/^api/");
    }


    public static void main(String[] args) {
        String reg = "^/api.*";

        String srt = "/api/index";

        boolean matches = srt.matches(reg);
        System.out.println(matches);
    }
}
