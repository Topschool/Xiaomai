package com.topschool.xm.controller.admin;

import com.topschool.xm.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/food")
public class FoodController {

    @Qualifier("defaultOrderFoodServiceImpl")
    @Autowired
    private OrderFoodService orderFoodService;

    @GetMapping
    public String index(){
        return "Hello world";
    }

    @PostMapping("/select_brand")
    public ResponseEntity<?> selectBrand(Integer id){
        orderFoodService.initOrderFoodSystem(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
