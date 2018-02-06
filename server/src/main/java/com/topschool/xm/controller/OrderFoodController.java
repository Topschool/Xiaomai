package com.topschool.xm.controller;

import com.topschool.xm.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("defaultOrderFoodServiceImpl")
    @Autowired
    private OrderFoodService orderFoodService;

    @GetMapping("/food_list")
    public ResponseEntity<?> getList(String uid){
       Map map = orderFoodService.getFoodList();
        return new ResponseEntity<Object>(map, OK);
    }

    @PostMapping("/booking")
    public ResponseEntity<?> booking(String uid, Integer foodId) throws Exception {
        String msg = orderFoodService.booking(uid, foodId);
        return new ResponseEntity<Object>(msg, OK);
    }

    @PostMapping("/foods_booking")
    public ResponseEntity<?> foodsBooking(String uid, Integer[] foods) throws Exception {
        for (Integer food : foods) {
            orderFoodService.booking(uid, food);
        }
        return new ResponseEntity(OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(String uid){
        String msg = orderFoodService.cancel(uid);
        return new ResponseEntity<Object>(msg, OK);
    }

    @GetMapping("/user_status")
    public ResponseEntity<?> getUserStatus(String uid){
        Map status = orderFoodService.getUserStatus(uid);
        return new ResponseEntity<Object>(status, OK);
    }

    @GetMapping("/user_order")
    public ResponseEntity<?> getUserOrder(String uid) throws Exception {
        Map map = orderFoodService.getUsersOrder(uid);
        return new ResponseEntity<Object>(map, OK);
    }
}
