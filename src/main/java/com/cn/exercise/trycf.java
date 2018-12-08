package com.cn.exercise;

/**
 * User: zhongrf
 * Date: 2018/6/20 14:27
 * Description:Try……catch……finally中return的测试
 * （1）、try：它里面放置可能引发异常的代码
 （2）、catch：后面对应异常类型和一个代码块，用于表明该catch块用于处理这种类型的代码块，可以有多个catch块。
 （3）、finally：主要用于回收在try块里打开的物力资源（如数据库连接、网络连接和磁盘文件），异常机制总是保证
 finally块总是被执行。只有finally块，执行完成之后，才会回来执行try或者catch块中的return或者throw语句，如果
 finally中使用了return或者throw等终止方法的语句，则就不会跳回执行，直接停止。
 */
public class trycf {
    public static void main(String[] args){
        System.out.println(tryTest());
        System.out.println(tryTest1());
    }

    public static String tryTest(){
        int value=0;
        try {
            System.out.println("try……");

            //等式1/0 ：分母为0 的明显错误          ——制造错误（用于抛异常）
            int result = 1 / value;

            return "111";
        }catch (Exception e){
            System.out.println("catch……");
            return "444";
        }finally {
            System.out.println("finally……");
            return "333";
        }
    }

    /**
     *  程序中try内部没有异常的情况下，若有finally，且finally中没有return。
     *  若在try中遇到return，则先跳去执行finally中的代码，在回来执行try中return
     *
     * @return
     */
    public static String tryTest1(){
        try {
            System.out.println("try1……");

            return "111";
        }catch (Exception e){
            System.out.println("catch1……");
        }finally {
            System.out.println("finally1……");
        }
        return "";
    }
}