package com.pengxy.algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/11/14 15:39
 */
public class array {
    public static Integer[] twoSum(Integer[] ints, Integer target) {
        Map<Integer, Integer> map = new HashMap<>();
        Integer[] result = new Integer[ints.length];
        for (int i = 0; i < ints.length - 1; i++) {
            // 先算出补数是多少
            Integer targetInt = target - ints[i];
            // 判断是否有该补数存在
            if (map.containsKey(targetInt)) {
                result[0] = i;
                result[1] = map.get(targetInt);
                return result;
            }
            // K:ins[i] V:下标
            map.put(ints[i], i);
        }
        return result;
    }


    public int removeDuplicates0026(int[] nums) {
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[cur]) {
                nums[cur + 1] = nums[i];
                cur++;
            }
        }
        return cur + 1;
    }


    public int removeElement0027(int[] nums,int target) {
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                nums[cur] = nums[i];
                cur++;
            }
        }
        return cur ;
    }


    public int searchInsert0035(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * @param nums
     * @return
     * 动态规划最重要的思想就是**利用上一个状态**, 对于本题而言就是: **到底要不要加上上一个状态f(i-1)的信息**, 这完全取决于f(i-1)的**正负情况**, 这样我们就能得出了动态规划的递推公式: f(i)=max{f(i−1)+ai,ai}
     */
    public int maxSubArray0053(int[] nums) {
        int sum = 0;
        int res = nums[0];
        for (int i : nums) {
            if (sum > 0) {
                sum = sum + i;
            } else {
                sum = i;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * @param digits
     * @return
     */
    public int[] plusOne0066(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] temp = new int[digits.length + 1];
        temp[0] = 1;
        return temp;
    }



    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 5, 7, 8};

        // Integer[] integers1 = twoSum(integers, 9);


    }
}
