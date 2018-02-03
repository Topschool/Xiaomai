package com.topschool.xm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ScratchCardService {

    void initCardPool(Integer size);

    Integer scratch(String wxId) throws Exception;

    boolean getPartnerTodayStatus(String wxId);

    List<Map<String, Object>> getTodayResult(Integer page, Integer pageSize);

    List<Map<String, Object>> getTodayTopList(Integer page, Integer pageSize);

    List<Map<String, Object>> getTodayLastList(Integer page, Integer pageSize);

    List<Map> getTotalTopResult(Integer page, Integer pageSize);

    Map getScratchSummary(String id);

    Integer getTodayTotal(Date date);

}
