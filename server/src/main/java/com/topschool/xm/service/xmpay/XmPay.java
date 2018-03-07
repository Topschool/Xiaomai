package com.topschool.xm.service.xmpay;

import com.topschool.xm.model.xmpay.TransactionRecord;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 小强
 */
public interface XmPay {

    TransactionRecord startTransfer(String payerId, String payeeId, BigDecimal amount, String remark);

    TransactionRecord payTransfer(Long transferId, String payerId, String remark);

    TransactionRecord cancelTransfer(Long transferId, String payerId, String remark);

    Map deposit(String accountId, BigDecimal amount, String remark);

    Map withdraw(String accountId, BigDecimal amount, String remark);

    Map getAccountInfo(String accountId);

    Map createAccount(String accountId, BigDecimal money);

    Map sealAccount(String accountId);
}
