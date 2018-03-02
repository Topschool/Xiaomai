package com.topschool.xm.controller.weapp;

import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.ResultBody;
import com.topschool.xm.service.weapp.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultBody<?> booking(long uid, long[] foods) throws SystemException {
        orderFoodService.booking(uid, foods);
        Map order = orderFoodService.getUserTodayOrder(uid);
        return new ResultBody<>(order);
    }

    @PostMapping("/cancel")
    public ResultBody<?> cancel(long uid){
        orderFoodService.cancel(uid);
        Map order = orderFoodService.getUserTodayOrder(uid);
        return new ResultBody<>(order);
    }

    @GetMapping("/menu")
    public ResultBody<?> getTodayMenu(){
        return new ResultBody<>(orderFoodService.getTodayMenu());
    }

    @GetMapping("/user_order")
    public ResultBody<?> getOrder(long uid){
        Map order = orderFoodService.getUserTodayOrder(uid);
        return new ResultBody<>(order);
    }
}
