package com.topschool.xm.service.impl;

import com.topschool.xm.dao.PartnerMapper;
import com.topschool.xm.exception.UserNameNotFoundException;
import com.topschool.xm.model.Partner;
import com.topschool.xm.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultPartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerMapper partnerMapper;

    @Override
    public Map register(String uid, String username, String invitationCode, String openId, int area) {
        Map userInfo = new HashMap();
        Partner partner = new Partner();
        partner.setUid(uid);
        partner.setUsername(username);
        partner.setIsAdmin(false);
        partner.setOpenId(openId);
        partner.setCreateTime(System.currentTimeMillis());
        partnerMapper.insert(partner);
        userInfo.put("username", username);
        userInfo.put("uid", uid);
        return userInfo;
    }

    @Override
    public String getUsername(Integer uid, String password) throws UserNameNotFoundException, NoPermissionException {
        Partner u = partnerMapper.getById(uid);
        if (null == u) {
            throw new UserNameNotFoundException("id不存在");
        }
        if (!u.getIsAdmin()) {
            throw new NoPermissionException("普通用户无权登录管理系统");
        }
        return u.getPassword().equals(password) ? u.getUsername() : null;
    }

    @Override
    public List<Map> getAllPartner() {
        return null;
    }

    @Override
    public void deletePartner(String uid) {

    }

    @Override
    public Map getUserInfoByOpenId(String openId) {
        Map userInfo = new HashMap();
        Partner u = partnerMapper.getByOpenId(openId);
        if (null == u) {
            return null;
        }
        userInfo.put("uid", u.getUid());
        userInfo.put("username", u.getUsername());
        return userInfo;
    }

    @Override
    public boolean uidExist(String uid) {
        return partnerMapper.getByUid(uid) != null;
    }
}
