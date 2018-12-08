package com.cn.common.algorithm;

/**
 * User: zhongrf
 * Date: 2018/4/9 17:30
 * Description:算法练习
 */
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] array={11,10,55,78,100,111,45,56,79,90,345,1000};

//        int[] bubbleArray=bubbleSort(array);
//
//
//        int[] insertSort=insertSort(array);

        int[] quickArray={11,10,55,78,100,111,45,56,79,90,345,1000};
        quickSort(quickArray,0,quickArray.length-1);
        output(quickArray);

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

    public static void quickSort(int[] a,int left,int right){
        //找到递归算法的出口
        if(left>right){
            return;
        }

        //存
        int i=left;
        int j=right;
        int key=a[left];

        while (i<j){
            //从右往左找到第一个小于key的数
            while (i<j&&a[j]>key){
                j--;
            }
            //从左往右找到第一个大于key的数
            while(i<j&&a[i]<=key){
                i++;
            }
            //交换
            if(i<j){
                int temp=a[i];
                a[i]=a[j];
                a[j]=temp;
            }
        }

        //调整key的位置
        int p=a[i];
        a[i]=a[left];
        a[left]=p;

        quickSort(a,left,i-1);
        quickSort(a,i+1,right);
    }
}