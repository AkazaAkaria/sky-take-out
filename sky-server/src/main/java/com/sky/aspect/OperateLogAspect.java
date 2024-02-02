package com.sky.aspect;

import com.alibaba.fastjson.JSONObject;
import com.sky.entity.Employee;
import com.sky.entity.OperateLog;
import com.sky.mapper.EmployeeMapper;
import com.sky.mapper.OperateLogMapper;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/11 13:59:29
 * @Comment springbootEmpDept>xuzq
 * @className TimeAspect
 * @description TODO
 */
@Aspect
@Component
@Slf4j
public class OperateLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private EmployeeMapper employeeMapper;


    // 切入点的定义  某个包下的所有方法都要被拦截时，使用
    // 方式1  @Pointcut("execution(* com.sky.controller.admin.DishController.*(..))")
    //  根据自己的需求需要在某个类下的某些方法来进行拦截  需要定义注解类如 OperateLog
    //方式2   @Pointcut("@annotation(com.sky.annotation.OperateLog)")
    @Pointcut("execution(* com.sky.controller.admin.*.*(..))")
    // @Pointcut("@annotation(com.sky.annotation.OperateLog)")
    private void pt() {}
    @Around("pt()") // 切入点
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer operateId ;
        String operateName = null;

        String jwt = httpServletRequest.getHeader("token");
        if (StringUtils.isEmpty(jwt)) {
            // 若是登录操作是，生成token，并记录当前登录用户信息
            String username = httpServletRequest.getCookies()[0].getValue();
            // 根据用户名查询用户信息
            Employee byUsername = employeeMapper.getByUsername(username);
            operateId = byUsername.getId().intValue();
            operateName = byUsername.getUsername();
        } else {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), jwt);
             operateId = (Integer) claims.get("empId") ;
            Employee employee = employeeMapper.getById(Long.valueOf(operateId));
            operateName = employee.getUsername();
        }
        LocalDateTime operateTime = LocalDateTime.now();
        long start = System.currentTimeMillis();
        String className = joinPoint.getTarget().getClass().getName();
        int index = className.lastIndexOf(".");
        className = className.substring(index).replace(".", "");
        log.info("目标对象的类名:{}", className);
        String methodName = "/"+joinPoint.getSignature().getName();
        log.info("目标方法的方法名:{}", methodName);
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        log.info("目标方法运行时传入的参数:{}", methodParams);
        Object proceed = joinPoint.proceed();
        String returnValue = JSONObject.toJSONString(proceed);
        log.info("目标方法运行的返回值:{}", returnValue);
        long end = System.currentTimeMillis();
        long costTime = end - start;
        log.info(joinPoint.getSignature() + "方法的执行耗时：{}ms", costTime);
        OperateLog operateLog = new OperateLog(null, operateId, operateName, operateTime, className, methodName, methodParams, returnValue, costTime, LocalDateTime.now());
        operateLogMapper.insert(operateLog);
        log.info("aop记录操作日志:{}", operateLog);
        return proceed;
    }

    @Before("pt()")
    public void before() {
        log.info("Before.........");
    }

    @After("pt()")
    public void after() {
        log.info("after.........");
    }

    @AfterReturning("pt()")
    public void afterReturning() {
        log.info("afterReturning.........");
    }

    @AfterThrowing("pt()")
    public void afterThrowing() {
        log.info("afterThrowing.........");
    }

}
