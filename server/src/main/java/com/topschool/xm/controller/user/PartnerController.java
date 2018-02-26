package com.topschool.xm.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.service.PartnerService;
import com.topschool.xm.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class PartnerController {
    private static final String GET_OPEN_ID_URL_TEMPLATE = "https://api.weixin.qq.com/sns/jscode2session?" +
            "appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
    private static final String APP_ID = "wx7c0418540db4d54f";
    private static final String SECRET = "1d8dcd22cb939840d73e995094ce99d9";
    private static final String INVITATION_CODE = "1234567890";
    private static final String[] AREAS = {"北京", "上海", "南京", "无锡"};
    private static final Map USER_ID_OPEN_ID_CACHE = new HashMap();

    @Autowired
    private PartnerService partnerService;

    @PostMapping("/sign_up")
    public ResponseEntity<?> signUp(@RequestParam String uid,
                                    @RequestParam String username,
                                    @RequestParam String invitationCode,
                                    @RequestParam Integer area) {
        String expectOpenId = (String) USER_ID_OPEN_ID_CACHE.get(uid);
        if (expectOpenId == null) {
            throw new IllegalArgumentException("未认证的uid无法注册");
        }
        if (username.trim().length() < 2 || username.trim().length() > 5) {
            throw new IllegalArgumentException("姓名非法");
        }
        if (!INVITATION_CODE.equals(invitationCode)) {
            throw new IllegalArgumentException("非法的邀请码");
        }
        if (area < 0 || area > 3) {
            throw new IllegalArgumentException("非法的地址");
        }
        Map userInfo = partnerService.register(uid, username, invitationCode, expectOpenId, area);
        USER_ID_OPEN_ID_CACHE.remove(uid);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PostMapping("/get_uid")
    public ResponseEntity<?> getUid(String code) {
        JSONObject object = HttpUtil.getJson(String.format(GET_OPEN_ID_URL_TEMPLATE, APP_ID, SECRET, code.trim()));
        if (object.get("openid") == null) {
            throw new IllegalArgumentException("code无效");
        }
        System.out.println(code);
        Map userInfo = partnerService.getUserInfoByOpenId((String) object.get("openid"));
        if (userInfo != null) {
            return new ResponseEntity<>(userInfo.get("uid"), HttpStatus.OK);
        }
        for (Object o : USER_ID_OPEN_ID_CACHE.keySet()) {
            if (object.get("openid").equals(USER_ID_OPEN_ID_CACHE.get(o))) {
                return new ResponseEntity<>(o, HttpStatus.OK);
            }
        }
        String uid = UUID.randomUUID().toString().replace("-", "");
        USER_ID_OPEN_ID_CACHE.put(uid, object.get("openid"));
        return new ResponseEntity<>(uid, HttpStatus.OK);
    }

    @GetMapping("/user_info")
    public ResponseEntity<?> getUserInfo(String userId) {
        return null;
    }
}
