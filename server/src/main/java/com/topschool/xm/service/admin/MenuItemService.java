package com.topschool.xm.service.admin;


import com.topschool.xm.exception.BrandNotFoundException;
import com.topschool.xm.exception.FoodNotExistException;

/**
 * @author 小强
 */
public interface MenuItemService {
    /**
     * 更具菜的id更新其他信息
     *
     * @param id    foodId
     * @param name  food名
     * @param price food价格
     * @throws FoodNotExistException id对应的food不存在
     */
    void updateItem(long id, String name, float price) throws FoodNotExistException;

    /**
     * 删除food
     *
     * @param foodId 所需删除的food的id
     * @throws FoodNotExistException id对应的food不存在
     */
    void deleteItem(long foodId) throws FoodNotExistException;

    /**
     * 添加新的food
     *
     * @param name         名称
     * @param price        价格
     * @param brandId 所属品牌的id
     * @throws BrandNotFoundException 对应的品牌不存在
     */
    void addItem(String name, float price, long brandId) throws BrandNotFoundException;
}
