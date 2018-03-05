package com.topschool.xm.dao;

import com.topschool.xm.Application;
import com.topschool.xm.enums.Address;
import com.topschool.xm.model.user.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserInfoDaoTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("里斯");
        userInfo.setUnionId("asfddghsef21edsa");
        userInfo.setAddress(Address.BJ);
        userInfo.setCreateTime(System.currentTimeMillis());
        userInfo.setAdmin(false);
        assertEquals(1, userInfoDao.insert(userInfo));
    }

    @Test
    public void select() {
    }

    @Test
    public void update() {
    }

    @Test
    public void selectById() {
    }

    @Test
    public void selectByUnionId() {
        UserInfo userInfo = userInfoDao.selectByUnionId("asfddghsef21edsa");
        System.out.println();
    }
}