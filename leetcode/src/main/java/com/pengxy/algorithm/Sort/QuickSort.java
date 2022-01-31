package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description: 快速排序，找一个基准值，小的丢左边，大的丢右边
 * @author: pengxuanyu
 * @create: 2021/02/10 14:36
 */
public class QuickSort {
    public static int quickSort(int[] datas ,int start ,int end){
        int base = datas[start];
        while(start < end){
            while(start < end && datas[end] >= base){
                end--;
            }
            // 比基准值小的数，和最左端交换
            datas[start] = datas[end];
            while(start < end && base > datas[start]){
                start++;
            }
            datas[end] = datas[start];
        }
        datas[start] = base;
        return base;
    }
    }

