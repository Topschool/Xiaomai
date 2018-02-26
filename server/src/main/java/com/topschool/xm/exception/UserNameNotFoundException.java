package com.topschool.xm.exception;

/**
 * @author 小强
 */
public class UserNameNotFoundException extends Throwable {
    public UserNameNotFoundException() {
        super();
    }

    public UserNameNotFoundException(String message) {
        super(message);
    }

    public UserNameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
