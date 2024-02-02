package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description //TODO 菜品表
 * @Param null
 * @return {@link null}
 */
@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * @return
     * @description //TODO 插入菜品数据
     * @Param dish
     */
    // 公共字段赋值
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * @return {@link Page< DishVO>}
     * @description //TODO   菜品分页查询
     * @Param dishPageQueryDTO
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * @return {@link Dish}
     * @description //TODO 根据主键查询菜品
     * @Param id
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * @return
     * @description //TODO  根据主键删除菜品数据
     * @Param id
     */
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     * @return {@link null}
     * @description //TODO 根据菜品id集合批量删除菜品数据
     * @Param null
     */
    void deleteByIds(List<Long> ids);

    /**
     * @return
     * @description //TODO 根据id动态修改菜品数据
     * @Param dish
     */
    void update(Dish dish);

    /**
     * 动态条件查询菜品
     *
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);

    /**
     * 根据套餐id查询菜品
     *
     * @param setmealId
     * @return
     */
    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long setmealId);

    /**
     * 根据条件统计菜品数量
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
