package com.topschool.xm.exception;

/**
 * @author 小强
 */
public class UserNotFoundException extends Throwable {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
