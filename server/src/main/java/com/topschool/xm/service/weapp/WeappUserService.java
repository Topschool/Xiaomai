package com.topschool.xm.service.weapp;

import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.model.TokenInfo;
import com.topschool.xm.util.Address;


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
    TokenInfo getToken(String code, String data, String iv);

    /**
     * 通过uid获取用户信息
     *
     * @param uid 用户id
     * @return 用户信息
     */
    JSONObject getUserInfo(String uid);

    /**
     * 用户入伙
     *
     * @param name    用户名
     * @param address 用户所在地
     * @return 用户个人信息
     */
    JSONObject registe(String name, Address address, String token);

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
}
