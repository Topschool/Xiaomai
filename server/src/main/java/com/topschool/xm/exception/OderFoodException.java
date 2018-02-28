package com.topschool.xm.exception;

/**
 * @author 小强
 */
public class OderFoodException extends Throwable {
    public OderFoodException() {
    }

    public OderFoodException(String message) {
        super(message);
    }

    public OderFoodException(String message, Throwable cause) {
        super(message, cause);
    }
}
