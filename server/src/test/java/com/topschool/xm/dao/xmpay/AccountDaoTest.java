package com.topschool.xm.dao.xmpay;

import com.topschool.xm.Application;
import com.topschool.xm.enums.AccountStatus;
import com.topschool.xm.enums.SystemError;
import com.topschool.xm.model.xmpay.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void insert() {
        Account account = new Account();
        account.setId("zhangsan");
        account.setBalance(BigDecimal.valueOf(100));
        account.setIsSysAccount(false);
        account.setStatus(AccountStatus.NORMAL);
        account.setUpdateTime(System.currentTimeMillis());
        assertEquals(1, accountDao.insert(account));
    }

    @Test
    public void insertList() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("lisi", BigDecimal.valueOf(100)));
        accounts.add(new Account("wangwu", BigDecimal.valueOf(100)));
        accounts.add(new Account("zhaoliu", BigDecimal.valueOf(100)));
        assertEquals(3, accountDao.insertList(accounts));
    }

    @Test
    public void select() {
        Account select = new Account();
        select.setIsSysAccount(false);
        List list = accountDao.select(select);
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void selectById() {
        Account account = accountDao.selectById("zhangsan");
        assertNotNull(account);
    }

    @Test
    public void update() {
        Account account = accountDao.selectById("zhangsan");
        account.setBalance(account.getBalance().add(BigDecimal.valueOf(100)));
        assertEquals(1, accountDao.update(account));
    }
}