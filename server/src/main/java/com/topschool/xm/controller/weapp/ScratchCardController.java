package com.topschool.xm.controller.weapp;

import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.ResultBody;
import com.topschool.xm.service.weapp.ScratchCardService;
import com.topschool.xm.service.weapp.TodayPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 小强
 */
@RestController
@RequestMapping("/v2/scratch_card")
public class ScratchCardController {

    @Autowired
    private ScratchCardService scratchCardService;


    @PostMapping("/scratch")
    public ResultBody<?> scratchCard(long uid) throws SystemException {
        return new ResultBody<>(scratchCardService.scratch(uid));
    }

    @GetMapping("/today_scratch_card_info")
    public ResultBody<?> getTodayInfo(){
        Map<String, Object> result = new HashMap<>(5);
        result.put("totalNum", scratchCardService.getTodayTotal());
        result.put("topList", scratchCardService.getTodayTopList());
        result.put("lastList", scratchCardService.getTodayLastList());
        result.put("todayList", scratchCardService.getTodayResult());
        result.put("totalTop", scratchCardService.getTotalTopResult());
        return new ResultBody<>(result);
    }

    @GetMapping("/user_status")
    public ResultBody<?> userStatus(long uid){
        return new ResultBody<>(scratchCardService.getUserTodayStatus(uid));
    }

}
