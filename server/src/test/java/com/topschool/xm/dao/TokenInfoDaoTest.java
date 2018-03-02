package com.topschool.xm.dao;

import com.topschool.xm.Application;
import com.topschool.xm.model.TokenInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class TokenInfoDaoTest {

    @Autowired
    private TokenInfoDao tokenInfoDao;

    @Test
    public void insert() {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setSessionId(UUID.randomUUID().toString().replace("-", ""));
        tokenInfo.setCreateTime(System.currentTimeMillis());
        tokenInfo.setExpired(true);
        tokenInfo.setUnionId("asdaqwd");
        tokenInfo.setToken(UUID.randomUUID().toString().replace("-", ""));
        assert 1 == tokenInfoDao.insert(tokenInfo);
    }

    @Test
    public void insertList() {
    }

    @Test
    public void select() {
    }

    @Test
    public void getByUnionId() {
        assertEquals("asdaqwd", tokenInfoDao.getByUnionId("asdaqwd").getUnionId());
    }

    @Test
    public void getByUid() {
    }

    @Test
    public void update() {
        TokenInfo tokenInfo = tokenInfoDao.getByUnionId("asdaqwd");
        tokenInfo.setExpired(false);
        assertEquals(1, tokenInfoDao.update(tokenInfo));
    }
}