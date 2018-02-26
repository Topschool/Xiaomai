package com.topschool.xm.service.impl;

import com.topschool.xm.dao.PartnerMapper;
import com.topschool.xm.dao.scratchcard.ScratchLogMapper;
import com.topschool.xm.exception.ScratchCardException;
import com.topschool.xm.model.Partner;
import com.topschool.xm.model.scratchcard.Card;
import com.topschool.xm.model.scratchcard.CardPool;
import com.topschool.xm.model.scratchcard.ScratchLog;
import com.topschool.xm.service.ScratchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 小强
 */
@Service
public class DefaultScratchCardServiceImpl implements ScratchCardService {

    private static final String IMAGE_URL_TEMPLATE = "http://ts-dingup-onlinetest.oss-cn-beijing.aliyuncs.com/img/shop/%d.jpg";

    @Autowired
    private ScratchLogMapper scratchLogMapper;
    @Autowired
    private PartnerMapper partnerMapper;
    @Autowired
    private CardPool cardPool;

    @Override
    public void initCardPool(Integer size) {
        if (cardPool.getPool() == null) {
            cardPool.init(size);
        }
    }

    @Override
    public Integer scratch(String uid) throws ScratchCardException {
        if (getPartnerTodayStatus(uid)) {
            throw new ScratchCardException("today's log has exist");
        }
        Integer result = cardPool.popFromPool();
//        save into db
        ScratchLog scratchLog = new ScratchLog();
        scratchLog.setUid(uid);
        scratchLog.setScratchDate(new java.sql.Date(System.currentTimeMillis()));
        scratchLog.setCreateDate(System.currentTimeMillis());
        scratchLog.setResult(result);
        if (!scratchLogMapper.insert(scratchLog)) {
            throw new ScratchCardException("scratch fail");
        }
//        push into toady's scratch log
        Card card = new Card();
        Partner u = partnerMapper.getByUid(uid);
        card.setUid(uid);
        card.setNickname(u.getUsername());
        card.setPrice(result);
        cardPool.getTodayList().add(card);
        if (result == 8) {
            cardPool.getTop2()[0] = card;
        }
        if (result == 6) {
            cardPool.getTop2()[1] = card;
        }
        if (result == 0) {
            cardPool.getLast2().add(card);
        }
//        sum
        cardPool.setTodayTotal(cardPool.getTodayTotal() + result);
        return result;
    }

    @Override
    public boolean getPartnerTodayStatus(String uid) {
        ScratchLog param = new ScratchLog();
        param.setUid(uid);
        param.setScratchDate(new java.sql.Date(System.currentTimeMillis()));

        return null != scratchLogMapper.getOnesScratchResult(param);
    }

    @Override
    public List<Map<String, Object>> getTodayResult(Integer page, Integer pageSize) {
        return changeList(cardPool.getTodayList());
    }

    @Override
    public List<Map<String, Object>> getTodayTopList(Integer page, Integer pageSize) {
        return changeList(Arrays.asList(cardPool.getTop2()));
    }

    @Override
    public List<Map<String, Object>> getTodayLastList(Integer page, Integer pageSize) {
        return changeList(cardPool.getLast2());
    }

    @Override
    public List<Map> getTotalTopResult(Integer page, Integer pageSize) {
        return scratchLogMapper.getCurrentMouthTop(3);
    }

    public Map getScratchSummary(String id) {
        return null;
    }

    @Override
    public Integer getTodayTotal(Date date) {
        return cardPool.getTodayTotal();
    }

    private Map changeToMap(Card card) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", card.getUid());
        map.put("nickname", card.getNickname());
        map.put("money", card.getPrice());
        map.put("imgUrl", String.format(IMAGE_URL_TEMPLATE, card.getPrice()));

        return map;
    }

    private List<Map<String, Object>> changeList(List<Card> list) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(list.size());
        for (Card card : list) {
            if (card == null) {
                continue;
            }
            result.add(changeToMap(card));
        }
        return result;
    }
}
