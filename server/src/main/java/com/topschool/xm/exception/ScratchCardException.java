package com.topschool.xm.exception;

/**
 * @author 小强
 */
public class ScratchCardException extends Throwable {
    public ScratchCardException() {
        super();
    }

    public ScratchCardException(String message) {
        super(message);
    }

    public ScratchCardException(String message, Throwable cause) {
        super(message, cause);
    }
}
