package com.topschool.xm.dao.orderfood;

import com.topschool.xm.BaseTest;
import com.topschool.xm.model.orderfood.OrderLog;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class OrderLogMapperTest extends BaseTest {

    OrderLogMapper orderLogMapper = getBean(OrderLogMapper.class);

    @Test
    public void insert() {
        OrderLog orderLog = new OrderLog();
        orderLog.setFoodId(1);
        orderLog.setUserId("abc");
        orderLog.setCreateTime(System.currentTimeMillis());
        assert orderLogMapper.insert(orderLog);
    }

    @Test
    public void exist() {
        OrderLog orderLog = new OrderLog();
        orderLog.setUserId("abcd");
        orderLog.setCreateTime(System.currentTimeMillis());
        boolean flag = orderLogMapper.exist(orderLog);
        assert flag;
    }

    @Test
    public void getByRestaurantId() {
        assertNotNull(orderLogMapper.getByRestaurantId(2));
    }

    @Test
    public void getByDate() {
        assertNotNull(orderLogMapper.getByDate(new Date(System.currentTimeMillis())));
    }

    @Test
    public void getByUserId() {
        assertNotNull(orderLogMapper.getByUserId("abc"));
    }

    @Test
    public void getByFoodId() {
        assertNotNull(orderLogMapper.getByFoodId(1));
    }
}