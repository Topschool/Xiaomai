package com.topschool.xm.service.weapp;

import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.TokenInfo;
import com.topschool.xm.enums.Address;

import java.util.Map;


/**
 * @author 小强
 */
public interface WeappUserService {

    /**
     * 获取token
     *
     * @param code 微信code
     * @param data 微信用户信息
     * @param iv   偏移量
     * @return 生成的token
     */
    TokenInfo getJsSession(String code, String data, String iv);

    /**
     * 通过uid获取用户信息
     *
     * @param sessionId 用户id
     * @return 用户信息
     */
    JSONObject getUserInfo(String sessionId);

    /**
     * 用户入伙
     *
     * @param name      用户名
     * @param address   用户所在地
     * @param sessionId sessionId
     * @return 用户个人信息
     */
    JSONObject register(String name, Address address, String sessionId);

    /**
     * 验证token
     *
     * @param token 待验证的token
     * @return 验证结果
     */
    boolean verificationToken(String token);

    /**
     * uid对应的用户是否存在
     *
     * @param uid uid
     * @return true表示存在，反之不存在
     */
    boolean userExist(long uid);

    boolean userExist(String sessionId);

    /**
     * 获取用户状态
     *
     * @param uid 用户id
     * @return 用户状态信息
     */
    Map getUserStatus(long uid) throws SystemException;
}
