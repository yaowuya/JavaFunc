package com.cn.common.javaexercise;

import java.util.Arrays;

/**
 * User: zhongrf
 * Date: 2018/4/9 17:30
 * Description:算法练习
 */
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] array={7,3,2,5,8,4,10,9};

        int[] bubbleArray=bubbleSort(array);

    }

    /**
     * 冒泡排序  从小到大排序
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array){
        if(array==null||array.length==0){
            System.out.println("数组不能为空");
        }

        int[] intArray=array;
        int n=0;
        System.out.println("冒泡开始排序...");
        for(int i=0;i<intArray.length;i++){
            boolean flag=false;
            for(int j=0;j<intArray.length-1-i;j++){
                if(intArray[j]>intArray[j+1]){
                    int temp=intArray[j];
                    intArray[j]=intArray[j+1];
                    intArray[j+1]=temp;
                    flag=true;
                }
                n++;
            }
            if(!flag){
                break;
            }
        }

        System.out.println("循环次数："+n);

        for(int a:intArray){
            System.out.print(a+" ");
        }

        return intArray;
    }
}