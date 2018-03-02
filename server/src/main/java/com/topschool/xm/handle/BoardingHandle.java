package com.topschool.xm.handle;

import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.service.weapp.WeappUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 小强
 */
@Component
public class BoardingHandle extends HandlerInterceptorAdapter {

    @Autowired
    private WeappUserService weappUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getHeader("jssession");
        if (weappUserService.userExist(sessionId)){
            throw new SystemException(SystemError.USER_HAS_EXIST);
        }
        return super.preHandle(request, response, handler);
    }
}
