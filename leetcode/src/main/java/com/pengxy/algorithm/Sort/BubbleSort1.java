package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/03/08 19:38
 */
public class BubbleSort1 {
    public static void main(String[] args) {
        // int[] arrary = { 1}
        int[] array = {2, 5, 9, 4, 1, 0, 3, 6, 7, 8};
        sort(array);
        Method.printArray(array);
    }

    static void sort(int[] array){
        // 外层循环是未排序的子数组，故而越来越小
        for(int j = array.length - 1; j > 0; j--) {
            // 遍历未排序的数组
            for (int i = 0; i < j; i++) {
                if (array[i] > array[i + 1]) {
                    Method.swapArray(array, i, i + 1);
                }
            }
        }

    }
}
