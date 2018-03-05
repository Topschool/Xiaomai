package com.topschool.xm.service.weapp.impl;

import com.topschool.xm.dao.scratchcard.CardDao;
import com.topschool.xm.dao.scratchcard.ScratchRecordDao;
import com.topschool.xm.model.scratchcard.Card;
import com.topschool.xm.model.scratchcard.TodayPool;
import com.topschool.xm.service.weapp.TodayPoolService;
import com.topschool.xm.util.RandomUtil;
import com.topschool.xm.enums.ScratchCardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小强
 */
@Service
public class DefaultTodayPoolServiceImpl implements TodayPoolService {

    @Value("${scratch.basePrices}")
    private int[] basePrices;

    @Value("${scratch.poolSize}")
    private int poolSize;

    @Autowired
    private ScratchRecordDao scratchRecordDao;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private TodayPool todayPool;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void init() {
        todayPool.setStatus(ScratchCardStatus.SCRATCHING);
        todayPool.setPool(new ArrayList<>(poolSize));
        todayPool.setTop2(new Card[2]);
        todayPool.setLast2(new ArrayList<>(2));
        todayPool.setTodayList(new ArrayList<>(poolSize));
        todayPool.setTodayTotal(0);
        todayPool.setTotalTop3(scratchRecordDao.getCurrentMouthTop(3, System.currentTimeMillis()));
        List<Card> cards = this.getTodayRecord();
        if (cards == null||cards.size()==0) {
            todayPool.setPool(generateCard());
            cardDao.insertList(todayPool.getPool());
            return;
        }
        for (Card card : cards) {
            if (card.getScratched()){
                if (card.getPrice().equals(BigDecimal.valueOf(8))) {
                    todayPool.getTop2()[0]=card;
                }
                if (card.getPrice().equals(BigDecimal.valueOf(6))) {
                    todayPool.getTop2()[1]=card;
                }
                if (card.getPrice().equals(BigDecimal.valueOf(0))) {
                    todayPool.getLast2().add(card);
                }
                todayPool.getTodayList().add(card);
                todayPool.setTodayTotal(todayPool.getTodayTotal()+card.getPrice().intValue());
            } else {
                todayPool.getPool().add(card);
            }
            todayPool.setTotalTop3(scratchRecordDao.getCurrentMouthTop(3, System.currentTimeMillis()));
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void cleanCardPool() {
        cardDao.deleteAll();
        todayPool.setTop2(null);
        todayPool.setLast2(null);
        todayPool.setTodayList(null);
        todayPool.setPool(null);
        todayPool.setTotalTop3(null);
        todayPool.setTodayTotal(0);
        todayPool.setStatus(ScratchCardStatus.STOPED);
    }

    private List<Card> generateCard() {
        List<Card> cards = new ArrayList<>(poolSize);
        for (int i = 0; i < poolSize - basePrices.length; i++) {
            int price = RandomUtil.generationRandom(3, 5);
            cards.add(new Card(BigDecimal.valueOf(price)));
        }
        for (int basePrice : basePrices) {
            cards.add(new Card(BigDecimal.valueOf(basePrice)));
        }
        return cards;
    }

    /**
     * 从数据库中获取今日刮卡池信息
     *
     * @return 今日刮卡池所有信息
     */
    private List<Card> getTodayRecord() {
        return cardDao.selectAll();
    }
}
