package com.topschool.xm.service.weapp;

import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.orderfood.Order;
import com.topschool.xm.model.orderfood.TodayMenu;

import java.util.Map;

/**
 * @author 小强
 * @date 2018/2/27 22:44
 */
public interface OrderFoodService {

    /**
     * 获取用户今日订单
     *
     * @param uid 用户id
     * @return 用户今日订单
     */
    Map getUserTodayOrder(long uid);

    /**
     * 获取今日菜单
     *
     * @return 今日菜单
     */
    TodayMenu getTodayMenu();

    /**
     * 用户订餐
     *
     * @param order 表单
     * @throws SystemException 出错
     */
    void booking(Order order) throws SystemException;

    /**
     * 取消uid用户的今日的foodId的订单
     *
     * @param uid    用户id
     * @param foodId 商品id
     */
    void cancel(long uid, long foodId);

    /**
     * 取消uid用户的今日的的订单
     *
     * @param uid 用户id
     */
    void cancel(long uid);

    /**
     * 获取用户今日点餐状态
     *
     * @param uid uid
     * @return true 已点餐，反之未点餐
     */
    boolean getUserTodayOrderStatus(long uid);

}
