package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description: 归并排序
 * @author: pengxuanyu
 * @create: 2021/03/11 10:35
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = { 3, 4, 5, 6, 7, 2, 9, 11, 23, 43};
        sort(array);
        //  Method.printArray(array);
    }

    static void sort(int[] array){
        int mid = array.length/2;
        int[] temp = new int[array.length];
        // 原数组的头结点
        int head = 0;
        // 原数组的中间后一个
        int half = mid + 1;
        // 新数组的头结点
        int temphead = 0;

        while(head <= mid && head < array.length ){
            if(array[head] <= array[half]){
                temp[temphead++] = array[head++];
            }else {
                temp[temphead++] = array[half++];
            }
        }
        while(head <= mid) {
            temp[temphead++] = array[head++];
        }
        while(half < array.length){
            temp[temphead++] = array[half++];
        }
        Method.printArray(temp);

    }
}
