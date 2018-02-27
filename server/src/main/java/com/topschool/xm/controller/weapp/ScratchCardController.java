package com.topschool.xm.controller.weapp;

import com.topschool.xm.exception.ScratchCardException;
import com.topschool.xm.service.weapp.ScratchCardService;
import com.topschool.xm.service.weapp.TodayPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> scratchCard(long uid) throws ScratchCardException {
        Map map = scratchCardService.scratch(uid);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/init")
    public ResponseEntity<?> init(){
        todayPoolService.init();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
