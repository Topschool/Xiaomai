package com.topschool.xm.dao.xmpay;

import com.topschool.xm.Application;
import com.topschool.xm.enums.TransactionStatus;
import com.topschool.xm.model.xmpay.TransactionRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class TransactionRecordDaoTest {

    @Autowired
    private TransactionRecordDao transactionRecordDao;

    @Test
    public void insert() {
        TransactionRecord record = new TransactionRecord(null, "zhangsan", "lisi", BigDecimal.valueOf(50), "父债子还");
        System.out.println("---------------");
        System.out.println(record);
        System.out.println("---------------");
        System.out.println(transactionRecordDao.insert(record));
        System.out.println("---------------");
        System.out.println(record);
    }

    @Test
    public void insertList() {
        TransactionRecord record1 = new TransactionRecord(null, "zhangsan", "lisi", BigDecimal.valueOf(50), "父债子还");
        TransactionRecord record2 = new TransactionRecord(null, "lisi", "zhangsan", BigDecimal.valueOf(50), "父债子还");
        TransactionRecord record3 = new TransactionRecord(null, "zhangsan", "lisi", BigDecimal.valueOf(50), "父债子还");
        List list = Arrays.asList(record1, record2, record3);
        transactionRecordDao.insertList(list);
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void select() {
        TransactionRecord record = new TransactionRecord();
        record.setPayeeId("lisi");
        List list = transactionRecordDao.select(record);
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void update() {
        TransactionRecord record = transactionRecordDao.selectById(19L);
        System.out.println(record);
        record.setRemark("凭本事借的钱，为什么要还");
        record.setStatus(TransactionStatus.CANCELLED);
        record.setFinishTime(System.currentTimeMillis());
        transactionRecordDao.update(record);
        System.out.println(record);
    }
}