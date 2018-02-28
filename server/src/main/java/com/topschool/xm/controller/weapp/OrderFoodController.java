package com.topschool.xm.controller.weapp;

import com.topschool.xm.exception.OderFoodException;
import com.topschool.xm.service.weapp.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> booking(long uid, long[] foods) throws OderFoodException {
        orderFoodService.booking(uid, foods);
        Map order = orderFoodService.getUserTodayOrder(uid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(long uid){
        orderFoodService.cancel(uid);
        Map order = orderFoodService.getUserTodayOrder(uid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/menu")
    public ResponseEntity<?> getTodayMenu(){
        return new ResponseEntity<>(orderFoodService.getTodayMenu(), HttpStatus.OK);
    }

    @GetMapping("/user_order")
    public ResponseEntity<?> getOrder(long uid){
        Map order = orderFoodService.getUserTodayOrder(uid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
