package com.topschool.xm.handle;

import com.topschool.xm.enums.SystemError;
import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.scratchcard.TodayPool;
import com.topschool.xm.service.weapp.ScratchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 小强
 */
@Component
public class ScratchCardHandle extends HandlerInterceptorAdapter {

    @Autowired
    private ScratchCardService scratchCardService;
    @Autowired
    private TodayPool todayPool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (todayPool.getStatus().getCode() == 0 || todayPool.getStatus().getCode() == 1) {
            throw new SystemException(SystemError.SCRATCH_CARD_SYSTEM_UNINIT);
        }
        return super.preHandle(request, response, handler);
    }
}
