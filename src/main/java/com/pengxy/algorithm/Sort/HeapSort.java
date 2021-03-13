package com.pengxy.algorithm.Sort;

/**
 * @program algorithm
 * @description: 将初始待排序关键字序列(R1, R2 … .Rn)构建成大顶堆，此堆为初始的无序区；
 * 将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]
 * @author: pengxuanyu
 * @create: 2021/03/13 10:37
 */
public class HeapSort {
    static int length;
    static int[] heapSort(int[] array){
        length = array.length;
        if(array.length < 1) return array;
        // 构建一个最大堆
        buildMaxHeap(array);
        // 循环将首位（堆的最上层为调整后的最大值）与末位交换，再调整堆
        while(length > 0){
            Method.swapArray(array,0,length - 1);
            length--;
            // 这里调整完，最上层的是最大的
            // 递归的入口，这里保证首位根节点必是最大的
            adjustHeap(array,0);
        }
        return array;

    }

    static void buildMaxHeap(int[] array){
        // ? 这里要多看一看
        for(int i = (length / 2) - 1;i >= 0 ;i--){
            adjustHeap(array,i);
        }

    }
    // 递归
    static void adjustHeap(int[] array,int i){
        int maxIndex = i;
        // 左子树大于根节点，则将最大指针指向左子树
        if(i * 2 < length && array[i * 2] > array[maxIndex]){
            maxIndex = i * 2;
        }
        // 如果右子树大于根节点，则将最大指针指向右子树
        if(i * 2 + 1 < length && array[i * 2 + 1] > array[maxIndex]){
            maxIndex = i * 2 + 1;
        }
        if(maxIndex != i ){
            Method.swapArray(array,maxIndex,i);
            adjustHeap(array,maxIndex);
        }
    }
}
