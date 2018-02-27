package com.topschool.xm.controller.weapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> boarding() {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        return null;
    }
}
