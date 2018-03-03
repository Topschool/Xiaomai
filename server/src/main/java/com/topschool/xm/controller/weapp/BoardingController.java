package com.topschool.xm.controller.weapp;

import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.enums.Address;
import com.topschool.xm.model.ResultBody;
import com.topschool.xm.model.TokenInfo;
import com.topschool.xm.service.weapp.WeappUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author 小强
 */
@RestController
@RequestMapping("/v2/boarding")
public class BoardingController {

    @Autowired
    private WeappUserService weappUserService;

    @PostMapping()
    public ResultBody<?> boarding(String username, int area, @RequestHeader(value="jssession") String sessionId) {
        Address address = Address.valueOf(area);
        JSONObject user = weappUserService.register(username.trim(), address, sessionId);
        return new ResultBody<>(user);
    }

    @GetMapping("/user_info")
    public ResultBody<?> userInfo(@RequestHeader(value="jssession", defaultValue = "0") String sessionId){
        JSONObject userInfo = weappUserService.getUserInfo(sessionId);
        return new ResultBody<>(userInfo);
    }

    @PostMapping("/get_js_session")
    public ResponseEntity<?> getJsSession(@RequestParam(value = "code")String code, @RequestParam("data")String data, @RequestParam("iv")String iv){
        TokenInfo tokenInfo = weappUserService.getJsSession(code.trim(), data.trim(), iv.trim());
        HttpHeaders headers = new HttpHeaders();
        headers.add("sessionId", tokenInfo.getSessionId());
        headers.add("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(new ResultBody(), headers, HttpStatus.OK);
    }
}
