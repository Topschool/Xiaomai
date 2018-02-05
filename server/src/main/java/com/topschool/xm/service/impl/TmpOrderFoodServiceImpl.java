package com.topschool.xm.service.impl;

import com.topschool.xm.service.OrderFoodService;
import com.topschool.xm.util.RandomUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TmpOrderFoodServiceImpl implements OrderFoodService {
    public List<Map> getFoodList() {
        List<Map> list = new ArrayList<Map>();
        for (int i = 0; i < 10; i++) {
            list.add(get(i));
        }
        return list;
    }

    public String booking(String userId, Integer foodId) {
//        return "订餐失败";
        return "订餐成功";
//        return "没资格";
    }

    public String cancel(String userId) {
//        return "取消失败";
        return "取消成功";
    }

    public Map getUserStatus(String userId) {
        Map map = new HashMap();
        map.put("id", userId);
        map.put("name", "test_user"+userId);
        map.put("scratchCardStatus", true);
        map.put("orderFoodStatus", true);
//        map.put("orderFoodList", "");
        return map;
    }

    public Map getUsersOrder(String userId){
        List<Map> order = new ArrayList<Map>();
        order.add(get(110));
        order.add(get(111));
        Map map = new HashMap();
        map.put("id", userId);
        map.put("name", "test_user"+userId);
        map.put("orderList", order);
        return map;
    }

    private Map get(int i){
        Map map = new HashMap();
        map.put("id", i);
        map.put("name", "test_food_" + i);
        int price = RandomUtil.generationRandom(15, 25);
        map.put("price", price);
        map.put("difference", price - 20 > 0 ? price - 20 : null);
        return map;
    }
}
