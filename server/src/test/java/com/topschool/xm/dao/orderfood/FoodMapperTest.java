package com.topschool.xm.dao.orderfood;

import com.topschool.xm.BaseTest;
import com.topschool.xm.model.orderfood.Food;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodMapperTest extends BaseTest{

    private FoodMapper foodMapper = this.getBean(FoodMapper.class);

    @Test
    public void insert() {
    }

    @Test
    public void getById() {
        Food food = foodMapper.getById(12);
        System.out.println();
    }

    @Test
    public void getAll() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getByRestaurantId() {
    }
}