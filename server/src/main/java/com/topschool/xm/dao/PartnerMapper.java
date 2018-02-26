package com.topschool.xm.dao;

import com.topschool.xm.model.Partner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小强
 */
public interface PartnerMapper {
    void insert(Partner partner);

    Partner getById(Integer id);

    Partner getByOpenId(String openId);

    Partner getByUid(String uid);

    List<Partner> getAll();
//    List<Partner> getBy
}
