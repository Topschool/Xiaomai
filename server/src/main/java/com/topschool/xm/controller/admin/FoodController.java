package com.topschool.xm.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class FoodController {
    @GetMapping
    public String index(){
        return "Hello world";
    }
}
