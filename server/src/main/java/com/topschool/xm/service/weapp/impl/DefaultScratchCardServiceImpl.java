package com.topschool.xm.service.weapp.impl;

import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.dao.UserInfoDao;
import com.topschool.xm.dao.scratchcard.CardDao;
import com.topschool.xm.dao.scratchcard.ScratchRecordDao;
import com.topschool.xm.exception.ScratchCardException;
import com.topschool.xm.exception.UserNotFoundException;
import com.topschool.xm.model.Card;
import com.topschool.xm.model.ScratchRecord;
import com.topschool.xm.model.TodayPool;
import com.topschool.xm.model.UserInfo;
import com.topschool.xm.service.weapp.ScratchCardService;
import com.topschool.xm.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author 小强
 */
@Service
public class DefaultScratchCardServiceImpl implements ScratchCardService {

    @Autowired
    private ScratchRecordDao scratchRecordDao;
    @Autowired
    private CardDao cardDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private TodayPool todayPool;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Map scratch(long uid) throws ScratchCardException, UserNotFoundException {
        UserInfo userInfo = userInfoDao.selectById(uid);
        if (userInfo==null) {
            throw new UserNotFoundException();
        }
        if (getUserTodayStatus(uid)) {
            throw new ScratchCardException("今日已刮卡");
        }
        if (todayPool.getStatus().getCode() == 0) {
            throw new ScratchCardException("今日刮卡已经结束");
        }
        if (todayPool.getStatus().getCode() == -1) {
            throw new ScratchCardException("今日刮卡未开始");
        }
        //随机获取一个整数，作为抽取的位置
        int randomPosition = RandomUtil.generationRandom(0, todayPool.getPool().size());
        //将randomPosition位置的card从pool中移出
        Card card = todayPool.getPool().remove(randomPosition);
        //初始化card中的用户信息
        card.setScratched(true);
        card.setUid(uid);
        card.setName(userInfo.getName());
        //将记录存入用户的刮卡记录表中
        ScratchRecord scratchRecord = new ScratchRecord();
        scratchRecord.setCreateTime(System.currentTimeMillis());
        scratchRecord.setResult(card.getPrice());
        scratchRecord.setUid(uid);
        scratchRecordDao.insert(scratchRecord);
        //将记录存入今日刮卡状态里
        cardDao.update(card);
        //将当前记录更新入缓存中
        if (card.getPrice().equals(BigDecimal.valueOf(8))){
            todayPool.getTop2()[0]=card;
        }
        if (card.getPrice().equals(BigDecimal.valueOf(6))){
            todayPool.getTop2()[1]=card;
        }
        if (card.getPrice().equals(BigDecimal.valueOf(0))){
            todayPool.getLast2().add(card);
        }
        todayPool.getTodayList().add(card);
        return cardToMap(card);
    }

    @Override
    public boolean getUserTodayStatus(long uid) {
        boolean flag = false;
        for (Card card : todayPool.getTodayList()) {
            flag = card.getUid() == uid;
        }
        return flag;
    }

    @Override
    public List<Map> getTodayResult() {
        return toMapList(todayPool.getTodayList());
    }

    @Override
    public List<Map> getTodayTopList() {
        return toMapList(todayPool.getTop2());
    }

    @Override
    public List<Map> getTodayLastList() {
        return toMapList(todayPool.getLast2());
    }

    @Override
    public List<Map> getTotalTopResult() {
        return todayPool.getTotalTop3();
    }

    @Override
    public JSONObject getScratchSummary(long id) {
        return null;
    }

    @Override
    public Integer getTodayTotal() {
        return todayPool.getTodayTotal();
    }

    private Map<String, Object> cardToMap(Card card) {
        Map<String, Object> map = new HashMap<>(3);
        if (card.getName()!=null) {
            map.put("uid", card.getUid());
            map.put("name", card.getName());
        }
        map.put("money", card.getPrice());
        return map;
    }

    private List<Map> toMapList(List<Card> cards) {
        List<Map> list = new ArrayList<>();
        for (Card card : cards) {
            list.add(cardToMap(card));
        }
        return list;
    }

    private List<Map> toMapList(Card[] cards) {
        List<Map> list = new ArrayList<>();
        for (Card card : cards) {
            if (card==null){
                continue;
            }
            list.add(cardToMap(card));
        }
        return list;
    }
}
