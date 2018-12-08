package com.cn.exercise.leetcode;

/**
 * User: zhongrf
 * Date: 2018/7/5 17:57
 * Description:从排序数组中删除重复项
 * 不要为其他数组分配额外的空间，您必须通过在 O（1）额外的内存中就地修改输入数组来实现这一点。
 */
public class removeDuplicates {
    public static void main(String[] args) {
        int[] num = {0,1,1,2,2,3,3,4};
        System.out.println(removeDuplicateArray(num));
    }

    public static int removeDuplicateArray(int[] num){
        if(num.length==0){
            return 0;
        }
        int number=0;
        for(int i=0;i<num.length;i++){
            if(num[i]!=num[number]){
                number++;
                num[number]=num[i];
            }
        }
        number+=1;
        return number;
    }
}