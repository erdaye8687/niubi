package com.ljy.logindemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/18
 */
@Data
@Component
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
    private List<String> urls = new ArrayList<>();
}
