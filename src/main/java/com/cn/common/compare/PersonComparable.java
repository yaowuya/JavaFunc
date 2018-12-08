package com.cn.common.compare;

import java.util.Arrays;

/**
 * User: zhongrf
 * Date: 2018/6/19 17:51
 * Description:Comparable是排序接口。若一个类实现了Comparable接口，就意味着该类支持排序。
 * 实现了Comparable接口的类的对象的列表或数组可以通过Collections.sort或Arrays.sort进行自动排序。
 * 此外，实现此接口的对象可以用作有序映射中的键或有序集合中的集合，无需指定比较器
 */
public class PersonComparable implements Comparable<PersonComparable> {
    String name;
    int age;

    public PersonComparable(String name, int age) {
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

    @Override
    public int compareTo(PersonComparable o) {
        return this.age - o.age;
    }

    public static void main(String[] args) {
        PersonComparable[] people = new PersonComparable[]{
                new PersonComparable("xujian", 20),
                new PersonComparable("xiewei", 10)
        };
        System.out.println("排序前");
        for (PersonComparable PersonComparable : people) {
            System.out.print(PersonComparable.getName() + ":" + PersonComparable.getAge());
        }
        Arrays.sort(people);
        System.out.println("\n排序后");
        for (PersonComparable PersonComparable : people) {
            System.out.print(PersonComparable.getName() + ":" + PersonComparable.getAge());
        }
    }
}