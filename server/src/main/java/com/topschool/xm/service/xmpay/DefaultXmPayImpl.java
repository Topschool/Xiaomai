package com.topschool.xm.service.xmpay;

import com.topschool.xm.dao.xmpay.AccountDao;
import com.topschool.xm.dao.xmpay.TransactionRecordDao;
import com.topschool.xm.enums.SystemError;
import com.topschool.xm.enums.TransactionStatus;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.xmpay.Account;
import com.topschool.xm.model.xmpay.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 小强
 */
@Service
public class DefaultXmPayImpl implements XmPay {

    @Autowired
    private AccountDao accountDao;
    private TransactionRecordDao transactionRecordDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public TransactionRecord startTransfer(String payerId, String payeeId, BigDecimal amount, String remark) {
        Account payer = accountDao.selectById(payerId);
        Account payee = accountDao.selectById(payeeId);
        if (payee==null||payer==null){
            throw new SystemException(SystemError.ACCOUNT_NOT_EXIST);
        }
        if (payee.getBalance().doubleValue()<amount.doubleValue()) {
            throw new SystemException(SystemError.ACCOUNT_INSUFFICIENT_BALANCE);
        }
        payer.setBalance(payer.getBalance().subtract(amount));
        payee.setBalance(payee.getBalance().add(amount));
        TransactionRecord record = new TransactionRecord(null, payerId, payerId, amount, remark);
        accountDao.update(payee);
        accountDao.update(payer);
        transactionRecordDao.insert(record);
        return record;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public TransactionRecord payTransfer(Long transferId, String payerId, String remark) {
        TransactionRecord record = transactionRecordDao.selectById(transferId);
        if (record==null) {
            throw new SystemException(SystemError.TRANSACTION_NOT_EXIST);
        }
        if (record.getStatus()==TransactionStatus.FINISH){
            throw new SystemException(SystemError.NOT_NEEDED_REPAY);
        }
        Account payer = accountDao.selectById(payerId);
        if (payer.getBalance().doubleValue()<record.getAmount().doubleValue()) {
            throw new SystemException(SystemError.ACCOUNT_INSUFFICIENT_BALANCE);
        }
        //从用户账户扣除应交金额
        payer.setBalance(payer.getBalance().subtract(record.getAmount()));
        //修改记录状态
        record.setStatus(TransactionStatus.FINISH);
        record.setFinishTime(System.currentTimeMillis());
        //更新数据库记录
        accountDao.update(payer);
        transactionRecordDao.update(record);
        return record;
    }

    @Override
    public TransactionRecord cancelTransfer(Long transferId, String payerId, String remark) {
        TransactionRecord record = transactionRecordDao.selectById(transferId);
        if (record==null) {
            throw new SystemException(SystemError.TRANSACTION_NOT_EXIST);
        }
        if (record.getStatus()==TransactionStatus.FINISH){
            throw new SystemException(SystemError.NOT_NEEDED_REPAY);
        }
        if (!payerId.equals(record.getPayerId())){
            throw new SystemException(SystemError.NO_PERMISSION_MODIFY_TRANSACTION);
        }
        record.setFinishTime(System.currentTimeMillis());
        record.setStatus(TransactionStatus.CANCELLED);
        record.setRemark(remark);
        transactionRecordDao.update(record);
        return record;
    }

    @Override
    public Map deposit(String accountId, BigDecimal amount, String remark) {
        return null;
    }

    @Override
    public Map withdraw(String accountId, BigDecimal amount, String remark) {
        return null;
    }

    @Override
    public Map getAccountInfo(String accountId) {
        return null;
    }

    @Override
    public Map createAccount(String accountId, BigDecimal money) {
        return null;
    }

    @Override
    public Map sealAccount(String accountId) {
        return null;
    }

}
