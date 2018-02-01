package com.topschool.xm.dto;

import com.topschool.xm.model.Partner;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ScratchResult implements Serializable{

    private Integer totalNum;
    private Integer curUserGroup;
    private Boolean partnerStatus;
    private Integer currentScratchResult;
    /**
     * size: 2
     * map key:
     *      key1: id, key2: nickname, key3: money
     */
    private List<Map<String, Object>> topList;
    /**
     * size: 2
     * map key:
     *      key1: id, key2: nickname, key3: money
     */
    private List<Map<String, Object>> lastList;
    /**
     * map key:
     * key1: id, key2: nickname, key3: money
     */
    private List<Map<String, Object>> todayList;
    /**
     * size: 3
     * map key:
     *      key1: id, key2: nickname, key3: money
     */
    private List<Map<String, Object>> totalTop;

    public Integer getCurUserGroup() {
        return curUserGroup;
    }

    public void setCurUserGroup(Integer curUserGroup) {
        this.curUserGroup = curUserGroup;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Boolean getPartnerStatus() {
        return partnerStatus;
    }

    public Integer getCurrentScratchResult() {
        return currentScratchResult;
    }

    public void setCurrentScratchResult(Integer currentScratchResult) {
        this.currentScratchResult = currentScratchResult;
    }

    public void setPartnerStatus(Boolean partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public List<Map<String, Object>> getTopList() {
        return topList;
    }

    public void setTopList(List<Map<String, Object>> topList) {
        this.topList = topList;
    }

    public List<Map<String, Object>> getLastList() {
        return lastList;
    }

    public void setLastList(List<Map<String, Object>> lastList) {
        this.lastList = lastList;
    }

    public List<Map<String, Object>> getTodayList() {
        return todayList;
    }

    public void setTodayList(List<Map<String, Object>> todayList) {
        this.todayList = todayList;
    }

    public List<Map<String, Object>> getTotalTop() {
        return totalTop;
    }

    public void setTotalTop(List<Map<String, Object>> totalTop) {
        this.totalTop = totalTop;
    }
}
