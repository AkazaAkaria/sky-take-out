package com.sky.util;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


/**
 * @author AkazaAkari
 * @version 1.0
 * @className JwtUtil
 * @description TODO  提供jwt令牌的创建,校验,解析功能
 * @date 2021/3/24 10:47
 */
public class JwtUtil {
    final static String jwt_secret="jwt_secret";
    final static long expire = 5*60*1000;


    /**
     * @return {@link String}
     * @description //TODO  创建令牌
     * @Param uerId
     **/
    public static String createToken(String userId) {

        long currentpoint = System.currentTimeMillis();

        Date expire_date = new Date(currentpoint+expire);
        //创建一个jwt构造器对象
        JWTCreator.Builder builder = JWT.create();

        //给构建器添加载荷(用户id)
        builder.withAudience(userId);
        //设置令牌的过期时间节点
        builder.withExpiresAt(expire_date);

        //生成算法对象,需要秘钥
        Algorithm algorithm = Algorithm.HMAC256(jwt_secret);
        //生成签名后的令牌,需要算法定义作为参数

        String sign = builder.sign(algorithm);

        return sign;
    }

    /**
     * @return {@link String}
     * @description //TODO 解析令牌获取用户信息
     * @Param token
     **/

    public String getUserInfoFromToken(String token) {

        return null;

    }

    /**
     * @return {@link String}
     * @description //TODO  校验令牌
     * @Param token
     **/
    public static boolean checkToken(String token) {

        try {
            //生成算法对象,需要秘钥
            Algorithm algorithm = Algorithm.HMAC256(jwt_secret);
        //创建jwt的校验器对象
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
          //校验令牌(解析)
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return true;
        }catch (Exception ex) {

        }
        return false;
    }
}
