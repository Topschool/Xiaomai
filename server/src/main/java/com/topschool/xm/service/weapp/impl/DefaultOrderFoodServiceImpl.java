package com.topschool.xm.service.weapp.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.dao.UserInfoDao;
import com.topschool.xm.dao.orderfood.BrandFoodDao;
import com.topschool.xm.dao.orderfood.OrderDao;
import com.topschool.xm.dao.orderfood.OrderItemDao;
import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.orderfood.BrandFood;
import com.topschool.xm.model.orderfood.Order;
import com.topschool.xm.model.orderfood.OrderItem;
import com.topschool.xm.model.orderfood.TodayMenu;
import com.topschool.xm.model.user.UserInfo;
import com.topschool.xm.service.weapp.OrderFoodService;
import com.topschool.xm.enums.OrderFoodStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小强
 */
@Service
public class DefaultOrderFoodServiceImpl implements OrderFoodService {

    @Autowired
    private TodayMenu todayMenu;

    @Autowired
    private BrandFoodDao brandFoodDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public Map getUserTodayOrder(long uid) {
        UserInfo userInfo = userInfoDao.selectById(uid);
        assert userInfo != null;
        Order order = getUserOrder(uid);
        Map<String, Object> result = new HashMap<>();
        result.put("uid", order.getUid());
        result.put("remark", order.getRemark());
        JSONArray items = new JSONArray();
        double difference = -20;
        for (OrderItem orderItem : order.getItems()) {
            JSONObject item = new JSONObject();
            BrandFood food = brandFoodDao.selectById(orderItem.getFoodId());
            item.put("foodId", orderItem.getFoodId());
            item.put("count", orderItem.getCount());
            item.put("foodName", food.getName());
            item.put("total", orderItem.getCount()*food.getPrice().doubleValue());
            difference+=orderItem.getCount()*food.getPrice().doubleValue();
            items.add(item);
        }
        result.put("difference", difference);
        result.put("orderItems", items);
        return result;
    }

    @Override
    public TodayMenu getTodayMenu() {
        if (todayMenu.getStatus() == OrderFoodStatus.STARTING) {
            return todayMenu;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void booking(Order form) {
        Order order = orderDao.selectByUid(form.getUid(), System.currentTimeMillis());
        if (order==null){
            form.setCreateTime(System.currentTimeMillis());
            orderDao.insert(form);
            order = orderDao.selectByUid(form.getUid(), System.currentTimeMillis());
        }
        order.setCreateTime(System.currentTimeMillis());
        order.setRemark(order.getRemark());
        orderDao.update(order);
        for (OrderItem orderItem : form.getItems()) {
            orderItem.setId(null);
            orderItem.setOrderId(order.getId());
            orderItem.setCount(orderItem.getCount()==null?1:orderItem.getCount());
        }
        orderItemDao.insertList(form.getItems());
    }


    @Override
    public void cancel(long uid, long foodId) {
        Order order = orderDao.selectByUid(uid, System.currentTimeMillis());
        orderItemDao.delete(order.getId(), foodId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancel(long uid) {
        Order order = orderDao.selectByUid(uid, System.currentTimeMillis());
        if (order==null) {
            throw new SystemException(SystemError.ORDER_NOT_EXIST);
        }
        orderDao.deleteByOrderId(order.getId());
        orderItemDao.deleteByOrderId(order.getId());
    }

    @Override
    public boolean getUserTodayOrderStatus(long uid) {
        return orderItemDao.getOrderItemByOrderId(uid) != null;
    }

    private Order getUserOrder(Long uid){
        Order order = orderDao.selectByUid(uid, System.currentTimeMillis());
        if (order==null) {
            throw new SystemException(SystemError.ORDER_NOT_EXIST);
        }
        List<OrderItem>orderItems = orderItemDao.getOrderItemByOrderId(order.getId());
        order.setItems(orderItems);
        return order;
    }
}
