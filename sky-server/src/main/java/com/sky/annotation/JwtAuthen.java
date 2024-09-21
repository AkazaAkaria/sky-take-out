package com.sky.annotation;

import java.lang.annotation.*;

/**
 * @author AkazaAkari
 * @version 1.0
 * @className JwtAuthen
 * @description TODO  自定义注解标识一个接口是否需要登录权限
 * @date 2021/3/24 14:18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtAuthen {

    public String name() default "";
}
