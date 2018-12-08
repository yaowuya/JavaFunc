package com.cn.exercise.generic;

/**
 * User: zhongrf
 * Date: 2018/5/30 11:16
 * Description:
 */
public class TestGeneric {
    public static void main(String[] args) {
        GenericStack<String> genericStack=new GenericStack<>();
        GenericStack<Integer> integerGenericStack=new GenericStack<>();

        genericStack.push("a");
        genericStack.push("b");
        genericStack.push("c");

        integerGenericStack.push(1);
        integerGenericStack.push(2);
        integerGenericStack.push(3);

        genericStack.printOut();
        integerGenericStack.printOut();

    }
}