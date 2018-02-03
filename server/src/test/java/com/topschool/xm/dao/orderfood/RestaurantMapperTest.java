package com.topschool.xm.dao.orderfood;

import com.topschool.xm.BaseTest;
import com.topschool.xm.model.orderfood.Restaurant;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestaurantMapperTest extends BaseTest {

    private RestaurantMapper restaurantMapper = getBean(RestaurantMapper.class);

    @Test
    public void insert() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("A");
        restaurant.setLogo("a.png");
        restaurant.setDescription("very delicious");
        assert restaurantMapper.insert(restaurant);
    }

    @Test
    public void getById() {
        Restaurant r = restaurantMapper.getById(1);
        assertNotNull(r);
    }

    @Test
    public void getAll() {
        assertNotNull(restaurantMapper.getAll());
    }

    @Test
    public void deleteById() {
        assert restaurantMapper.deleteById(1);
    }

    @Test
    public void update() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(2);
        restaurant.setName("A");
        restaurant.setLogo("a.png");
        restaurant.setDescription("good");
        assert restaurantMapper.update(restaurant);
    }
}