package com.topschool.xm.enums;

/**
 * @author 小强
 */
public enum OrderFoodStatus {
    UNINIT(-1, "今日菜单未初始化"),
    STARTING(1, "正在订餐"),
    STOPED(0, "已停止订餐");

    private int code;
    private String description;

    OrderFoodStatus(int code, String description) {
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
