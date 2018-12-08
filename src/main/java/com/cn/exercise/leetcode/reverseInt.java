package com.cn.exercise.leetcode;

/**
 * User: zhongrf
 * Date: 2018/6/20 17:31
 * Description:给定一个 32 位有符号整数，将整数中的数字进行反转。
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 */
public class reverseInt {
    public static void main(String[] args) {
        System.out.println(solution(-1290));
        System.out.println(solution2(-2478));
    }

    public static int solution(int x) {
        long result = 0;
        int tmp=Math.abs(x);
        while (tmp > 0) {
            result = result * 10 + tmp % 10;
            tmp = tmp / 10;
            if(result>Integer.MAX_VALUE){
                return 0;
            }
        }
        return (int)(x>=0?result:-result);
    }

    public static int solution2(int x){
        int temp=Math.abs(x);
        StringBuilder str=new StringBuilder(Integer.toString(temp));
        str=str.reverse();
        if(Long.parseLong(str.toString())>Integer.MAX_VALUE){
            return 0;
        }
        return x>0?Integer.parseInt(str.toString()):-Integer.parseInt(str.toString());
    }
}