package com.topschool.xm.service;

import java.util.Map;

public interface OrderFoodService {

    Map getFoodList();

    String booking(String userId, Integer foodId);

    String cancel(String userId);

    Map getUserStatus(String userId);

    Map getUsersOrder(String userId);

    void initOrderFoodSystem(int restaurantId);

    boolean submit() throws Exception;

    boolean clean();
}
