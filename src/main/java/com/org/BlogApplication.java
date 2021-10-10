package com.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MengXi
 *
 * @SpringBootApplication : 声明该类是一个springBoot启动类
 *     该注解等同于三个注解：
 *          @SpringBootConfiguration 表明该类是一个配置类
 *          @EnableAutoConfiguration  核心注解 ： 自动配置
 *          @ComponentScan            组件扫描
 *
 */
@SpringBootApplication
public class BlogApplication {

    /**
     * main函数是java程序入口。
     * run方法表示运行springBoot的引导类，run方法的参数就是springboot启动类的字节码文件
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
