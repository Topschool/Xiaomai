package com.topschool.xm.dao.orderfood;

import com.topschool.xm.Application;
import com.topschool.xm.model.Brand;
import com.topschool.xm.model.BrandFood;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class BrandFoodDaoTest {

    @Autowired
    private BrandFoodDao brandFoodDao;

    @Test
    public void insert() {
        BrandFood food = new BrandFood("test food", BigDecimal.valueOf(22), 2L);
        food.setImgUrl("gfhjds.png");
        assertEquals(1, brandFoodDao.insert(food));
    }

    @Test
    public void insertList() {
    }

    @Test
    public void select() {

    }

    @Test
    public void selectById() {
        assertEquals("gfhjds.png", brandFoodDao.selectById(21).getImgUrl());
    }

    @Test
    public void update() {
        BrandFood food = brandFoodDao.selectById(23);
        food.setPrice(BigDecimal.valueOf(25));
        assertEquals(1, brandFoodDao.update(food));
    }
}