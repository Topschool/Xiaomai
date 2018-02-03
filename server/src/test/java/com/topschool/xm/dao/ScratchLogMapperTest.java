package com.topschool.xm.dao;

import com.topschool.xm.BaseTest;
import com.topschool.xm.dao.scratchcard.ScratchLogMapper;
import com.topschool.xm.model.scratchcard.ScratchLog;
import org.junit.Test;

import java.sql.Date;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class ScratchLogMapperTest extends BaseTest {

    private ScratchLogMapper scratchLogMapper = this.getBean(ScratchLogMapper.class);

    @Test
    public void insert() {
        Random random = new Random();
        Date date = new Date(System.currentTimeMillis());

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 28; j++) {
                ScratchLog scratchLog = new ScratchLog();
                for (int k = 0; k < 20; k++) {
                    scratchLog.setWxId("wx_test" + k);
                    scratchLog.setResult(Math.abs(random.nextInt() % 10));
                    date.setMonth(i);
                    date.setDate(j);
                    scratchLog.setScratchDate(date);
                    scratchLog.setCreateDate(System.currentTimeMillis());
                    assert scratchLogMapper.insert(scratchLog);
                }
            }
        }
    }

    @Test
    public void getOnesScratchResult() {
        ScratchLog scratchLog = new ScratchLog();
        scratchLog.setWxId("wx_test1");
        scratchLog.setScratchDate(new java.sql.Date(System.currentTimeMillis()));
        assertNotNull(scratchLogMapper.getOnesScratchResult(scratchLog));
        System.out.println();
    }

    @Test
    public void getCurrentMouthScratchListByWxId() {
        assertNotNull(scratchLogMapper.getCurrentMouthScratchListByWxId("wx_test1"));
    }

    @Test
    public void getTodayScratchResultList() {
        assertNotNull(scratchLogMapper.getTodayScratchResultList());
    }

    @Test
    public void getMouthCountByWxId() {
        assertNotNull(scratchLogMapper.getMouthCountByWxId("wx_test1"));
    }

    @Test
    public void getCurrentMouthTop() {
        for (Map map : scratchLogMapper.getCurrentMouthTop(3)) {
            System.out.println(map.get("wx_id"));
            System.out.println(map.get("total"));
        }
    }

    @Test
    public void randomTest() {
        System.out.println(new Date(System.currentTimeMillis()).toString());
    }
}