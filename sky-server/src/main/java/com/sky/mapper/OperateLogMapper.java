package com.sky.mapper;


import com.sky.entity.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/11 17:25:10
 * @Comment springbootEmpDept>xuzq
 * @className OperateLogMapper
 * @description TODO
 */
@Mapper
public interface OperateLogMapper {
    @Insert("insert into operate_log (operate_id, operate_name, operate_time, class_name, method_name, method_params, return_value, cost_time,create_time) " +
            "VALUES (#{operateId},#{operateName},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime},#{createTime})")
    void insert(OperateLog operateLog);
}
