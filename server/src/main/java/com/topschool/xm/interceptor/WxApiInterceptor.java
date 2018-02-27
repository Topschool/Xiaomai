package com.topschool.xm.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信接口拦截器
 *
 * @author 小强
 */
@Component
public class WxApiInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private PartnerService partnerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IllegalArgumentException {
//        String uid = request.getParameter("uid");
//        if (!partnerService.uidExist(uid)) {
//            throw new IllegalArgumentException("uid不存在");
//        }
        return true;
    }
}
