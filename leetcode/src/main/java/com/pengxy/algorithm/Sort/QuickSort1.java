package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description: 快速排序
 * @author: pengxuanyu
 * @create: 2021/03/11 16:50
 */
public class QuickSort1 {
    public static void main(String[] args) {
        int[] array = {2, 5, 9, 4, 1, 0, 3, 6, 77, 8, 11, 23, 43};
        sort(array,0,array.length - 1);
        Method.printArray(array);
    }

    static void sort(int[] array,int leftBound,int rightBound){
        if(leftBound >= rightBound) return;
        int mid = partional(array,leftBound,rightBound);
        sort(array,leftBound,mid -1 );
        sort(array,mid + 1,rightBound);
    }

    static int partional(int[] array,int leftBound,int rightBound){
        // 从数组中找出一个基准值，小于基准值得统统放左边，大于基准值的统统放右边，然后不断的递归
        // int bivot = (int) (leftBound + Math.random() * (rightBound - leftBound + 1));
        int pivot = array[rightBound];
        int left = leftBound;
        int right = rightBound - 1;

        while(left <= right){
            // 左指针小于基准值，一直向右移动
            while(left <= right && array[left] <= pivot){
                left++;
            }
            while(left <= right && array[right] > pivot){
                right--;
            }
            if(left < right){
                Method.swapArray(array,left,right);
            }
        }
        Method.swapArray(array,left,rightBound);

        return left;
    }
}
