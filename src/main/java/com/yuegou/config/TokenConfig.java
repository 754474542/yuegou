package com.yuegou.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "utils.token")
public class TokenConfig {

    private String tokenKey;
    private Integer tokenExpirationTime;

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public Integer getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

}
