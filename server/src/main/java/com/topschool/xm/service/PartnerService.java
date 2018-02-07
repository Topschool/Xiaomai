package com.topschool.xm.service;

import java.util.List;
import java.util.Map;

public interface PartnerService {

    void register(String uid, String username, String invitationCode, String openId, int area);

    List<Map> getAllPartner();

    void deletePartner(String uid);

    boolean uidExist(String uid);
}
