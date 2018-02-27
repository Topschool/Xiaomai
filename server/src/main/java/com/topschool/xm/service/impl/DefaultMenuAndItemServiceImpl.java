package com.topschool.xm.service.impl;

import com.dingup.util.GOssUtil;
import com.topschool.xm.exception.BrandNotFoundException;
import com.topschool.xm.exception.FoodNotExistException;
import com.topschool.xm.service.MenuItemService;
import com.topschool.xm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 小强
 */
@Service
public class DefaultMenuAndItemServiceImpl implements MenuItemService, MenuService {

    private static final String FOLDER = "dinner/img";
    private static final String BUCKET_NAME = "topschool-xiaomai";

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Override
    public List<Food> getMenuItem(Integer id) {
        return foodMapper.getByRestaurantId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateItem(Integer id, String name, float price, String logo) throws FoodNotExistException {
        Food food = foodMapper.getById(id);
        if (food == null) {
            throw new FoodNotExistException("id为%d的food不存在");
        }
        food.setName(name);
        food.setPrice(price);
        food.setLogoUrl(logo);
        foodMapper.update(food);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteItem(Integer foodId) throws FoodNotExistException {
        Food food = foodMapper.getById(foodId);
        if (food == null) {
            throw new FoodNotExistException("id为%d的food不存在");
        }
        foodMapper.deleteById(foodId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addItem(String name, float price, String logo, Integer restaurantId) throws BrandNotFoundException {
        Restaurant restaurant = restaurantMapper.getById(restaurantId);
        if (restaurant == null) {
            throw new BrandNotFoundException(String.format("id为%d的品牌不存在", restaurantId));
        }
        Food food = new Food();
        food.setName(name);
        food.setPrice(price);
        food.setRestaurantId(restaurantId);
        foodMapper.insert(food);
    }

    @Override
    public List<Restaurant> getAllMenu() {
        return restaurantMapper.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateMenu(Integer id, String name, MultipartFile logo, String description) throws BrandNotFoundException, IOException {
        Restaurant restaurant = restaurantMapper.getById(id);
        if (restaurant == null) {
            throw new BrandNotFoundException(String.format("id为%d的品牌不存在", id));
        }
        GOssUtil.deleteObjectInOss(restaurant.getLogo());
        String filename = String.valueOf(System.currentTimeMillis());
        String url = GOssUtil.putObjectToOss(logo.getInputStream(), logo.getSize(), logo.getContentType(), FOLDER, filename, BUCKET_NAME);
        restaurant.setDescription(description);
        restaurant.setLogo(url);
        restaurant.setName(name);
        restaurantMapper.update(restaurant);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMenu(Integer restaurantId) throws BrandNotFoundException {
        Restaurant restaurant = restaurantMapper.getById(restaurantId);
        if (restaurant == null) {
            throw new BrandNotFoundException(String.format("id为%d的品牌不存在", restaurantId));
        }
        restaurantMapper.deleteById(restaurantId);
        GOssUtil.deleteObjectInOss(restaurant.getLogo());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addMenu(String name, MultipartFile logo, String description) throws IOException {
        String filename = String.valueOf(System.currentTimeMillis());
        String url = GOssUtil.putObjectToOss(logo.getInputStream(), logo.getSize(), logo.getContentType(), FOLDER, filename, BUCKET_NAME);
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setLogo(url);
        restaurant.setDescription(description);
        restaurantMapper.insert(restaurant);
    }
}
