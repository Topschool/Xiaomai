package com.topschool.xm.exception;

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
