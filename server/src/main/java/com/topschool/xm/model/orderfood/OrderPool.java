package com.topschool.xm.model.orderfood;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
public class OrderPool {
    private List<Food> foodList;
    private List<OrderLog> orderList;
    private String status;

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public List<OrderLog> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderLog> orderList) {
        this.orderList = orderList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
