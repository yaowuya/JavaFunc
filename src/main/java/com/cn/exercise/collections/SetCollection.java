package com.cn.exercise.collections;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: zhongrf
 * Date: 2018/5/31 14:12
 * Description:
 */
public class SetCollection {
    public static void main(String[] args){
        Set<String> set=new HashSet<>();
        set.add("a");
        set.add("ab");
        set.add("ac");
        set.add("a");
        set.add("d");
        set.add("f");

        Iterator<String> iterator=set.iterator();

        while (iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("\n");
        LinkedHashSet<String> linkedHashSet=new LinkedHashSet<>();
        linkedHashSet.add("1");
        linkedHashSet.add("2");
        linkedHashSet.add("3");
        linkedHashSet.add("4");
        for(String str:linkedHashSet){
            System.out.print(str+" ");
        }
    }
}