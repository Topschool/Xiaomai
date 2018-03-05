package com.topschool.xm.dao.orderfood;

import com.topschool.xm.Application;
import com.topschool.xm.model.orderfood.Order;
import com.topschool.xm.model.orderfood.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;

    @Test
    public void insert() {
        Order order = new Order();
        order.setRemark("吃屎去吧");
        order.setCreateTime(System.currentTimeMillis());
        order.setUid(1000L);

        assertEquals(1, orderDao.insert(order));
        order = orderDao.selectByUid(order.getUid(), System.currentTimeMillis());
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(1L, 3, order.getId()));
        orderItems.add(new OrderItem(2L, 1, order.getId()));
        orderItems.add(new OrderItem(3L, 2, order.getId()));
        assertEquals(3, orderItemDao.insertList(orderItems));
    }

    @Test
    public void insertList() {
    }

    @Test
    public void select() {
    }

    @Test
    public void selectByUid() {
        Order order = orderDao.selectByUid(1000L, System.currentTimeMillis());
        System.out.println(order);
    }

    @Test
    public void update() {
    }

    @Test
    public void del(){
        assertEquals(1, orderDao.deleteByOrderId(7L));
        assertEquals(3, orderItemDao.deleteByOrderId(7L));
    }
}