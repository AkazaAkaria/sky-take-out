package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/11 17:16:08
 * @Comment springbootEmpDept>xuzq
 * @className OperateLog
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLog {
    /**
     * id
     */
    private Integer id;
    /**
     * 操作人id
     */
    private Integer operateId;
    /**
     * 操作人
     */
    private String operateName;
    /**
     * 操作时间
     */
    private LocalDateTime operateTime;
    /**
     * 操作的类名
     */
    private String className;
    /**
     * 操作的方法名
     */
    private String methodName;
    /**
     * 方法的参数
     */
    private String methodParams;
    /**
     * 返回值
     */
    private String returnValue;
    /**
     * 方法执行耗时,单位ms
     */
    private Long costTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
