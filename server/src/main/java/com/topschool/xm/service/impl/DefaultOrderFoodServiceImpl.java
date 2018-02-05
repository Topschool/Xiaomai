package com.topschool.xm.service.impl;

import com.topschool.xm.dao.orderfood.FoodMapper;
import com.topschool.xm.dao.orderfood.OrderLogMapper;
import com.topschool.xm.dao.scratchcard.ScratchLogMapper;
import com.topschool.xm.model.orderfood.Food;
import com.topschool.xm.model.orderfood.OrderLog;
import com.topschool.xm.model.orderfood.OrderPool;
import com.topschool.xm.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        result.put("systemStatus", orderPool.getStatus());
        result.put("foods", orderPool.getFoodList());
        return result;
    }

    public String booking(String userId, Integer foodId) {
        OrderLog orderLog = new OrderLog();
        orderLog.setFoodId(foodId);
        orderLog.setUserId(userId);
        orderLog.setCreateTime(System.currentTimeMillis());
        orderPool.getOrderList().add(orderLog);
        return "订餐成功";
    }

    public String cancel(String userId) {
        List<OrderLog> orders = orderPool.getOrderList();
        List usersOrders = new ArrayList();
        for (OrderLog order : orders) {
            if (order.getUserId().equals(userId)) {
                usersOrders.add(order);
            }
        }
        return "取消成功";
    }

    public Map getUserStatus(String userId) {
        Map map = new HashMap();
        map.put("id", userId);
//        map.put("name", );
        map.put("scratchCardStatus", scratchLogMapper.isExist(userId));
        map.put("orderFoodStatus", isExist(userId));
        return map;
    }

    public Map getUsersOrder(String userId) {
        List<OrderLog> orders = orderPool.getOrderList();
        List usersOrders = new ArrayList();
        for (OrderLog order : orders) {
            if (order.getUserId().equals(userId)) {
                usersOrders.add(order);
            }
        }
        Map map = new HashMap();
        map.put("id", userId);
//        map.put("name", "test_user"+userId);
        map.put("orderList", usersOrders);
        return map;
    }

    public void initOrderFoodSystem(int restaurantId) {
        List<Food> list = foodMapper.getByRestaurantId(restaurantId);
        orderPool.setFoodList(list);
        orderPool.setStatus("正在订餐");
    }

    @Override
    public boolean submit() throws Exception {
        for (OrderLog orderLog : orderPool.getOrderList()) {
            if (!orderLogMapper.insert(orderLog)) {
                throw new Exception("提交失败");
            }
        }
        return true;
    }

    @Override
    public boolean clean() {
        orderPool.setStatus("未开始");
        orderPool.setFoodList(null);
        orderPool.setOrderList(null);
        return false;
    }

    boolean isExist(String uid) {
        for (OrderLog orderLog : orderPool.getOrderList()) {
            if (orderLog.getUserId().equals(uid)) {
                return true;
            }
        }
        return false;
    }
}
