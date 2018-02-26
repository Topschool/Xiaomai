package com.topschool.xm.service;

import com.topschool.xm.exception.BrandNotFoundException;
import com.topschool.xm.model.orderfood.Food;
import com.topschool.xm.model.orderfood.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MenuService {
    List<Restaurant> getAllMenu();

    List<Food> getMenuItem(Integer id);


    void updateMenu(Integer id, String name, MultipartFile logo, String description) throws BrandNotFoundException, IOException;

    void deleteMenu(Integer restaurantId) throws BrandNotFoundException;

    void addMenu(String name, MultipartFile logo, String description) throws IOException;
}
