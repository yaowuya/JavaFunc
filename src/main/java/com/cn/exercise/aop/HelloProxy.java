package com.cn.exercise.aop;

/**
 * User: zhongrf
 * Date: 2018/6/8 14:52
 * Description:
 */
public class HelloProxy implements Hello {
    private HelloImpl helloImpl=null;

    public HelloProxy(){
        this.helloImpl=new HelloImpl();
    }
    @Override
    public void say(String name) {
        before();
        helloImpl.say(name);
        after();
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }
}