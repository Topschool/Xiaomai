package com.topschool.xm.handle;

import com.topschool.xm.enums.Address;
import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.service.weapp.WeappUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 小强
 */
@Component
public class BoardingHandle extends HandlerInterceptorAdapter {

    @Value("${weapp.signal}")
    private String signal;

    @Autowired
    private WeappUserService weappUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getHeader("jssession");
        if (sessionId==null){
            throw new SystemException(SystemError.TOKEN_ILLEGAL);
        }
        if (weappUserService.userExist(sessionId)) {
            throw new SystemException(SystemError.USER_HAS_EXIST);
        }
        String invitationCode = request.getParameter("invitationCode");
        if (!signal.equals(invitationCode)) {
            throw new SystemException(SystemError.SIGNAL_ILLEGAL);
        }
        String name = request.getParameter("username");
        if (name != null) {
            Assert.hasText(name, "name不能为空");
            int address = Integer.valueOf(request.getParameter("area"));
            Address.valueOf(address);
    }
        return super.preHandle(request, response, handler);
    }
}
