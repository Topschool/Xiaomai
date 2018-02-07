package com.topschool.xm.interceptor;

import com.topschool.xm.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WxApiInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PartnerService partnerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uid = request.getParameter("uid");
        if (!partnerService.uidExist(uid)){
            throw new IllegalArgumentException("uid不存在");
        }
        return true;
    }
}
