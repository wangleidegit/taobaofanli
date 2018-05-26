package com.taobao.fanli.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author:xiaolei
 * @date:2018/4/7
 */
@Configuration
public class TaobaoConfig {

    @Value("${taobao.url}")
    private String url;
    @Value("${taobao.appkey}")
    private String appkey;
    @Value("${taobao.secret}")
    private String secret;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
