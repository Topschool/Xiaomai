package com.topschool.xm.service;

import com.topschool.xm.exception.FoodNotExistException;

import javax.naming.NoPermissionException;
import java.util.Map;

public interface OrderFoodService {

    /**
     * 获取今日菜单
     *
     * @return 菜单集合
     */
    Map getFoodList();

    /**
     * 用户订餐
     *
     * @param userId 用户id
     * @param foodId food id
     * @return 订餐结果
     * @throws NoPermissionException 无订餐资格
     * @throws FoodNotExistException 不存在food
     */
    String booking(String userId, Integer foodId) throws NoPermissionException, FoodNotExistException;

    /**
     * 取消订餐，取消当日所有记录
     *
     * @param userId 用户id
     * @return 操作结果
     */
    String cancel(String userId);

    /**
     * 获取用户状态，包括订餐状态和刮卡状态
     *
     * @param userId 用户id
     * @return 状态信息
     */
    Map getUserStatus(String userId);

    /**
     * 获取用户今日订单
     *
     * @param userId 用户id
     * @return 今日订单信息
     */
    Map getUsersOrder(String userId) throws FoodNotExistException;

    /**
     * 初始化今日菜单
     *
     * @param restaurantId 饭馆编号
     */
    void initOrderFoodSystem(int restaurantId);

    /**
     * 提交今日订单
     *
     * @return
     * @throws Exception
     */
    @Deprecated
    boolean submit() throws Exception;

    /**
     * 清除订单缓存
     *
     * @return
     */
    boolean clean();
}
