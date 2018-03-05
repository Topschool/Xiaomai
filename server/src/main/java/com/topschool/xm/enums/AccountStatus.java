package com.topschool.xm.enums;

import com.topschool.xm.exception.SystemException;

/**
 * @author 小强
 */
public enum AccountStatus {

    NORMAL(1, "正常"),

    ;
    private int code;
    private String description;

    AccountStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static AccountStatus valueOf(int code) {
        for (AccountStatus accountStatus : values()) {
            if (accountStatus.getCode() == code) {
                return accountStatus;
            }
        }
        throw new SystemException(SystemError.ACCOUNT_STATUS_NOT_EXIST);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
