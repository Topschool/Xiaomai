package com.topschool.xm.controller;

import com.topschool.xm.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/order_food")
public class OrderFoodController {

    @Autowired
    private OrderFoodService orderFoodService;

    @GetMapping("/food_list")
    public ResponseEntity<?> getList(String id){
       List list = orderFoodService.getFoodList();
        return new ResponseEntity<Object>(list, OK);
    }

    @PostMapping("/booking")
    public ResponseEntity<?> booking(String userId, Integer foodId){
        String msg = orderFoodService.booking(userId, foodId);
        return new ResponseEntity<Object>(msg, OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(String id){
        String msg = orderFoodService.cancel(id);
        return new ResponseEntity<Object>(msg, OK);
    }

    @GetMapping("/user_status")
    public ResponseEntity<?> getUserStatus(String id){
        Map status = orderFoodService.getUserStatus(id);
        return new ResponseEntity<Object>(status, OK);
    }

    @GetMapping("/user_order")
    public ResponseEntity<?> getUserOrder(String id){
        Map map = orderFoodService.getUsersOrder(id);
        return new ResponseEntity<Object>(map, OK);
    }
}
