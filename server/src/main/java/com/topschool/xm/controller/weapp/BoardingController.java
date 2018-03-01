package com.topschool.xm.controller.weapp;

import com.topschool.xm.model.ResultBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小强
 */
@RestController
@RequestMapping("/v2/boarding")
public class BoardingController {

    @GetMapping()
    public ResultBody<?> boarding() {
        return new ResultBody<>();
    }
}
