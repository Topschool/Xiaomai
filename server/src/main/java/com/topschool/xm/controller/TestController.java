package com.topschool.xm.controller;

import com.topschool.xm.model.orderfood.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 小强
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/view")
    public String index(){
        return "test";
    }

    @PostMapping("/post")
    @ResponseBody
    public Object post(@Valid Order order)
    {
        return order;
    }
}
