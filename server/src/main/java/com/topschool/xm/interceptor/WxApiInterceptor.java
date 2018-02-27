package com.topschool.xm.interceptor;

import com.topschool.xm.service.weapp.WeappUserService;
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

    @Autowired
    private WeappUserService weappUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IllegalArgumentException {
        long uid = Long.parseLong(request.getParameter("uid"));
        if (!weappUserService.userExist(uid)) {
            throw new IllegalArgumentException("uid不存在");
        }
        return true;
    }
}
