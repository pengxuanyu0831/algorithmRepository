package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description: 选择排序
 * @author: pengxuanyu
 * @create: 2021/02/11 12:05
 */
public class SelectionSort {
    public static int[] selectionSort(int[] datas){
        if(datas.length == 0){
            return datas;
        }
        for(int i = 0;i < datas.length; i++){
            // 现在的i 是最小值的位置
            int minIndex = i;
            int min = datas[minIndex];
            // j 从 i 开始遍历数组，找比 i 更小的数
            for(int j = i ;i < datas.length; j++){
                // 找比 下标i 更小的
                if(datas[j] < datas[minIndex]){
                    minIndex = j;
                }
            }
            // 如果i 不是当前找到最小的，就和最小的交换，始终保证i 是最小的
            if(i != minIndex){
                datas[minIndex] = datas[i];
                datas[i] = min;
            }

        }
        return datas;
    }
}
