package com.topschool.xm.handle;

import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.ResultBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author 小强
 */
@RestControllerAdvice
public class RestExceptionHandle {

    @ExceptionHandler(RuntimeException.class)
    public ResultBody<?> handle(WebRequest request, RuntimeException e){
        if (e instanceof SystemException){
            return new ResultBody<>(e);
        }
        return new ResultBody<>(-1, "something happened");
    }
}
