package com.pengxy.algorithm.Sort;

import javax.swing.plaf.IconUIResource;

/**
 * @program algorithm
 * @description: 计数排序，适用数量大，范围小的数组
 * @author: pengxuanyu
 * @create: 2021/03/11 19:38
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] array = {0,9,7,6,4,4,4,5,2,1,1,9,0,0,0,2};

        int[] result = sort(array);
        Method.printArray(sort(array));
    }

    // 将原数组中等于i的元素的个数，作为新数组的index
    static int[] sort(int[] array){
        // 新数组
        int[] result = new int[array.length];
        // 范围 0-10
        int[] count = new int[10];
        // 将原数组的元素，当成count数组的index读取
        for(int i = 0;i<array.length;i++){
            count[array[i]]++;
        }
        System.out.println(count.toString());

        //return count;
        for(int i = 0,j = 0; i < count.length;i++){
            // count数组的元素，是对应index的个数
            while(count[i]-- > 0){
                result[j++] = i;
            }
        }
        return result;
    }
}
