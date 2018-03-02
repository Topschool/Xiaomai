package com.topschool.xm.handle;

import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.service.weapp.WeappUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * 微信接口拦截器
 *
 * @author 小强
 */
@Component
public class WeappApiHandle extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        return super.preHandle(request, response, handler);
    }
}
