package com.topschool.xm.service.impl;

import com.topschool.xm.dao.PartnerMapper;
import com.topschool.xm.model.Partner;
import com.topschool.xm.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DefaultPartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerMapper partnerMapper;

    @Override
    public void register(String uid, String username, String invitationCode, String openId, int area) {
        Partner partner = new Partner();
        partner.setUid(uid);
        partner.setUsername(username);
        partner.setIsAdmin(false);
        partner.setOpenId(openId);
        partner.setCreateTime(System.currentTimeMillis());
        partnerMapper.insert(partner);
    }

    @Override
    public List<Map> getAllPartner() {
        return null;
    }

    @Override
    public void deletePartner(String uid) {

    }

    @Override
    public boolean uidExist(String uid) {
        return partnerMapper.getByUid(uid) != null;
    }
}
