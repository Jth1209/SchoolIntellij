package edu.du.proj_11m.userManagement.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);//세션이 없으면 세션을 생성하지 않고, 존재하는 세션을 가지고 온다.
        if(session != null) {
            Object authInfo = session.getAttribute("authInfo");
            if (authInfo != null) {
                return true;
            }
        }
        response.sendRedirect("/login");
        return false;
    }
    //interceptor 사용하는 순서 1. interceptor클래스 만들기 2.interceptor이 맞는 역할을 수행하도록 작성 3.WebMvcConfig에 registry.addInterceptor(생성한 interceptor 생성자).addPathPatterns(적용할 페이지)
}
