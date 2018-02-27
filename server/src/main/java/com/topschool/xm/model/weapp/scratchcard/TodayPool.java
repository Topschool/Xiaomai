package com.topschool.xm.model.weapp.scratchcard;

import com.topschool.xm.util.ScratchCardStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 小强
 */
@Component
public class TodayPool {
    private Card[] top2;
    private List<Card> last2;
    private List<Card> todayList;
    private List<Card> pool;
    private List<Map> totalTop3;
    private int todayTotal;
    private ScratchCardStatus status = ScratchCardStatus.UNINIT;

    public List<Card> getPool() {
        return pool;
    }

    public void setPool(List<Card> pool) {
        this.pool = pool;
    }

    public Card[] getTop2() {
        return top2;
    }

    public List<Map> getTotalTop3() {
        return totalTop3;
    }

    public void setTotalTop3(List<Map> totalTop3) {
        this.totalTop3 = totalTop3;
    }

    public void setTop2(Card[] top2) {
        this.top2 = top2;
    }

    public List<Card> getLast2() {
        return last2;
    }

    public void setLast2(List<Card> last2) {
        this.last2 = last2;
    }

    public List<Card> getTodayList() {
        return todayList;
    }

    public void setTodayList(List<Card> todayList) {
        this.todayList = todayList;
    }


    public int getTodayTotal() {
        return todayTotal;
    }

    public void setTodayTotal(int todayTotal) {
        this.todayTotal = todayTotal;
    }

    public ScratchCardStatus getStatus() {
        return status;
    }

    public void setStatus(ScratchCardStatus status) {
        this.status = status;
    }
}
