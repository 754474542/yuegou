package com.yuegou.controller.pretreatment;

import com.yuegou.controller.pretreatment.exceptionhandle.ExpiredJwtException;
import com.yuegou.controller.pretreatment.exceptionhandle.PathJumpException;
import com.yuegou.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class ControllerInterceptor implements HandlerInterceptor {

    @Autowired
    private Logger logger;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] ignoreList = {"login", "register", "verificationCode", "forgot"};
        if (!(handler instanceof HandlerMethod)) return false;
        HandlerMethod hm = (HandlerMethod) handler;
        String path = hm.getMethod().getName();
        for (String s : ignoreList) {
            if (path.equals(s)) return true;
        }
        Claims claims = null;
        String token = request.getHeader("token");
        try {
            claims = jwtUtil.parseToken(token);
        } catch (Exception e) {
//            throw new ExpiredJwtException(Code.TOKEN_EXPIRE_ERR, "没检索到token过期，或者token过期，请重新登录");
            e.printStackTrace();
        }
        logger.info(claims.get("userName") + " 访问了 " + path);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
