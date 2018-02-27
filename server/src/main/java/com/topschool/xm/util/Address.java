package com.topschool.xm.util;

/**
 * @author 小强
 */
public enum Address {
    BJ(1, "北京"),
    SH(2, "上海"),
    NJ(3, "南京"),
    WX(4, "无锡"),
    ;
    private int code;
    private String description;

    Address(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
