package com.topschool.xm.controller.weapp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.*;
import com.topschool.xm.model.orderfood.BrandFood;
import com.topschool.xm.model.orderfood.Order;
import com.topschool.xm.model.orderfood.OrderItem;
import com.topschool.xm.model.orderfood.TodayMenu;
import com.topschool.xm.model.vo.FoodOrderForm;
import com.topschool.xm.service.weapp.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author 小强
 * @date 2018/2/27 22:39
 */
@RestController
@RequestMapping("/v2/order_food")
public class OrderFoodController {

    @Autowired
    private OrderFoodService orderFoodService;

    @PostMapping("/booking")
    public ResultBody<?> booking(@Valid FoodOrderForm order) throws SystemException {
        Order orderForm = new Order();
        orderForm.setUid(order.getUid());
        orderForm.setItems(new ArrayList<>());
        outer:
        for (Long id : order.getFoods()) {
            for (OrderItem orderItem : orderForm.getItems()) {
                if (orderItem.getFoodId().equals(id)){
                    orderItem.setCount(orderItem.getCount()+1);
                    continue outer;
                }
            }
            orderForm.getItems().add(new OrderItem(id));
        }
        orderForm.setRemark(order.getRemark());
        orderFoodService.booking(orderForm);
        Map result = orderFoodService.getUserTodayOrder(order.getUid());
        return new ResultBody<>(result);
    }

    @PostMapping("/cancel")
    public ResultBody<?> cancel(long uid){
        orderFoodService.cancel(uid);
        return new ResultBody<>("取消成功");
    }

    @GetMapping("/menu")
    public ResultBody<?> getTodayMenu(){
        return new ResultBody<>(filterMenu(orderFoodService.getTodayMenu()));
    }

    @GetMapping("/user_order")
    public ResultBody<?> getOrder(long uid){
        Map order = orderFoodService.getUserTodayOrder(uid);
        return new ResultBody<>(order);
    }

    private JSONObject filterMenu(TodayMenu todayMenu){
        JSONObject object = new JSONObject();
        object.put("status", todayMenu.getStatus());
        object.put("todayBrand", todayMenu.getTodayBrand());
        JSONArray menu = new JSONArray();
        for (BrandFood food : todayMenu.getMenu()) {
            JSONObject foodItem = new JSONObject();
            foodItem.put("id", food.getId());
            foodItem.put("name", food.getName());
            foodItem.put("price", food.getPrice());
            foodItem.put("imgUrl", food.getImgUrl());
            foodItem.put("count", 0);
            menu.add(foodItem);
        }
        object.put("menu", menu);
        return object;
    }
}
