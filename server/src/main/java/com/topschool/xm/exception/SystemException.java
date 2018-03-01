package com.topschool.xm.exception;

import com.topschool.xm.enums.SystemError;

/**
 * @author 小强
 */
public class SystemException extends RuntimeException {
    private int code;

    public SystemException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SystemException(SystemError e) {
        super(e.getDescription());
        this.code = e.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
