package com.cn.exercise.aop;

/**
 * User: zhongrf
 * Date: 2018/6/8 14:56
 * Description:
 */
public class HelloTest {
    public static void main(String[] args) {
        helloProxy();
        dynamicProxy();
        CGLibProxy();
    }

    public static void helloProxy(){
        HelloProxy helloProxy=new HelloProxy();
        helloProxy.say("Jack");
    }

    public static void dynamicProxy(){
        DynamicProxy dynamicProxy=new DynamicProxy(new HelloImpl());
        Hello helloProxy=dynamicProxy.getProxy();
        helloProxy.say("小明");
    }

    public static void CGLibProxy(){
        Hello helloProxy=CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("小红");
    }
}