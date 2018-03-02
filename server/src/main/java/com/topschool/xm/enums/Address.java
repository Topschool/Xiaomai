package com.topschool.xm.enums;

import com.topschool.xm.exception.SystemException;

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

    public String getDescription() {
        return description;
    }

    public static Address valueOf(int code){
        for (Address address : values()) {
            if (address.getCode()==code){
                return address;
            }
        }
        throw new SystemException(SystemError.ADDRESS_CODE_NOT_EXIST);
    }
}
