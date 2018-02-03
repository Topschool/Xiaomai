package com.topschool.xm.service.impl;

import com.topschool.xm.BaseTest;
import com.topschool.xm.model.scratchcard.CardPool;
import com.topschool.xm.service.ScratchCardService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.*;

public class DefaultScratchCardServiceImplTest extends BaseTest {

    private ScratchCardService scratchCardService = this.getBean(DefaultScratchCardServiceImpl.class);

    private CardPool cardPool = this.getBean(CardPool.class);

    @Test
    public void initCardPool() {
        scratchCardService.initCardPool(100);
        assert cardPool.getPool().size()==100;
    }

    @Test
    public void scratch() throws Exception {
        cardPool.init(100);
        scratchCardService.scratch("hello-world");
        System.out.println();
    }

    @Test
    public void getPartnerTodayStatus() {
        assert scratchCardService.getPartnerTodayStatus("hello-world");
    }

    @Test
    public void getTodayResult() {
    }

    @Test
    public void getTodayTopList() {
    }

    @Test
    public void getTodayLastList() {
    }

    @Test
    public void getTotalTopResult() {
    }

    @Test
    public void getScratchSummary() {
    }

    @Test
    public void getTodayTotal() {
    }
}