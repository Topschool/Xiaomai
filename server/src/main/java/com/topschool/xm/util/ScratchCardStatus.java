package com.topschool.xm.util;

/**
 * @author 小强
 */
public enum  ScratchCardStatus {
    UNINIT(-1, "未初始化"),
    SCRATCHING(1, "正在刮卡"),
    STOPED(0, "已停止")
    ;

    private int code;
    private String description;

    ScratchCardStatus(int code, String description) {
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
