package com.topschool.xm.configuration;

import com.topschool.xm.exception.FoodNotExistException;
import com.topschool.xm.exception.ScratchCardException;
import com.topschool.xm.exception.UserNameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;

/**
 * @author 小强
 */
@RestControllerAdvice
public class RestApiControlAdvice {

    @ExceptionHandler(FoodNotExistException.class)
    public ResponseEntity<?> handle(FoodNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<?> handle(NoPermissionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ScratchCardException.class)
    public ResponseEntity<?> handle(ScratchCardException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handle(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<?> handle(UserNameNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
