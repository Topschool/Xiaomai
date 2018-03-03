package com.topschool.xm.service.weapp.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.dao.TokenInfoDao;
import com.topschool.xm.dao.UserInfoDao;
import com.topschool.xm.dao.UserStatusDao;
import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.TokenInfo;
import com.topschool.xm.model.UserInfo;
import com.topschool.xm.model.UserStatus;
import com.topschool.xm.service.weapp.WeappUserService;
import com.topschool.xm.enums.Address;
import com.topschool.xm.util.AESUtil;
import com.topschool.xm.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 小强
 * @date 2018/2/27 22:28
 */
@Service
public class DefaultWeappUserServiceImpl implements WeappUserService {

    @Value("${weapp.getSessionKeyUrlTemplate}")
    private String getSessionKeyUrlTemplate;
    @Value("${weapp.appid}")
    private String appID;
    @Value("${weapp.secret}")
    private String appSecret;


    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserStatusDao userStatusDao;
    @Autowired
    private TokenInfoDao tokenInfoDao;

    @Override
    public TokenInfo getJsSession(String code, String data, String iv) {
        JSONObject result = HttpUtil.getJson(String.format(getSessionKeyUrlTemplate, appID, appSecret, code));
//        if (result.get())
        String wechatUserInfoStr = AESUtil.decrypt(data, result.getString("session_key"), iv, "utf-8");
        JSONObject wechatUserInfo = JSONObject.parseObject(wechatUserInfoStr);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setSessionId(UUID.randomUUID().toString().replace("-", ""));
        String unionId = (String) wechatUserInfo.get("unionId");
        if (unionId==null) {
            throw new SystemException(SystemError.WEAPP_NOT_BOUND_OPEN_PLATFORM);
        }
        tokenInfo.setUnionId(unionId);
        tokenInfo.setCreateTime(System.currentTimeMillis());
        if (tokenInfoDao.getByUnionId(tokenInfo.getUnionId()) == null) {
            tokenInfoDao.insert(tokenInfo);
            return tokenInfo;
        }
        tokenInfoDao.update(tokenInfo);
        return tokenInfo;
    }

    @Override
    public JSONObject getUserInfo(String sessionId) {
        UserInfo userInfo = getUserInfoBySessionId(sessionId);
        if (userInfo == null) {
            throw new SystemException(SystemError.USER_NOT_EXIST);
        }
        filterUser(userInfo);
        return (JSONObject) JSONObject.toJSON(userInfo);
    }

    @Override
    public JSONObject register(String name, Address address, String sessionId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setAdmin(false);
        userInfo.setCreateTime(System.currentTimeMillis());
        TokenInfo tokenInfo = tokenInfoDao.getBySessionId(sessionId);
        assert null != tokenInfo;
        userInfo.setUnionId(tokenInfo.getUnionId());
        userInfo.setAddress(address);
        userInfoDao.insert(userInfo);
        userInfo = userInfoDao.selectByUnionId(tokenInfo.getUnionId());
        filterUser(userInfo);
        return (JSONObject) JSON.toJSON(userInfo);
    }

    @Override
    public boolean verificationToken(String token) {
        return false;
    }

    @Override
    public boolean userExist(long uid) {
        return userInfoDao.selectById(uid) != null;
    }

    @Override
    public boolean userExist(String sessionId) {
        return getUserInfoBySessionId(sessionId) != null;
    }

    @Override
    public Map getUserStatus(long uid) throws SystemException {
        UserStatus userStatus = userStatusDao.selectById(uid);
        if (userStatus == null) {
            throw new SystemException(SystemError.ORDER_FOOD_NO_PERMISSION);
        }
        UserInfo userInfo = userInfoDao.selectById(uid);
        Map<String, Object> map = new HashMap<>(5);
        map.put("uid", uid);
        map.put("name", userInfo.getName());
        map.put("allowOrdering", userStatus.getAllowOrdering());
        return map;
    }

    private UserInfo getUserInfoBySessionId(String sessionId) {
        TokenInfo tokenInfo = tokenInfoDao.getBySessionId(sessionId);
        if (tokenInfo == null) {
            throw new SystemException(SystemError.TOKEN_ILLEGAL);
        }
        return userInfoDao.selectByUnionId(tokenInfo.getUnionId());
    }

    private void filterUser(UserInfo userInfo){
        userInfo.setCreateTime(null);
        userInfo.setUnionId(null);
        userInfo.setAdmin(null);
        userInfo.setNickname(null);
    }
}
