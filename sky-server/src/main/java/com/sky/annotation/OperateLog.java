package com.sky.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/11 17:32:16
 * @Comment springbootEmpDept>xuzq
 * @className OperateLog
 * @description TODO
 */
// @Target(ElementType.METHOD)
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperateLog {
}
