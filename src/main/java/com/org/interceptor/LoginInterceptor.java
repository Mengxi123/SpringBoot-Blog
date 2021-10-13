package com.org.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Create by MengXi on 2021/10/13 15:47.
 *
 * interface HandlerInterceptor   允许自定义处理程序执行链的工作流接口。
 * preHandle  在请求到达目的地之前的拦截点，进行预处理。
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (request.getSession().getAttribute("user") == null) {
            //session域中的user为空，就重定向到登录界面
            response.sendRedirect("/admin");
            return false;
        }

        return true;
    }
}
