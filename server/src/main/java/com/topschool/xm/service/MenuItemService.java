package com.topschool.xm.service;


import com.topschool.xm.exception.BrandNotFoundException;
import com.topschool.xm.exception.FoodNotExistException;

public interface MenuItemService {
    void updateItem(Integer id, String name, float price, String logo) throws FoodNotExistException;

    void deleteItem(Integer foodId) throws FoodNotExistException;

    void addItem(String name, float price, String logo, Integer restaurantId) throws BrandNotFoundException;
}
