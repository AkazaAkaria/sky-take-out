package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/24 20:11:11
 * @Comment sky-take-out>xuzq
 * @className DishFlavorMapper
 * @description TODO 口味表
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * @description  批量插入口味数据
     * @Param flavors
     * @return
     */
    void insertBatch(List<DishFlavor> flavors);
/**
 * @description //TODO 根据菜品Id删除对应的口味数据
 * @Param dishId
 * @return
 */

    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * @description //TODO 根据菜品id集合批量删除相关的口味数据
     * @Param ids
     * @return
     */
    void deleteByDishIds(List<Long> dishIds);
/**
 * @description //TODO 根据菜品id查询对应的口味数据
 * @Param dishId
 * @return {@link List< DishFlavor>}
 */
@Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
