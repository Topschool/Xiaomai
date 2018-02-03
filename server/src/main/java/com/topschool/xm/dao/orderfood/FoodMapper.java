package com.topschool.xm.dao.orderfood;

import com.topschool.xm.model.orderfood.Food;

import java.util.List;

public interface FoodMapper {

    boolean insert(Food food);

    Food getById(Integer id);

    List<Food> getAll();

    boolean deleteById(Integer id);

    boolean update(Food food);

    List<Food> getByRestaurantId(Integer id);
}
