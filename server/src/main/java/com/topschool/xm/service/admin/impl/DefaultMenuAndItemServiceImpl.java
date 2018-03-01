package com.topschool.xm.service.admin.impl;

import com.dingup.util.GOssUtil;
import com.topschool.xm.dao.orderfood.BrandDao;
import com.topschool.xm.dao.orderfood.BrandFoodDao;
import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.Brand;
import com.topschool.xm.model.BrandFood;
import com.topschool.xm.model.TodayMenu;
import com.topschool.xm.service.admin.MenuItemService;
import com.topschool.xm.service.admin.MenuService;
import com.topschool.xm.service.admin.TodayMenuService;
import com.topschool.xm.enums.OrderFoodStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 小强
 */
@Service
public class DefaultMenuAndItemServiceImpl implements MenuItemService, MenuService, TodayMenuService {

    @Value("${oss.folder}")
    private String folder;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Autowired
    private BrandFoodDao foodDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private TodayMenu todayMenu;

    @Override
    public void updateItem(long id, String name, float price) throws SystemException {
        BrandFood food = foodDao.selectById(id);
        if (food == null) {
            throw new SystemException(SystemError.FOOD_NOT_EXIST);
        }
        food.setName(name);
        food.setPrice(BigDecimal.valueOf(price));
        foodDao.update(food);
    }

    @Override
    public void deleteItem(long foodId) throws SystemException {
        BrandFood food = foodDao.selectById(foodId);
        if (food == null) {
            throw new SystemException(SystemError.FOOD_NOT_EXIST);
        }
        food.setSelected(false);
        foodDao.update(food);
    }

    @Override
    public void addItem(String name, float price, long brandId) throws SystemException {
        BrandFood food = new BrandFood(name, BigDecimal.valueOf(price), brandId);
        if (brandDao.selectById(brandId)==null) {
            throw new SystemException(SystemError.BRAND_NOT_EXIST);
        }
        foodDao.insert(food);
    }

    @Override
    public List<Brand> getAllMenu() {
        return null;
    }

    @Override
    public List<BrandFood> getMenuItem(Integer id) {
        return null;
    }

    @Override
    public void updateMenu(Integer id, String name, MultipartFile logo, String description) throws SystemException, IOException {
        Brand brand = brandDao.selectById(id);
        if (brand == null) {
            throw new SystemException(SystemError.BRAND_NOT_EXIST);
        }
        brand.setName(name);
        GOssUtil.deleteObjectInOss(brand.getLogoUrl());
        String newUrl = GOssUtil.putObjectToOss(logo.getInputStream(), logo.getSize(), logo.getContentType(), folder, String.valueOf(System.currentTimeMillis()) + ".png", bucketName);
        brand.setLogoUrl(newUrl);
        brand.setDescription(description);
        brandDao.update(brand);
    }

    @Override
    public void deleteMenu(long brandId) throws SystemException {
        Brand brand = brandDao.selectById(brandId);
        if (brand == null) {
            throw new SystemException(SystemError.BRAND_NOT_EXIST);
        }
        brand.setSelected(false);
        brandDao.update(brand);
    }

    @Override
    public void addMenu(String name, MultipartFile logo, String description) throws IOException {
        Brand brand = new Brand();
        brand.setName(name);
        String url = GOssUtil.putObjectToOss(logo.getInputStream(), logo.getSize(), logo.getContentType(), folder, String.valueOf(System.currentTimeMillis()) + ".png", bucketName);
        brand.setLogoUrl(url);
        brand.setDescription(description);
        brandDao.insert(brand);
    }

    @Override
    public void init() {
        Brand brand = brandDao.selectTodaySelect();
        List<BrandFood> items = foodDao.select(new BrandFood(brand.getId(), true));
        todayMenu.setTodayBrand(brand);
        todayMenu.setStatus(OrderFoodStatus.STARTING);
        todayMenu.setMenu(items);
    }

    @Override
    public void stop() {
        todayMenu.setMenu(null);
        todayMenu.setStatus(OrderFoodStatus.STOPED);
        todayMenu.setTodayBrand(null);
    }
}
