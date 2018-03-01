package com.topschool.xm.model;

import com.topschool.xm.exception.SystemException;

/**
 * @author 小强
 */
public class ResultBody<T> {
    private int code;
    private String error;
    private String success;
    private T data;

    public ResultBody() {
        this.success = "ok";
        this.code=1;
    }

    public ResultBody(T data) {
        this.success = "ok";
        this.data = data;
        this.code=1;
    }

    public ResultBody(SystemException e) {
        this.code = e.getCode();
        this.error = e.getMessage();
    }

    public ResultBody(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
