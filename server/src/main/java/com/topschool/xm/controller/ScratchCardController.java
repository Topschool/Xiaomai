package com.topschool.xm.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.dto.ScratchResult;
import com.topschool.xm.service.ScratchCardService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/game/scratch-card")
public class ScratchCardController {

    @Autowired
    private ScratchCardService scratchCardService;

    @GetMapping("")
    public ResponseEntity<?> scratch(String id) {
        ScratchResult result = new ScratchResult();
        result.setCurUserGroup(1);
        result.setPartnerStatus(scratchCardService.getPartnerTodayStatus(id));
        result.setCurrentScratchResult(scratchCardService.scratch(id));
        result.setLastList(scratchCardService.getTodayLastList(0, 2));
        result.setTopList(scratchCardService.getTodayTopList(0, 2));
        result.setTodayList(scratchCardService.getTodayResult(0, 2));
        result.setTotalTop(scratchCardService.getTotalTopResult(0, 3));
        result.setTotalNum(scratchCardService.getTodayTotal(new Date()));
        return new ResponseEntity<Object>(result, OK);
    }
}
