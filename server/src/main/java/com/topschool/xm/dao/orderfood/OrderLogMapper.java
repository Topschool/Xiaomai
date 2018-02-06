package com.topschool.xm.dao.orderfood;

import com.topschool.xm.model.orderfood.OrderLog;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface OrderLogMapper {
    boolean insert(OrderLog orderLog);
    boolean exist(OrderLog orderLog);
    List<OrderLog> getByRestaurantId(Integer id);
    List<OrderLog> getByDate(Date date);
    List<OrderLog> getByUserId(String id);
    List<OrderLog> getByFoodId(int id);
    boolean delete(@Param("uid")String uid, @Param("foodId")Integer foodId);
    List<OrderLog> getTodayOrderByUserId(String id);
}
