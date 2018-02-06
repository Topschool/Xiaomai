package com.topschool.xm.service.impl;

import com.sun.javafx.binding.StringFormatter;
import com.topschool.xm.dao.orderfood.FoodMapper;
import com.topschool.xm.dao.orderfood.OrderLogMapper;
import com.topschool.xm.dao.scratchcard.ScratchLogMapper;
import com.topschool.xm.exception.FoodNotExistException;
import com.topschool.xm.model.orderfood.Food;
import com.topschool.xm.model.orderfood.OrderLog;
import com.topschool.xm.model.orderfood.OrderPool;
import com.topschool.xm.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NoPermissionException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class DefaultOrderFoodServiceImpl implements OrderFoodService {

    @Autowired
    private OrderPool orderPool;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private ScratchLogMapper scratchLogMapper;

    public Map getFoodList() {
        Map result = new HashMap();
        List<Map> foods = new ArrayList<>();
        if (null == orderPool.getFoodList()) {
            result.put("systemStatus", false);
            result.put("foods", null);
            return result;
        }
        for (Food food : orderPool.getFoodList()) {
            foods.add(changeFoodInfoToMap(food));
        }
        result.put("systemStatus", orderPool.getStatus());
        result.put("foods", foods);
        return result;
    }

    public String booking(String userId, Integer foodId) throws NoPermissionException, FoodNotExistException {
        assert userId != null;
        assert foodId != null;
        Boolean flag = scratchLogMapper.isExist(userId, new Date(System.currentTimeMillis()));
        if (null == flag || !flag) {
            throw new NoPermissionException("今日未刮卡，不能点餐");
        }
        if (null == foodMapper.getById(foodId)) {
            throw new FoodNotExistException(String.format("id为%d的food不存在", foodId));
        }
        OrderLog orderLog = new OrderLog();
        orderLog.setFoodId(foodId);
        orderLog.setUserId(userId);
        orderLog.setCreateTime(System.currentTimeMillis());
        if (orderLogMapper.exist(orderLog)) {
            return "已经订餐";
        }
        orderLogMapper.insert(orderLog);
        return "订餐成功";
    }

    public String cancel(String userId) {
        List<OrderLog> orderLogs = orderLogMapper.getTodayOrderByUserId(userId);
        if (orderLogs == null || orderLogs.size() < 1) {
            return "还未订餐";
        }
        for (OrderLog order : orderLogs) {
            orderLogMapper.delete(order.getUserId(), order.getFoodId());
        }
        return "取消成功";
    }

    public Map getUserStatus(String userId) {
        Map map = new HashMap();
        map.put("id", userId);
//        map.put("name", );
        map.put("scratchCardStatus", scratchLogMapper.isExist(userId, new Date(System.currentTimeMillis())));
        map.put("orderFoodStatus", isExist(userId));
        return map;
    }

    public Map getUsersOrder(String userId) throws FoodNotExistException {
        List<OrderLog> orderLogs = orderLogMapper.getTodayOrderByUserId(userId);
        List<Map> orders = new ArrayList<>();

        for (OrderLog order : orderLogs) {
            Food food = foodMapper.getById(order.getFoodId());
            if (null==food){
                throw new FoodNotExistException(String.format("id为%d的food不存在", order.getFoodId()));
            }
            orders.add(changeFoodInfoToMap(food));
        }
        Map map = new HashMap();
        map.put("id", userId);
//        map.put("name", "test_user"+userId);
        map.put("orderList", orders);
        return map;
    }

    @Override
    public void initOrderFoodSystem(int restaurantId) {
        List<Food> list = foodMapper.getByRestaurantId(restaurantId);
        orderPool.setFoodList(list);
        orderPool.setStatus(true);
    }

    @Override
    public boolean submit() throws Exception {

        return true;
    }

    @Override
    public boolean clean() {
        orderPool.setStatus(false);
        orderPool.setFoodList(null);
//        orderPool.setOrderList(null);
        return false;
    }

    private boolean isExist(String uid) {
        List<OrderLog> orderLogs = orderLogMapper.getTodayOrderByUserId(uid);
        return null != orderLogs && orderLogs.size() > 0;
    }

    private Map changeFoodInfoToMap(Food food) {
        Map map = new HashMap();
        map.put("id", food.getId());
        map.put("name", food.getName());
        map.put("price", food.getPrice());
        map.put("difference", food.getPrice() > 20 ? food.getPrice() - 20 : null);
        map.put("imageUrl", food.getLogoUrl());
        return map;
    }
}
