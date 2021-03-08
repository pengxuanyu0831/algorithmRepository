package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/03/08 16:44
 */
public class SelectionSort1 {
    public static void main(String[] args) {
        int[] array = {2, 5, 9, 4, 1, 0, 3, 6, 7};

        for(int i = 0; i < array.length;i++){
            int minPos = i;
            // 第二次循环，从i的下一位开始，后边当成新数组，重新遍历找最小
            for(int k = i + 1; k < array.length; k++){
                minPos = array[k] < array[minPos]? k:minPos;
            }
            //System.out.print(minPos);

            //int temp = array[i];
            //array[i] = array[minPos];
            // array[minPos] = temp;

            swap(array,i ,minPos);

            System.out.println("经过第" + i + "次循环后的结果为:");
            print(array);
        }
    }

    static void swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static void print(int[] arr){
        //System.out.println();
        for ( int j = 0 ;j < arr.length; j++){
            System.out.print(arr[j] + " ");
        }
    }
}
