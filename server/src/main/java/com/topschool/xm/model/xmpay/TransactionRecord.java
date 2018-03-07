package com.topschool.xm.model.xmpay;

import com.topschool.xm.enums.TransactionStatus;

import java.math.BigDecimal;

/**
 * @author 小强
 */
public class TransactionRecord {
    private Long id;
    private String payerId;
    private String payeeId;
    private BigDecimal amount;
    private String remark;
    private TransactionStatus status;
    private Long createTime;
    private Long finishTime;

    public TransactionRecord() {
    }

    public TransactionRecord(Long id, String payerId, String payeeId, BigDecimal amount, String remark) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.remark = remark;
        this.status = TransactionStatus.FINISH;
        this.createTime = System.currentTimeMillis();
    }

    public TransactionRecord(Long id, String payerId, String payeeId, BigDecimal amount, String remark, TransactionStatus status, Long createTime, Long finishTime) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.remark = remark;
        this.status = status;
        this.createTime = createTime;
        this.finishTime = finishTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "id=" + id +
                ", payerId='" + payerId + '\'' +
                ", payeeId='" + payeeId + '\'' +
                ", amount=" + amount +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
