package com.topschool.xm.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScratchCardTask {

    @Scheduled(cron = "0/5 * * * * ? ")
    public void initScratchCardPool(){
        System.out.println("initScratchCardPool");
    }

}
