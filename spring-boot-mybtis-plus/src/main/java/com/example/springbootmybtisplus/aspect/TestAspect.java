package com.example.springbootmybtisplus.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * @Author: zengrenshang
 * @Date: 2020/5/30 22:07
 */
@Component
@Aspect
public class TestAspect {

    @Around(value = "execution(* com.example.springbootmybtisplus.service.impl.TestServiceImpl.getStr(..))")
    public Object doSetFeildValue(ProceedingJoinPoint pjp) throws  Throwable{
        Object ret = pjp.proceed();
        System.out.println("ret");
        ret = "shaxiao";
        return ret;

    }
}
