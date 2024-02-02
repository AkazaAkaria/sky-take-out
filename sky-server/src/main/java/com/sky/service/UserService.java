package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/27 9:53:05
 * @Comment sky-take-out>xuzq
 * @className UserService
 * @description TODO
 */
public interface UserService {

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
