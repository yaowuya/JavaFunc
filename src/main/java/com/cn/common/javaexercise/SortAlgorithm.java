package com.cn.common.javaexercise;

import java.util.Arrays;

/**
 * User: zhongrf
 * Date: 2018/4/9 17:30
 * Description:算法练习
 */
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] array={11,10,55,78,100,111,45,56,79,90,345,1000};

        int[] bubbleArray=bubbleSort(array);


        int[] insertSort=insertSort(array);

    }

    /**
     * 冒泡排序  从小到大排序
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array){
        if(array==null||array.length==0){
            System.out.println("数组不能为空");
            return null;
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
        System.out.println(" ");
        return intArray;
    }

    /**
     * 插入排序
     * @param array
     * @return
     */
    public static int[] insertSort(int[] array){
        if(array==null||array.length==0){
            System.out.println("数组不能为空");
            return null;
        }

        int[] insertArray=array;
        int n=0;
        System.out.println("插入开始排序...");
        for(int i=1;i<insertArray.length;i++){
            int temp=insertArray[i];
            int j=i-1;
            for(;j>=0;j--){
                n++;
                if(insertArray[j]>temp){
                    insertArray[j+1]=insertArray[j];
                }else{
                    break;
                }
            }

            insertArray[j+1]=temp;
        }
        System.out.println("循环次数："+n);
        output(insertArray);
        return insertArray;
    }

    public static void output(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        }
    }
}