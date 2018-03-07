package com.topschool.xm.model.xmpay;

import com.topschool.xm.enums.AccountStatus;

import java.math.BigDecimal;

/**
 * @author 小强
 */
public class Account {
    private String id;
    private BigDecimal balance;
    private AccountStatus status;
    private Boolean isSysAccount;
    private Long updateTime;

    public Account() {
    }

    public Account(String id, BigDecimal balance) {
        this(id, balance, AccountStatus.NORMAL, false, System.currentTimeMillis());

    }

    public Account(String id, BigDecimal balance, AccountStatus status, Boolean isSysAccount, Long updateTime) {
        this.id = id;
        this.balance = balance;
        this.status = status;
        this.isSysAccount = isSysAccount;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Boolean getIsSysAccount() {
        return isSysAccount;
    }

    public void setIsSysAccount(Boolean sysAccount) {
        isSysAccount = sysAccount;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
