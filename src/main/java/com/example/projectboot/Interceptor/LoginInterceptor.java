package com.example.projectboot.Interceptor;

//登录拦截

import com.example.projectboot.util.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

//    拦截方法

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


//    从请求头获取token
        String token = request.getHeader("Authorization");
//    获取url地址
        String uri = request.getRequestURI();

        if ("/user/login".equals(uri) || "OPTIONS".equals(request.getMethod())) {
            return true;//放行，不拦截
        } else {
            if (token == null || "".equals(token)) {
                System.out.println("-----请求拦截");
                return false;
            }
            TokenUtil tokenUtil = new TokenUtil();
            if (tokenUtil.verifyToken(token)) {
                return true;  //token认证
            }
        }
        return false;
    }
}