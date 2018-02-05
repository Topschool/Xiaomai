package com.topschool.xm.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/food")
public class FoodController {

    @GetMapping
    public String index(){
        return "Hello world";
    }
}
