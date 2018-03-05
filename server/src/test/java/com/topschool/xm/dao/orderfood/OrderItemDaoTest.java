package com.topschool.xm.dao.orderfood;

import com.topschool.xm.Application;
import com.topschool.xm.model.orderfood.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class OrderItemDaoTest {

    @Autowired
    private OrderItemDao orderItemDao;

    @Test
    public void insert() {
        OrderItem orderItem = new OrderItem(2L);
        orderItem.setOrderId(1L);
        assertEquals(1,orderItemDao.insert(orderItem));
    }

    @Test
    public void insertList() {
    }

    @Test
    public void select() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
        assertEquals(1, orderItemDao.delete(1L, 2L));
    }

    @Test
    public void deleteByOrderId() {
        assertEquals(2, orderItemDao.deleteByOrderId(1));
    }

    @Test
    public void getTodayOrderByOrderId() {
        List list = orderItemDao.getOrderItemByOrderId(1L);
        for (Object o : list) {
            System.out.println(o);
        }
    }
}