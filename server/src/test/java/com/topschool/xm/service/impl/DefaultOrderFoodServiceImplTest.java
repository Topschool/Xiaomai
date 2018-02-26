package com.topschool.xm.service.impl;

import com.topschool.xm.BaseTest;
import com.topschool.xm.model.orderfood.OrderPool;
import com.topschool.xm.service.OrderFoodService;
import org.junit.Test;

public class DefaultOrderFoodServiceImplTest extends BaseTest {

    OrderFoodService orderFoodService = this.getBean(DefaultOrderFoodServiceImpl.class);
    OrderPool orderPool = this.getBean(OrderPool.class);

    @Test
    public void getFoodList() {
    }

    @Test
    public void booking() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void getUserStatus() {
    }

    @Test
    public void getUsersOrder() {
    }

    @Test
    public void initOrderFoodSystem() {
        orderFoodService.initOrderFoodSystem(1);
        System.out.println();
    }

    @Test
    public void submit() {
    }

    @Test
    public void clean() {
    }

    @Test
    public void isExist() {
    }
}