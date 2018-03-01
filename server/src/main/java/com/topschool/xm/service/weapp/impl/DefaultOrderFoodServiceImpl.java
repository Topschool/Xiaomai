package com.topschool.xm.service.weapp.impl;

import com.topschool.xm.dao.UserInfoDao;
import com.topschool.xm.dao.orderfood.BrandFoodDao;
import com.topschool.xm.dao.orderfood.OrderRecordDao;
import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.BrandFood;
import com.topschool.xm.model.OrderRecord;
import com.topschool.xm.model.TodayMenu;
import com.topschool.xm.model.UserInfo;
import com.topschool.xm.service.weapp.OrderFoodService;
import com.topschool.xm.enums.OrderFoodStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
    private OrderRecordDao orderRecordDao;
    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public Map getUserTodayOrder(long uid) {
        UserInfo userInfo = userInfoDao.selectById(uid);
        assert userInfo != null;
        List<OrderRecord> records = orderRecordDao.getTodayOrderByUserId(uid, System.currentTimeMillis());
        Map<String, Object> result = new ConcurrentHashMap<>(5);
        BigDecimal difference = BigDecimal.valueOf(-20);
        List<Map> order = new ArrayList<>();
        for (OrderRecord record : records) {
            Map map = recordToMap(record);
            difference = difference.add((BigDecimal) map.get("price"));
            order.add(map);
        }
        result.put("uid", uid);
        result.put("username", userInfo.getName());
        result.put("order", order.size() == 0 ? null : order);
        result.put("difference", difference.floatValue() >= 0f ? difference : 0);
        return result;
    }

    @Override
    public TodayMenu getTodayMenu() {
        if (todayMenu.getStatus() == OrderFoodStatus.STARTING) {
            return todayMenu;
        }
        return null;
    }

    @Override
    public void booking(long uid, long foodId) {
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setUserId(uid);
        orderRecord.setFoodId(foodId);
        orderRecord.setCreateTime(System.currentTimeMillis());
        orderRecordDao.insert(orderRecord);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void booking(long uid, long[] foodIds) throws SystemException {
        if (todayMenu.getStatus()== OrderFoodStatus.STOPED) {
            throw new SystemException(SystemError.ORDER_FOOD_SYSTEM_STOP);
        }
        if (todayMenu.getStatus()== OrderFoodStatus.UNINIT) {
            throw new SystemException(SystemError.ORDER_FOOD_SYSTEM_NOT_START);
        }
        for (long foodId : foodIds) {
            booking(uid, foodId);
        }
    }

    @Override
    public void cancel(long uid, long foodId) {
        orderRecordDao.delete(uid, foodId, System.currentTimeMillis());
    }

    @Override
    public void cancel(long uid) {
        orderRecordDao.deleteTodayAll(uid, System.currentTimeMillis());
    }

    @Override
    public boolean getUserTodayOrderStatus(long uid) {
        return orderRecordDao.getTodayOrderByUserId(uid, System.currentTimeMillis()) != null;
    }

    private Map recordToMap(OrderRecord record) {
        BrandFood food = brandFoodDao.selectById(record.getFoodId());
        assert food != null;
        ConcurrentMap<String, Object> map = new ConcurrentHashMap<>(3);
        map.put("foodId", record.getFoodId());
        map.put("foodName", food.getName());
        map.put("price", food.getPrice());
        return map;
    }
}
