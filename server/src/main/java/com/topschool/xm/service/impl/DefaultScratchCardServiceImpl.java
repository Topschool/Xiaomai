package com.topschool.xm.service.impl;

import com.topschool.xm.dao.scratchcard.ScratchLogMapper;
import com.topschool.xm.exception.ScratchCardException;
import com.topschool.xm.model.scratchcard.Card;
import com.topschool.xm.model.scratchcard.CardPool;
import com.topschool.xm.model.scratchcard.ScratchLog;
import com.topschool.xm.service.ScratchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefaultScratchCardServiceImpl implements ScratchCardService {

    @Autowired
    private ScratchLogMapper scratchLogMapper;
    @Autowired
    private CardPool cardPool;

    public void initCardPool(Integer size) {
        if (cardPool.getPool() == null) {
            cardPool.init(size);
        }
    }

    public Integer scratch(String wxId) throws ScratchCardException {
        if (getPartnerTodayStatus(wxId)) {
            throw new ScratchCardException("today's log has exist");
        }
        Integer result = cardPool.popFromPool();
//        save into db
        ScratchLog scratchLog = new ScratchLog();
        scratchLog.setWxId(wxId);
        scratchLog.setScratchDate(new java.sql.Date(System.currentTimeMillis()));
        scratchLog.setCreateDate(System.currentTimeMillis());
        scratchLog.setResult(result);
        if (!scratchLogMapper.insert(scratchLog)) {
            throw new ScratchCardException("scratch fail");
        }
//        push into toady's scratch log
        Card card = new Card();
        card.setWxId(wxId);
        card.setPrice(result);
        cardPool.getTodayList().add(card);
        if (result == 8) {
            cardPool.getTop2().add(1, card);
        }
        if (result == 6) {
            cardPool.getTop2().add(2, card);
        }
        if (result == 0) {
            cardPool.getLast2().add(card);
        }
//        sum
        cardPool.setTodayTotal(cardPool.getTodayTotal() + result);
        return result;
    }

    public boolean getPartnerTodayStatus(String wxId) {
        ScratchLog param = new ScratchLog();
        param.setWxId(wxId);
        param.setScratchDate(new java.sql.Date(System.currentTimeMillis()));

        return null != scratchLogMapper.getOnesScratchResult(param);
    }

    public List<Map<String, Object>> getTodayResult(Integer page, Integer pageSize) {
        return changeList(cardPool.getTodayList());
    }

    public List<Map<String, Object>> getTodayTopList(Integer page, Integer pageSize) {
        return changeList(cardPool.getTop2());
    }

    public List<Map<String, Object>> getTodayLastList(Integer page, Integer pageSize) {
        return changeList(cardPool.getLast2());
    }

    public List<Map> getTotalTopResult(Integer page, Integer pageSize) {

        return scratchLogMapper.getCurrentMouthTop(3);
    }

    public Map getScratchSummary(String id) {
        return null;
    }

    public Integer getTodayTotal(Date date) {
        return cardPool.getTodayTotal();
    }

    private Map changeToMap(Card card) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", card.getWxId());
        map.put("nickname", card.getNickname());
        map.put("money", card.getPrice());

        return map;
    }

    private List<Map<String, Object>> changeList(List<Card> list) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(list.size());
        for (Card card : list) {
            result.add(changeToMap(card));
        }
        return result;
    }
}
