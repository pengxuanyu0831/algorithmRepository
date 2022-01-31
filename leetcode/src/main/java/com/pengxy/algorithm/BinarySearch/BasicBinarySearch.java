package com.pengxy.algorithm.BinarySearch;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/01/24 21:14
 */
public class BasicBinarySearch {
    public static int binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (left + right)/2 -1 ;
            if(target == nums[mid]){
                return mid;
            }else if(nums[mid] < target){
                left = mid  + 1;
            }else if(nums[mid] > target){
                right = mid - 1 ;
            }
        }
        return -1;
    }

    public static void printMethod(int[] nums){
        for(int i = 0 ;i < nums.length;i++){
            System.out.print("这颗树为:" + nums);
        }
    }

    public static void main(String[] args) {
        binarySearch(new int[]{1, 2, 3, 5, 7},2);
    }
}
