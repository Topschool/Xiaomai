package com.topschool.xm.task;

import com.topschool.xm.service.ScratchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 刮刮卡相关定时任务
 *
 * @author 小强
 */
@Component
@EnableScheduling
public class ScratchCardTask {

    @Value("${scratch.poolSize}")
    private int scratchPoolSize;

    @Autowired
    private ScratchCardService scratchCardService;

    /**
     * 每周工作日凌晨刷新刮刮卡
     */
    @Scheduled(cron = "0 0 0 * * MON-FRI")
    public void initScratchCardPool() {
        scratchCardService.initCardPool(scratchPoolSize);
    }

}
