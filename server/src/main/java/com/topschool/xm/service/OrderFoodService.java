package com.topschool.xm.service;

import java.util.Map;

public interface OrderFoodService {

    /**
     * 获取今日菜单
     *
     * @return 菜单集合
     */
    Map getFoodList();

    String booking(String userId, Integer foodId) throws Exception;

    String cancel(String userId);

    Map getUserStatus(String userId);

    Map getUsersOrder(String userId);

    void initOrderFoodSystem(int restaurantId);

    boolean submit() throws Exception;

    boolean clean();
}
