package com.topschool.xm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get-api")
    public ResponseEntity<?> testApi(){
        return new ResponseEntity<Object>("Hello world", HttpStatus.OK);
    }
}
