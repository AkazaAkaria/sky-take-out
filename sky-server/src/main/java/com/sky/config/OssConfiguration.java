package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/24 18:53:12
 * @Comment sky-take-out>xuzq
 * @className OssConfiguration
 * @description TODO  配置类，用于创建AliOssUtil对象
 */
@Slf4j
@Configuration
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean //AliOssUtil 的bean有时就不会去在创建
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("开始创建阿里云上传文件工具类对象是：{}",aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(), aliOssProperties.getBucketName());
    }
}
