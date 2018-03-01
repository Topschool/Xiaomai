package com.topschool.xm.controller;

import com.topschool.xm.model.ResultBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小强
 */
@RestController
public class ErrorController {
    @RequestMapping(value = "/404")
    public ResultBody<?> handle(){
        return new ResultBody<>(0, "资源不存在");
    }
}
