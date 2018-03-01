package com.topschool.xm.enums;

/**
 * @author 小强
 */
public enum SystemError {
    //刮刮卡相关
    SCRATCH_CARD_SYSTEM_UNINIT(1000, "刮刮卡系统未初始化"),
    SCRATCH_CARD_SYSTEM_ERROE(1001, "刮刮卡系统出错"),
    SCRATCH_CARD_REPEAT(1002, "重复刮卡"),

    //订餐系统相关
    ORDER_FOOD_SYSTEM_NOT_START(2000, "订餐未开始"),
    ORDER_FOOD_SYSTEM_STOP(2001, "订餐已结束"),
    ORDER_FOOD_NO_PERMISSION(2002, "未刮卡用户不允许订餐"),
    FOOD_NOT_EXIST(2003, "food不存在"),
    BRAND_NOT_EXIST(2004, "brand不存在"),

    //用户认证相关
    USER_NOT_EXIST(3000, "用户不存在"),
    USER_CERTIFICATION_FAIL(3001, "用户认证失败"),

    //小程序相关
    TOKEN_ILLEGAL(4000, "token不合法"),
    TOKEN_EXPIRED(4001, "token已过期"),

    //注册相关
    USER_NAME_ILLEGAL(5000, "用户名非法"),
    SIGNAL_ILLEGAL(5001, "邀请码非法"),
    ADDRESS_CODE_NOT_EXIST(5002,"地址编码非法"),

    SYSTEM_ERROE(6000, "系统出错");

    ;
    private int code;
    private String description;

    SystemError(int code, String description) {
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
