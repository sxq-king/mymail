package com.sxq.mymail.aop;

import java.lang.annotation.*;

/**
 * 自定义注解，拦截controller
 */
@Target({ElementType.METHOD,ElementType.PARAMETER}) //作用在参数和方法上
@Retention(RetentionPolicy.RUNTIME) //运行时注解
@Documented //表明这个注解应该被javaDoc工具记录
public @interface SystemControllerLog {
    String description() default "";
}
