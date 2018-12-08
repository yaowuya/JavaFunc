package com.cn.exercise.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * User: zhongrf
 * Date: 2018/6/8 15:46
 * Description:CGLib动态代理,使用单例模式
 * private 构造方法，是为了不被new
 */
public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy instance=new CGLibProxy();

    private CGLibProxy(){

    }

    public static CGLibProxy getInstance(){
        return instance;
    }

    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result=methodProxy.invokeSuper(o,args);
        after();
        return result;

    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }
}