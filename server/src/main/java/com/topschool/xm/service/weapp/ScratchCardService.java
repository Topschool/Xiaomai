package com.topschool.xm.service.weapp;

import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.exception.ScratchCardException;

import java.util.List;
import java.util.Map;

/**
 * @author 小强
 */
public interface ScratchCardService {

    /**
     * 刮卡
     *
     * @param uid 用户id
     * @return 刮到的结果
     * @throws ScratchCardException 出错
     */
    Map scratch(long uid) throws ScratchCardException;

    /**
     * 获取今日用户刮卡状态
     *
     * @param uid 用户id
     * @return 用户刮卡状态 true 已刮卡，false 为刮卡
     */
    boolean getUserTodayStatus(long uid);

    /**
     * 获取今天所有人员刮卡记录
     *
     * @return 集合
     */
    List<Map> getTodayResult();

    /**
     * 获取今日最高的成员
     *
     * @return 集合
     */
    List<Map> getTodayTopList();

    /**
     * 获取今日最低的成员
     *
     * @return 集合
     */
    List<Map> getTodayLastList();

    /**
     * 获取当月总和最高的成员
     *
     * @return 集合
     */
    List<Map> getTotalTopResult();

    /**
     * 获取用户刮卡概括信息，包括月总额，最高，最低，平均值
     *
     * @param id 用户id
     * @return 信息集
     */
    JSONObject getScratchSummary(long id);

    /**
     * 获取当天所有刮卡总额
     *
     * @return 总额
     */
    Integer getTodayTotal();

}
