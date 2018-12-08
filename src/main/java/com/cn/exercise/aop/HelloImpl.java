package com.cn.exercise.aop;

/**
 * User: zhongrf
 * Date: 2018/6/8 14:51
 * Description:
 */
public class HelloImpl implements Hello{
    @Override
    public void say(String name) {
        System.out.println("Hello! "+name);
    }
}