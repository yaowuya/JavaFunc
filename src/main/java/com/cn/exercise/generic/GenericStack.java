package com.cn.exercise.generic;

import java.util.ArrayList;

/**
 * User: zhongrf
 * Date: 2018/5/30 11:11
 * Description:
 */
public class GenericStack <E>{
    private ArrayList<E> list=new ArrayList<E>();

    public int getSize(){
        return list.size();
    }

    public E peek(){
        return list.get(list.size()-1);
    }

    public void push(E o){
        list.add(o);
    }

    public E pop(){
        E o=list.get(list.size()-1);
        list.remove(o);
        return o;
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void printOut(){
        for(E entry:list){
            System.out.println(entry);
        }
    }

}