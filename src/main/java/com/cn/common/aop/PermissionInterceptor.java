package com.cn.common.aop;

import com.cn.common.annotation.Permission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * User: zhongrf
 * Date: 2018/3/22 11:23
 * Description:
 */
@Order(2)
@Component
@Aspect
public class PermissionInterceptor {
    private static Logger logger= LoggerFactory.getLogger(PermissionInterceptor.class);

    @Around("execution(* com.cn.controller.webservice..*.*(..))")
    public Object permissionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args=joinPoint.getArgs();
        Method method=((MethodSignature)joinPoint.getSignature()).getMethod();
        Permission permission = method.getDeclaredAnnotation(Permission.class);

        if(permission!=null){
            boolean flag=doCheck(args);
            if(!flag){
                return null;
            }
        }

        return joinPoint.proceed(joinPoint.getArgs());

    }

    private boolean doCheck(Object[] args) {
        HttpServletResponse response=(HttpServletResponse) args[0];
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


}