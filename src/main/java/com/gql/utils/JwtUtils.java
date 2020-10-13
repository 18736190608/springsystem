package com.gql.utils;

import com.gql.common.model.TokenData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.UUID;

/**
 * @author gql
 * @date 2020/7/30
 **/
public class JwtUtils {
    /**
     * token生成
     *
     * @param secret      密钥
     * @param subject     用户ID
     * @param issuer      签发人
     * @param period      有效时间(毫秒)
     * @param roles       访问主张-角色
     * @param permissions 访问主张-权限
     * @param algorithm   加密算法(SignatureAlgorithm是enum)
     * @return json web token
     */
    public static String createToken(String secret, String subject, String issuer, Long period, String host, String roles, String permissions, SignatureAlgorithm algorithm) {

        // 当前时间戳(精确到毫秒)
        long currentTimeMillis = System.currentTimeMillis();
        // 秘钥
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secret);
        JwtBuilder jwt = Jwts.builder();
        jwt.setId(UUID.randomUUID().toString());
        // 用户名
        jwt.setSubject(subject);
        // 签发者
        if (null != issuer && ! "".equals(issuer)) {
            jwt.setIssuer(issuer);
        }
        // 签发时间
        jwt.setIssuedAt(new Date(currentTimeMillis));
        // 有效时间
        if (null != period) {
            Date expiration = new Date(currentTimeMillis + period * 1000);
            jwt.setExpiration(expiration);
        }
        // 客户地址
        if (null != host && ! "".equals(host)) {
            jwt.claim("host", host);
        }
        // 访问主张-角色
        if (null != roles && ! "".equals(roles)) {
            jwt.claim("roles", roles);
        }
        // 访问主张-权限
        if (null != permissions && ! "".equals(permissions)) {
            jwt.claim("perms", permissions);
        }
        jwt.compressWith(CompressionCodecs.DEFLATE);
        jwt.signWith(algorithm, secretKeyBytes);
        return jwt.compact();
    }

    public static TokenData parseToken(String token, String secret) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).getBody();
        TokenData tokenData = new TokenData();
        tokenData.setId(claims.getId());// 令牌ID
        tokenData.setSubject(claims.getSubject());// 客户标识
        tokenData.setIssuer(claims.getIssuer());// 签发者
        tokenData.setIssuedAt(claims.getIssuedAt());// 签发时间
        tokenData.setAudience(claims.getAudience());// 接收方
        tokenData.setExpiration(claims.getExpiration());//过期时间
        tokenData.setHost(claims.get("host", String.class));//客户地址
        tokenData.setRoles(claims.get("roles", String.class));// 访问主张-角色
        tokenData.setPerms(claims.get("perms", String.class));// 访问主张-权限

        return tokenData;
    }

    /**
     * Token令牌有效性验证。
     *
     * @param token  令牌
     * @param secret 密钥
     * @return 令牌是否有效性（true：有效；false：无效）
     */
    public static boolean verity(String token, String secret) {
        try {
            TokenData tokenData = parseToken(token, secret);
            Date expiration = tokenData.getExpiration();
            return expiration.after(new Date());
        } catch (ExpiredJwtException ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        String token = JwtUtils.createToken("rtyui", "admin", "uadmin", 1000L, "", "admin", "", SignatureAlgorithm.HS256);
        System.out.println(token);
        boolean rtyui1 = JwtUtils.verity(token, "rtyui");
        System.out.println(rtyui1);
        TokenData rtyui = JwtUtils.parseToken(token, "rtyui");
        System.out.println(rtyui.getSubject()+"====="+rtyui.getIssuer());
    }

}
