package com.topschool.xm.dao.orderfood;

import com.topschool.xm.model.orderfood.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小强
 */
public interface FoodMapper {

    boolean insert(Food food);

    Food getById(Integer id);

    List<Food> getAll();

    boolean deleteById(Integer id);

    boolean update(Food food);

    List<Food> getByRestaurantId(Integer id);
}
