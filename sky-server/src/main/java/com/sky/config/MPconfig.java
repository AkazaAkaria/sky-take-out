package com.sky.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author zqxu
 * @Date 2024/04/10 21:19
 * @Comment <demo1>Xuzq
 * @Description
 */
@Configuration
@MapperScan("com.sky.mapper")
public class MPconfig {

    // 乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    //逻辑删除插件
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    /**
     * sql 执行性能分析插件
     * 开发环境使用，生产环境关闭 maxTime 指的是 sql 执行的最大时间，超过这个时间则会报出严重的性能问题
     */
    @Bean
    @Profile({"dev","test"}) // 设置 dev 和 test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 设置 sql 执行的最大时间为 1000ms
        performanceInterceptor.setMaxTime(100);
        // 设置是否输出 sql 语句
        performanceInterceptor.setWriteInLog(true);
        // 设置 sql 格式化
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
    /**
     * @Title: mybatisConfigurationCustomizer
     * @Description: 编写一个MyBatis的配置类，将这个插件注册进去
     * @CreatedDate: 2024/06/16 00:06
     * @Param
     * @Return ConfigurationCustomizer
     **/
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {
            configuration.addInterceptor(new SqlInterceptor());
        };
    }
}
