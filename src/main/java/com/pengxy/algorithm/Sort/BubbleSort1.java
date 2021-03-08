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
        for (int j = 0; j < array.length - 1; j--) {
            for (int i = 0; i < 10 - 1; i++) {
                if (array[i] > array[i + 1]) {
                    Method.swapArray(array, i, i + 1);
                }
            }
        }

    }
}
