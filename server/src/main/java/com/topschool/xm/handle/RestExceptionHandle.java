package com.topschool.xm.handle;

import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.ResultBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

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
        if (e instanceof IllegalArgumentException){
            return new ResultBody<>(-2, e.getMessage());
        }
        return new ResultBody<>(-1, "something happened");
    }
}
