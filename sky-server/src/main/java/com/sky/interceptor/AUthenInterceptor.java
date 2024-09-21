package com.sky.interceptor;

import com.google.gson.Gson;

import com.sky.annotation.JwtAuthen;
import com.sky.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AkazaAkari
 * @version 1.0
 * @className AUthenInterceptor
 * @description TODO
 * @date 2021/3/24 14:23
 */
public class AUthenInterceptor implements HandlerInterceptor {


    //进入处理方法之前激发
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("prihangdle...");

        boolean flag= false;
        if (handler instanceof HandlerMethod){//是否处理器方法
            //从处理器方法中获取权限方法注解
            HandlerMethod method = (HandlerMethod) handler;
            JwtAuthen jwtAuthen = method.getMethodAnnotation(JwtAuthen.class);
            if (jwtAuthen!= null){//含权限注解,必须检验令牌合法
                String token = request.getHeader("authoritation");//获取方法的描述,后续可以记录日志
                boolean check = JwtUtil.checkToken(token);
                if (check){
                    flag=true;
                }else {//令牌无效,无权访问

                    response.setContentType("application/json;charset=UTF-8");
                    //返回json消息
                    PrintWriter out = response.getWriter();
                    Map json = new HashMap();
                    json.put("statusCode", -100);
                    json.put("msg","this user auth id invalid");
                    String s = new Gson().toJson(json);
                    out.write(s);
                    flag=false;
                }
            } else {
                flag=true;
            }
        }else {
            return true;
        }
        //添加拦截逻辑
        return flag;
    }
    //执行完处理方法返回逻辑视图后被激发
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    //获取视图后被激发
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
