package com.topschool.xm.exception;

/**
 * @author 小强
 */
public class BrandNotFoundException extends Throwable {
    public BrandNotFoundException() {
    }

    public BrandNotFoundException(String message) {
        super(message);
    }

    public BrandNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
