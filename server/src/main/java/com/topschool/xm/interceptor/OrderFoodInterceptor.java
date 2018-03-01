package com.topschool.xm.interceptor;

import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.service.weapp.ScratchCardService;
import com.topschool.xm.service.weapp.WeappUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 小强
 */
@Component
public class OrderFoodInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private WeappUserService weappUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long uid = Long.parseLong(request.getParameter("uid"));
        Map map = weappUserService.getUserStatus(uid);
        if (!(Boolean)map.get("allowOrdering")) {
            throw new SystemException(SystemError.ORDER_FOOD_NO_PERMISSION);
        }
        return super.preHandle(request, response, handler);
    }
}
