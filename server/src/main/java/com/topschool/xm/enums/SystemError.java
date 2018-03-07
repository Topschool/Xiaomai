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
    ORDER_NOT_EXIST(2005, "订单不存在"),

    //用户认证相关
    USER_NOT_EXIST(3000, "用户不存在"),
    USER_HAS_EXIST(3001, "用户已经存在"),
    USER_CERTIFICATION_FAIL(3002, "用户认证失败"),

    //小程序相关
    TOKEN_ILLEGAL(4000, "token不合法"),
    TOKEN_EXPIRED(4001, "token已过期"),
    WEAPP_NOT_BOUND_OPEN_PLATFORM(4002, "小程序未绑定开放平台"),

    //注册相关
    USER_NAME_ILLEGAL(5000, "用户名非法"),
    SIGNAL_ILLEGAL(5001, "邀请码非法"),
    ADDRESS_CODE_NOT_EXIST(5002,"地址编码非法"),

    SYSTEM_ERROE(6000, "系统出错"),

    //虚拟账户相关
    ACCOUNT_STATUS_NOT_EXIST(7000, "非法账户状态"),
    ACCOUNT_NOT_EXIST(7001, "用户不存在"),
    ACCOUNT_INSUFFICIENT_BALANCE(7002, "余额不足"),

    //交易相关
    TRANSACTION_NOT_EXIST(8000, "交易不存在"),
    NO_PERMISSION_MODIFY_TRANSACTION(8001, "没有权限修改别人的交易"),
    NOT_NEEDED_REPAY(8002, "已完成支付"),

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
