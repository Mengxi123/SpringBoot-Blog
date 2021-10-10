package com.org.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ControllerAdvice 表明该类是一个拦截类：拦截所有标注有Controller注解的控制器
 * @author MengXi
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 获取日志对象记录异常
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @ExceptionHandler  异常处理器
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        //记录日志
        logger.error("Request URL : {}, Exception : {}", request.getRequestURI(), e);

        //如果存在异常状态码，就抛出异常，让springBoot处理，而不是拦截所有异常
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        //返回模型(Model-封装的数据)视图(View-展示数据)
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据,以键值对形式,在页面跳转时可以通过el表达式获取数据${url}
        modelAndView.addObject("url", request.getRequestURI());
        modelAndView.addObject("exception", e);
        //设置试图名称,即要跳转的页面
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
