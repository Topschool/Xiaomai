package com.topschool.xm.handle;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 后台管理拦截器
 *
 * @author 小强
 */
@Component
public class AdminHandle extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("/login?");
            return false;
        }
        return true;
    }
}
