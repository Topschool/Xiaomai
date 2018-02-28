package com.topschool.xm.controller.admin;

import com.topschool.xm.service.admin.TodayMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小强
 */
@RestController
@RequestMapping("/admin/order_food_manage")
public class OrderFoodManageController {

    @Autowired
    private TodayMenuService todayMenuService;

    @PostMapping("/start")
    public ResponseEntity<?> start(){
        todayMenuService.init();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/stop")
    public ResponseEntity<?> stop(){
        todayMenuService.stop();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
