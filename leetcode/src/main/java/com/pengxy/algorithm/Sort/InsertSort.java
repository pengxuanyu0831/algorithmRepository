package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/03/10 17:59
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {2, 5, 9, 4, 1, 0, 3, 6, 7, 8, 11, 23, 43};
        sort(array);
        Method.printArray(array);
    }

    static void sort(int[] array){
        for(int i = 1; i< array.length;i++){
            for(int j = i;j > 0; j--){
                if(array[j-1] > array[j]){
                    Method.swapArray(array,j,j-1);
                }
            }
        }
    }

}
