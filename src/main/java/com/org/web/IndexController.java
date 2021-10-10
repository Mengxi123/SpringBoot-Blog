package com.org.web;

import com.org.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author MengXi
 * @Controller 用于表示层的注解，作用与@Component注解一样，表明将该资源交给spring进行管理
 *             String value : 指定bean的id，如果不屑，默认为该类的类名(且将其首字母小写)
 */
@Controller
public class IndexController {
    /**
     *  @GetMapping  建立请求url和处理请求方法之间的映射关系，配置在类上为一级注解，配置在方法上为二级注解
     *              String value : 指定请求的url
     *              String method :指定请求的方法
     *              String params : 限制请求参数的条件，支持简单的表达式
     *
     * Restful风格的参数获取：url + 请求方式
     *          在url中使用占位符进行参数绑定，同时在方法中使用@PathVariable注解与占位符进行匹配,参数名称要一致。
     */
    @GetMapping("/")
    public String index() {
//        int i = 1 / 0;
//        String blog = null;
//        if (blog == null) {
//            throw new NotFoundException("博客不存在");
//        }
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
//        int i = 1 / 0;
//        String blog = null;
//        if (blog == null) {
//            throw new NotFoundException("博客不存在");
//        }
        return "blog";
    }
}
