package com.topschool.xm.service.impl;

import com.topschool.xm.service.ScratchCardService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TmpScratchCardServiceImpl implements ScratchCardService {
    public void initCardPool(Integer size) {
    }

    public Integer scratch(String wxId) {
        return 1;
    }

    public boolean getPartnerTodayStatus(String wxId) {
        return true;
    }

    public List<Map<String, Object>> getTodayResult(Integer page, Integer pageSize) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", i);
            map.put("nickname", "test_user_" + i);
            map.put("money", i);
            map.put("url", "../../images/004.png");
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> getTodayTopList(Integer page, Integer pageSize) {
        List list = getTodayResult(0, 10);
        return Arrays.asList(new HashMap<String, Object>(), (Map<String, Object>) list.get(list.size() - 2));
    }

    public List<Map<String, Object>> getTodayLastList(Integer page, Integer pageSize) {
        List list = getTodayResult(0, 10);
        return Arrays.asList((Map<String, Object>) list.get(1), (Map<String, Object>) list.get(2));
    }

    public List<Map> getTotalTopResult(Integer page, Integer pageSize) {
        List list = getTodayResult(0, 10);
        return Arrays.asList((Map) list.get(list.size() - 1), (Map) list.get(list.size() - 2), (Map) list.get(list.size() - 3));
    }

    public Map getScratchSummary(String id) {
        return null;
    }

    public Integer getTodayTotal(Date date) {
        List<Map<String, Object>> list = getTodayResult(0, 10);
        Integer total = 0;
        for (Map<String, Object> stringObjectMap : list) {
            total += (Integer) stringObjectMap.get("money");
        }
        return total;
    }
}
