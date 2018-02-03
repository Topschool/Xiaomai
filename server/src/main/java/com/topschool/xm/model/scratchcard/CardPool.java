package com.topschool.xm.model.scratchcard;

import com.topschool.xm.util.RandomUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Scope("singleton")
public class CardPool {
    private List<Card> top2;
    private List<Card> last2;
    private List<Card> todayList;
    private List<Integer> pool;
    private List<Card> totalList;
    private int todayTotal;

    /**
     * init card pool
     * @param poolSize pool size
     */
    public void init(int poolSize){
        top2 = new ArrayList<Card>(2);
        last2 = new ArrayList<Card>(2);
        todayList = new ArrayList<Card>(poolSize);
        pool = new ArrayList<Integer>(poolSize);
        pool.addAll(Arrays.asList(new Integer[]{8, 6, 0, 0, 1, 1, 2, 2}));
        for (int i = 0; i < poolSize - 8; i++) {
            pool.add(RandomUtil.generationRandom(3, 5));
        }
    }

    public Integer popFromPool(){
        return this.pool.remove(RandomUtil.generationRandom(0, this.pool.size()));
    }

    public List<Integer> getPool() {
        return pool;
    }

    public void setPool(List<Integer> pool) {
        this.pool = pool;
    }

    public List<Card> getTop2() {
        return top2;
    }

    public void setTop2(List<Card> top2) {
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

    public List<Card> getTotalList() {
        return totalList;
    }

    public void setTotalList(List<Card> totalList) throws Exception {
        if (totalList.size()>3){
            throw new Exception("total list max size is 3");
        }
        this.totalList = totalList;
    }

    public int getTodayTotal() {
        return todayTotal;
    }

    public void setTodayTotal(int todayTotal) {
        this.todayTotal = todayTotal;
    }
}
