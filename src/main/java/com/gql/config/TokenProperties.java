package com.gql.config;

import com.gql.enums.TokenCacheType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gql
 * @date 2020/7/30
 **/
@Component
@ConfigurationProperties(prefix = "jwt")
public class TokenProperties {

    private String header;

    private String  secret;

    /**
     * Token过期时间（秒）
     */
    private long expiration;
    /**
     * Token前缀
     */
    private String tokenPrefix;
    /**
     * Token缓存类型
     */
    private TokenCacheType cacheType;

    /**
     * 不是Ajax请求的URL
     */
    private String notAjaxUrl;


    public String getHeader()
    {
        return header;
    }

    public void setHeader( String header )
    {
        this.header = header;
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret( String secret )
    {
        this.secret = secret;
    }

    public long getExpiration()
    {
        return expiration;
    }

    public void setExpiration( long expiration )
    {
        this.expiration = expiration;
    }

    public String getTokenPrefix()
    {
        return tokenPrefix;
    }

    public void setTokenPrefix( String tokenPrefix )
    {
        this.tokenPrefix = tokenPrefix;
    }

    public TokenCacheType getCacheType()
    {
        return cacheType;
    }

    public void setCacheType( TokenCacheType cacheType )
    {
        this.cacheType = cacheType;
    }

    public String getNotAjaxUrl()
    {
        return notAjaxUrl;
    }

    public void setNotAjaxUrl( String notAjaxUrl )
    {
        this.notAjaxUrl = notAjaxUrl;
    }

    public List<String> getNotAjaxUrlList()
    {
        List<String> resultList = new ArrayList<String>();
        if( notAjaxUrl != null && !"".equals( notAjaxUrl ) )
        {
            String[] urls = notAjaxUrl.split( "," );
            for( String url : urls )
            {
                url = url.trim();
                if( !"".equals( url ) ) {
                    resultList.add( url );
                }
            }
        }
        return resultList;
    }
}
