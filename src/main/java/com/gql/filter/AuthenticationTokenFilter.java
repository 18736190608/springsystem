package com.gql.filter;

import com.gql.common.model.TokenData;
import com.gql.config.TokenProperties;
import com.gql.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gql
 * @date 2020/7/31
 **/
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String autheader = "";
        String url = request.getRequestURI();
        url = url.substring(request.getContextPath().length());

        if (! "/user/login".equals(url)) {
            autheader = request.getParameter(tokenProperties.getHeader());
            if ( autheader == null || "".equals(autheader)){
                autheader = request.getHeader(tokenProperties.getHeader());
            }
        }
        String tokenPrefix = tokenProperties.getTokenPrefix();
        if (StringUtils.isNotBlank(autheader)) {
            //String token = autheader.substring(tokenPrefix.length());

            if (!"".equals(autheader)) {
                if (JwtUtils.verity(autheader, tokenProperties.getSecret())) {
                    TokenData tokenData = JwtUtils.parseToken(autheader, tokenProperties.getSecret());
                    if (null != tokenData.getSubject() && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = this.userDetailsService.loadUserByUsername(tokenData.getSubject());
                        if (userDetails != null) {
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            logger.info("AuthenticationTokenFilter[doFilterInternal]  authenticated user " + tokenData.getSubject() + ", setting security context");
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }

                }
            }
        }
        filterChain.doFilter(request, response);

    }
}
