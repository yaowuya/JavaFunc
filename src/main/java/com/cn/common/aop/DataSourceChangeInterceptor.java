package com.cn.common.aop;


import com.cn.common.annotation.DataSourceType;
import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by 钟锐锋 on 2017/11/10.
 */
@Order(1)
@Component
@Aspect
public class DataSourceChangeInterceptor {
    private static Logger logger= LoggerFactory.getLogger(DataSourceChangeInterceptor.class);

    @Around("execution(* com.cn.dao..*.*(..))")
    public Object changeDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args=joinPoint.getArgs();
        Method method=((MethodSignature)joinPoint.getSignature()).getMethod();
        DataSourceType dataSourceType=method.getDeclaredAnnotation(DataSourceType.class);
        if(dataSourceType!=null){
            return doChange(joinPoint,dataSourceType);
        }
        return joinPoint.proceed(args);
    }

    private Object doChange(ProceedingJoinPoint joinPoint,DataSourceType dataSourceType) throws Throwable {
        Method method=((MethodSignature)joinPoint.getSignature()).getMethod();
        String name=dataSourceType.value();

        MultipleDataSource.setDataSourceKey(name);
        Object returnVal=null;

        try {
            returnVal=joinPoint.proceed(joinPoint.getArgs());
        }catch (Throwable throwable){
            logger.error("DataSourceChangeInterceptor proceed error!!!",throwable);
            throw throwable;
        }finally {
            //切回数据源
            MultipleDataSource.restore();
        }
        return returnVal;
    }
}
