package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description: 希尔排序
 * @author: pengxuanyu
 * @create: 2021/03/10 18:18
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {2, 5, 9, 4, 1, 0, 3, 6, 77, 8, 11, 23, 43};
        sort(array);
        Method.printArray(array);
    }

    static void sort(int[] array){
        // Kuth序列
        int h = 1;
        while(h <= array.length/3 ){
            h = h*3 +1;
        }
        // int gap = 4;
        //for(int gap = array.length >> 1;gap >0 ;gap /= 2) {
        for(int gap = h;gap >0 ;gap = (gap - 1)/3) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i; j > gap - 1; j -= gap) {
                    if (array[j - gap] > array[j]) {
                        Method.swapArray(array, j, j - gap);
                    }
                }
            }
        }
    }
}
