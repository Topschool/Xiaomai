package com.topschool.xm.configuration;

import com.topschool.xm.exception.FoodNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;

@RestControllerAdvice
public class RestApiControlAdvice {

    @ExceptionHandler(FoodNotExistException.class)
    public ResponseEntity<?> foodHandle(FoodNotExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<?> handle(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
