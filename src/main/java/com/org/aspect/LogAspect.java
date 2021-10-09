package com.org.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author MengXi
 * @date 2021/10/9 17:39
 * Aspect:表明当前类是一个切面类 - aop的原理就是利用动态代理对一些函数增强
 * Component:开启组件扫描，springBoot才能扫描到，把当前类的一个对象（bean）放入容器IOC中，属性值value可以指定bean的id，不写时，默认为当前类名(首字母改成小写)
 */
@Aspect
@Component
public class LogAspect {


}
