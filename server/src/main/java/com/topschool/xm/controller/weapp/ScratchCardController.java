package com.topschool.xm.controller.weapp;

import com.topschool.xm.exception.ScratchCardException;
import com.topschool.xm.exception.UserNotFoundException;
import com.topschool.xm.service.weapp.ScratchCardService;
import com.topschool.xm.service.weapp.TodayPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private TodayPoolService todayPoolService;

    @PostMapping("/scratch")
    public ResponseEntity<?> scratchCard(long uid) throws ScratchCardException, UserNotFoundException {
        return new ResponseEntity<>(scratchCardService.scratch(uid), HttpStatus.OK);
    }

    @GetMapping("/today_scratch_card_info")
    public ResponseEntity<?> getTodayInfo(){
        Map<String, Object> result = new HashMap<>(5);
        result.put("totalNum", scratchCardService.getTodayTotal());
        result.put("topList", scratchCardService.getTodayTopList());
        result.put("lastList", scratchCardService.getTodayLastList());
        result.put("todayList", scratchCardService.getTodayResult());
        result.put("totalTop", scratchCardService.getTotalTopResult());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/init")
    public ResponseEntity<?> init(){
        todayPoolService.init();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user_status")
    public ResponseEntity<?> userStatus(long uid){
        return new ResponseEntity<>(scratchCardService.getUserTodayStatus(uid), HttpStatus.OK);
    }

}
