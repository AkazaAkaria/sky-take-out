package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.alioss")
@Data
public class AliOssProperties {

    private String endpoint;// 外网域名
    private String accessKeyId; // 阿里云账号AccessKey ID
    private String accessKeySecret; // 阿里云账号AccessKey Secret
    private String bucketName; // 阿里云存储空间名称

}
