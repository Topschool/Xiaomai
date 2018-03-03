package com.topschool.xm.task;

import com.topschool.xm.service.admin.TodayMenuService;
import com.topschool.xm.service.weapp.OrderFoodService;
import com.topschool.xm.service.weapp.TodayPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 小强
 */
@Component
public class InitTask {

    @Autowired
    private TodayPoolService todayPoolService;
    @Autowired
    private TodayMenuService todayMenuService;

    @PostConstruct
    private void initScratchCard(){
        System.out.println("init scratch card");
        todayPoolService.init();
        System.out.println("init order food system");
        todayMenuService.init();
    }

}
