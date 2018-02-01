package com.topschool.xm.service;

import com.topschool.xm.model.Partner;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ScratchCardService {

    Integer scratch(String wxId);

    Partner getPartnerInfo(String wxId);

    Boolean getPartnerTodayStatus(String wxId);

    List<Map<String, Object>> getTodayResult(Integer page, Integer pageSize);

    List<Map<String, Object>> getTodayTopList(Integer page, Integer pageSize);

    List<Map<String, Object>> getTodayLastList(Integer page, Integer pageSize);

    List<Map<String, Object>> getTotalTopResult(Integer page, Integer pageSize);

    Map getScratchSummary(String id);

    Integer getTodayTotal(Date date);

}
