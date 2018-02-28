package com.topschool.xm.service.weapp;

import com.topschool.xm.exception.OderFoodException;
import com.topschool.xm.model.TodayMenu;

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
     * @param uid    用户id
     * @param foodId 商品id
     */
    void booking(long uid, long foodId);

    /**
     * 用户订餐
     *
     * @param uid     用户id
     * @param foodIds 商品id集合
     */
    void booking(long uid, long[] foodIds) throws OderFoodException;

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
