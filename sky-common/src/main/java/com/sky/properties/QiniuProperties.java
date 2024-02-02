package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.qiniu")
@Data
public class QiniuProperties {

    // 修改以下两个值放到proprietarties中，在密钥管理中获取

    private String accessKey;

    private String accessSecretKey;
    //空间名称
    private String bucket;


}
