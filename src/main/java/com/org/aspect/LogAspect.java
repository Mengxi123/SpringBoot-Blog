package com.org.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author MengXi
 * @date 2021/10/9 17:39
 * Aspect:表明当前类是一个切面类 - aop的原理就是利用动态代理对一些函数增强
 * Component:开启组件扫描，springBoot才能扫描到，把当前类的一个对象（bean）放入容器IOC中，属性值value可以指定bean的id，不写时，默认为当前类名(首字母改成小写)
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Pointcut 用于配置切入点表达式，指定对那些类的那些方法进行增强
     *           String expression : [访问修饰符]（访问修饰符可省略） 返回值 包名.包名.包名...类名.方法名(参数列表) 【参数列表..表示任意参数】
     *
     * 拦截com.org.web包下的所有类中的所有方法
     */
    @Pointcut("execution(* com.org.web.*.*(..))")
    public void log() {

    }

    /**
     * @Before : 前置通知，该增强的方法在切入点方法之前执行
     *           String value ：用于指定切入点表达式，或者切入点表达式的引用，注意不要忘记写括号
     *
     * JoinPoint : 连接点，就是指那些被拦截的点（方法） --- 因为spring只支持方法类型的连接点。
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //通过连接点拿到类名和方法名,以及参数
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", requestLog);
    }


    /**
     * @After : 最终通知，无论切入点方法执行是是否有异常，该增强的方法均会在其后面执行
     *           String value ：用于指定切入点表达式，或者切入点表达式的引用，注意不要忘记写括号
     */
    @After("log()")
    public void doAfter() {
//        logger.info("---------doAfter-----------");
    }


    /**
     * @AfterReturning : 后置通知，当切入点方法正常执行后，执行该增强方法； 后置通知和异常通知只能执行其中一个。
     *           String value ：用于指定切入点表达式，或者切入点表达式的引用，注意不要忘记写括号
     *           String returning ： 通知签名中要将返回值绑定到的参数的名称
     */
    @AfterReturning(value = "log()", returning = "result")
    public void doAfterReturning(Object result) {
        logger.info("Result : {}", result);
    }

    /**
     * 将日志封装成一个对象
     */
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }


}
