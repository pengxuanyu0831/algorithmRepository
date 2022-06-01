package Arraysss;

import java.util.Arrays;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/05/30 23:05
 */
public class Arraysss {
    /**
     * #704
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            // 中间值大于目标值 -----> 目标值落在了左半区间
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * #27
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow;
        for (slow = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }


    /**
     * #26
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int fast = 1;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
                fast++;
            } else {
                fast++;
            }
        }
        return slow + 1;
    }


    /**
     * #283
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                fast++;
                slow++;
            } else {
                fast++;
            }
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


    /**
     * #844
     * @param s
     * @param t
     * @return
     * 画图 画图 画图
     */
    public boolean backspaceCompare(String s, String t) {
        StringBuilder ssb = new StringBuilder();
        StringBuilder sst = new StringBuilder();
        for (char ss : s.toCharArray()) {
            if (ss != '#') {
                ssb.append(ss);
            } else if (ssb.length() > 0) {
                ssb.deleteCharAt(ssb.length() - 1);
            }
        }

        for (char tt : t.toCharArray()) {
            if (tt != '#') {
                sst.append(tt);
            } else if (sst.length() > 0) {
                sst.deleteCharAt(sst.length() - 1);
            }
        }
        return sst.toString().equals(ssb.toString());
    }


    /**
     * #977
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int slow = 0;
        int fast = nums.length - 1;
        int[] result = new int[nums.length];
        int j = nums.length - 1;
        while (slow <= fast) {
            if (nums[slow] * nums[slow] < nums[fast] * nums[fast]) {
                result[j--] = nums[slow] * nums[slow];
                slow++;
            } else {
                result[j--] = nums[fast] * nums[fast];
                fast--;
            }
        }
        return result;
    }

}
