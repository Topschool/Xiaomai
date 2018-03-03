package com.topschool.xm.handle;

import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
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
public class OrderFoodHandle extends HandlerInterceptorAdapter {

    @Autowired
    private WeappUserService weappUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uidStr = request.getParameter("uid");
        if (uidStr!=null){
            Long uid = Long.parseLong(uidStr);
        }
//        Map map = weappUserService.getUserStatus(uid);
//        assert map!=null;
//        if (!(Boolean)map.get("allowOrdering")) {
//            throw new SystemException(SystemError.ORDER_FOOD_NO_PERMISSION);
//        }
        Map map = request.getParameterMap();
        return super.preHandle(request, response, handler);
    }
}
