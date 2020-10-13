package com.gql.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gql
 * @date 2020/7/31
 **/
public class TokenData  implements Serializable {
    private String id;// 令牌id
    private String subject;// 客户标识（用户名、账号）
    private String issuer;// 签发者
    private Date issuedAt;// 签发时间
    private String audience;// 接收方
    private String roles;// 访问主张-角色
    private String perms;// 访问主张-资源
    private String host;// 客户地址
    private Date expiration;//过期时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
