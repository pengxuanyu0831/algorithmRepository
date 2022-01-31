package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/03/08 19:44
 */
public class Method {
    static void printArray(int[] arrary) {
        System.out.print("[ ");
        for (int j = 0; j < arrary.length; j++) {
            System.out.print(arrary[j] + ", ");
        }
        System.out.println("]");
    }

    static void swapArray(int[] array,int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static int findMax(int[] array){
        for(int i = 0; i < array.length - 1;i++){
            if(array[i] > array[i + 1]){
                swapArray(array,i,i + 1);
            }
        }
        return array[array.length - 1];
    }

}
