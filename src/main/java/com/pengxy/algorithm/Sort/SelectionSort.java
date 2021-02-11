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
        for(int i = 0;i < datas.length;i++){
            // 当前最小值
            int miniIndex = i;
            for(int j = i;j < datas.length ;j++){
                // 如果遍历后续的值，出现小于基准值
                if(datas[j] < datas[miniIndex]){
                    // 记录小于基准值的索引位置
                    miniIndex = j;
                }
            }
            // datas[miniIndex] 现在是最小的值
            int temp = datas[miniIndex];
            datas[miniIndex] = datas[i];
            datas[i] = temp;
        }
        return datas;
    }
}
