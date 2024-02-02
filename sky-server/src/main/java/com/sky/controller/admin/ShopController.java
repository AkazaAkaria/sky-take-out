package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/26 15:59:59
 * @Comment sky-take-out>xuzq
 * @className ShopController
 * @description TODO
 */
@RestController("adminShopController")
@Slf4j
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关的接口")
public class ShopController {
    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @return {@link Result}
     * @description //TODO 设置店铺的营业状态
     * @Param status
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺的营业状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置店铺的营业状态:{}", status == 1 ? "营业中" : "打烊中");
        // 将店铺状态存储的Redis中
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

    /**
     * @return {@link Result}
     * @description //TODO 获取店铺的营业状态
     * @Param status
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus() {

        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺的营业状态:{}", status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}
