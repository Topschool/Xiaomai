package com.topschool.xm.service.admin;

import com.topschool.xm.Application;
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
public class TodayMenuServiceTest {

    @Autowired
    private TodayMenuService todayMenuService;
    @Test
    public void init() {
        todayMenuService.init();
    }

    @Test
    public void stop() {
    }
}