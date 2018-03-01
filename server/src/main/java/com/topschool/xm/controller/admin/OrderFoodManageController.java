package com.topschool.xm.controller.admin;

import com.topschool.xm.model.ResultBody;
import com.topschool.xm.service.admin.TodayMenuService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResultBody<?> start(){
        todayMenuService.init();
        return new ResultBody<>();
    }

    @PostMapping("/stop")
    public ResultBody<?> stop(){
        todayMenuService.stop();
        return new ResultBody<>();
    }
}
