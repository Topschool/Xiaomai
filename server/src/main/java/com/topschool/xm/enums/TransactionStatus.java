package com.topschool.xm.enums;

/**
 * @author 小强
 */
public enum TransactionStatus {

    FINISH(1, "已完成"),
    UNPAID(0, "未付款"),
    CANCELLED(-1, "已取消"),;

    private int code;
    private String description;

    TransactionStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
