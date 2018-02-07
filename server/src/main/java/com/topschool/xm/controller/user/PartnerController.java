package com.topschool.xm.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.topschool.xm.service.PartnerService;
import com.topschool.xm.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private PartnerService partnerService;

    @PostMapping("/sign_up")
    public ResponseEntity<?> signUp(@RequestParam String uid,
                                           @RequestParam String username,
                                           @RequestParam String invitationCode,
                                           HttpServletRequest request) throws NoPermissionException {
        HttpSession session = request.getSession();
        String expectUid = (String) session.getAttribute("uid");
        if (expectUid == null) {
            throw new NoPermissionException("没有权限注册用户");
        }
        if (!expectUid.equals(uid)) {
            throw new IllegalArgumentException("uid不正确");
        }
        if (username.trim().length() <= 2) {
            throw new IllegalArgumentException("姓名非法");
        }
        if (!INVITATION_CODE.equals(invitationCode)) {
            throw new IllegalArgumentException("非法的邀请码");
        }
        String openId = (String) session.getAttribute("openId");
        partnerService.register(uid, username, invitationCode, openId, 1);
        Map result = new HashMap();
        result.put("uid", uid);
        result.put("username", username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/get_uid")
    public ResponseEntity<?> getUid(String code, HttpServletRequest request) {
        JSONObject object = HttpUtil.getJson(String.format(GET_OPEN_ID_URL_TEMPLATE, APP_ID, SECRET, code.trim()));
        if (object.get("openid") == null) {
            throw new IllegalArgumentException("code无效");
        }
        HttpSession session = request.getSession();
        session.setAttribute("openId", object.get("openid"));
        String uid = UUID.randomUUID().toString().replace("-", "");
        session.setAttribute("uid", uid);
        return new ResponseEntity<>(uid, HttpStatus.OK);
    }

}
