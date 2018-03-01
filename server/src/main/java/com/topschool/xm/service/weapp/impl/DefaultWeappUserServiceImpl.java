package com.topschool.xm.service.weapp.impl;

import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.dao.UserInfoDao;
import com.topschool.xm.model.TokenInfo;
import com.topschool.xm.service.weapp.WeappUserService;
import com.topschool.xm.enums.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小强
 * @date 2018/2/27 22:28
 */
@Service
public class DefaultWeappUserServiceImpl implements WeappUserService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public TokenInfo getToken(String code, String data, String iv) {
        return null;
    }

    @Override
    public JSONObject getUserInfo(String uid) {
        return null;
    }

    @Override
    public JSONObject registe(String name, Address address, String token) {
        return null;
    }

    @Override
    public boolean verificationToken(String token) {
        return false;
    }

    @Override
    public boolean userExist(long uid) {
        return userInfoDao.selectById(uid)!=null;
    }
}
