package com.topschool.xm.service;

import java.util.List;
import java.util.Map;

public interface OrderFoodService {

    List<Map> getFoodList();

    String booking(String userId, Integer foodId);

    String cancel(String userId);

    Map getUserStatus(String userId);

    Map getUsersOrder(String userId);
}
