package com.topschool.xm.service.weapp;

import java.util.List;
import java.util.Map;

/**
 * @author 小强
 * @date 2018/2/27 22:44
 */
public interface OrderFoodService {

    void start();
    void end();
    Map getUserTodayOrder(long uid);
    List<Map> getTodayMenu();
    List<Map> booking(long uid, long foodId);
    boolean cancel(long uid, long foodId);
    boolean getUserTodayOrderStatus(long uid);

}
