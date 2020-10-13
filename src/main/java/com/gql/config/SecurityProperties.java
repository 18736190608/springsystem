package com.gql.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Security配置属性。</p>
 *
 * @author gql
 */
@Component
@ConfigurationProperties(prefix = "security-config")
public class SecurityProperties {
    private String loginUrl;
    private String permitAllUrls;
    private String ignoredUrls;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getPermitAllUrls() {
        return permitAllUrls;
    }

    public void setPermitAllUrls(String permitAllUrls) {
        this.permitAllUrls = permitAllUrls;
    }

    public String getIgnoredUrls() {
        return ignoredUrls;
    }

    public void setIgnoredUrls(String ignoredUrls) {
        this.ignoredUrls = ignoredUrls;
    }

    public List<String> getPermitAllUrlList() {
        List<String> resultList = new ArrayList<String>();
        if (this.permitAllUrls != null && ! ("").equals(this.permitAllUrls)) {
            String[] urls = this.permitAllUrls.split(",");
            for (String url : urls) {
                if (url != null && ! ("").equals(url)) resultList.add(url.trim());
            }
        }
        return resultList;
    }

    public List<String> getIgnoredUrlList() {
        List<String> resultList = new ArrayList<String>();
        if (this.ignoredUrls != null && ! ("").equals(this.ignoredUrls)) {
            String[] urls = this.ignoredUrls.split(",");
            for (String url : urls) {
                if (url != null && ! ("").equals(url)) resultList.add(url.trim());
            }
        }
        return resultList;
    }
}
