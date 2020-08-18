package com.example.springbootmybtisplus.aspect;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybtisplus.util.BeanUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Aspect
public class SetFeildValueAspect {

    @Autowired
    private BeanUtil beanUtil;

    @Around("@annotation(com.example.springbootmybtisplus.annotation.NeedSetFeildValue)")
    public Object doSetFeildValue(ProceedingJoinPoint pjp) throws  Throwable{
        Object ret = pjp.proceed();
        if(ret instanceof Collection){
            this.beanUtil.setFieldValueForCollection((Collection) ret);
        }else{
            Page page = (Page) ret;
            this.beanUtil.setFieldValueForCollection(page.getRecords());
            //不是集合，也需要設置屬性值，，beanUtil还提供一个设置单个对象的属性值方法
        }
        return ret;

    }


}
