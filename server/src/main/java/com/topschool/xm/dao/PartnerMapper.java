package com.topschool.xm.dao;

import com.topschool.xm.model.Partner;

import java.util.List;

public interface PartnerMapper {
    void insert(Partner partner);
    Partner getById(Integer id);
    Partner getByOpenId(String openId);
    Partner getByUid(String uid);
    List<Partner> getAll();
//    List<Partner> getBy
}
