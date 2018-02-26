package com.topschool.xm.dao.orderfood;

import com.topschool.xm.model.orderfood.Restaurant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小强
 */
public interface RestaurantMapper {

    boolean insert(Restaurant restaurant);

    Restaurant getById(Integer id);

    List<Restaurant> getAll();

    boolean deleteById(Integer id);

    boolean update(Restaurant restaurant);
}
