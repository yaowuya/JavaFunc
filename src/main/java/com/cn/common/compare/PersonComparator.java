package com.cn.common.compare;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * User: zhongrf
 * Date: 2018/6/19 17:59
 * Description:
 */
public class PersonComparator  {
    String name;
    int age;

    public PersonComparator(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        PersonComparator[] people=new PersonComparator[]{
                new PersonComparator("xujian", 20),
                new PersonComparator("xiewei", 10)
        };
        System.out.println("排序前");
        for (PersonComparator PersonComparator : people)
        {
            System.out.print(PersonComparator.getName()+":"+PersonComparator.getAge());
        }

        //如果是List，则用Collections
        Arrays.sort(people, new Comparator<PersonComparator>() {
            @Override
            public int compare(PersonComparator o1, PersonComparator o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        System.out.println("\n排序后");
        for (PersonComparator PersonComparator : people)
        {
            System.out.print(PersonComparator.getName()+":"+PersonComparator.getAge());
        }
    }
}