package com.pengxy.algorithm.Int;

import com.pengxy.algorithm.Sort.Method;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/03/15 12:49
 */
public class FindTarget {
    public static void main(String[] args) {
        int[] array = {1, 23, 5, 6, 7, 32, 6, 8, 3};
        int[] result = findTarget(array, 7);
        for(int k = 1 ;k < result.length;k++){
            System.out.println(k);
        }
    }

    public static int[] findTarget(int[] array, int target){
        for(int i = 1;i < array.length;i++){
            for(int j = i + 1; j < array.length;j++){
                if(target == array[i] + array[j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }
}
