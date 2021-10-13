package com.org.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Create by MengXi on 2021/10/13 16:15.
 * @Configuration  表明这是一个配置类，
 * WebMvcConfigurer 定义回调方法，以自定义通过@EnableWebMvc启用的SpringMVC的基于Java的配置
 *             void addInterceptors : 添加SpringMVC生命周期拦截器，用于控制器方法调用和资源处理程序请求的预处理和后处理。
 *                  addPathPatterns ： 添加要拦截的URL
 *                  excludePathPatterns : 排序掉一些不拦截的路径
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //添加过滤路径
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
        
    }
}
