package com.topschool.xm.exception;

public class FoodNotExistException extends Throwable {
    public FoodNotExistException() {
        super();
    }

    public FoodNotExistException(String message) {
        super(message);
    }

    public FoodNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
