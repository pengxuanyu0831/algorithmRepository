package Arraysss;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

    /**
     * #209
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int start = 0;
        int end;
        int sum = 0;
        // 右端点一直在动
        for (end = 0; end < nums.length; end++) {
            sum += nums[end];
            // 当总和大于或等于目标值时
            while (sum >= target) {
                result = Math.min(result, end - start + 1);
                sum -= nums[start];
                // 移动左端点
                start++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }


    /**
     * #904
     * @param fruits
     * @return
     * 滑动窗口
     */
    public int totalFruit(int[] fruits) {
        int left = 0;
        int i = 0;
        int basket1 = fruits[0];
        int basket2 = fruits[1];
        int result = 0;
        for (i = 0; i < fruits.length; i++) {
            if (fruits[i] == basket1 || fruits[i] == basket2) {
                result = Math.max(result, i - left + 1);
            } else {
                left = i - 1;
                basket1 = fruits[left];
                // left 要把前面的都清零
                while (left >= 1 && fruits[left] == basket1) {
                    left--;
                }
                basket2 = fruits[i];
                result = Math.max(result, i - left + 1);
            }
        }
        return result;
    }
    //
    public int totalFruitTwo(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0;
        int r = 0;
        int result = Integer.MIN_VALUE;
        for (r = 0; r < fruits.length; r++) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            while (map.size() > 2) {
                Integer integer = map.get(fruits[l]);
                integer--;
                if (integer == 0) {
                    map.remove(fruits[l]);
                }
                l++;
            }
            result = Math.max(result, r - l + 1);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] ints = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        Arraysss arraysss = new Arraysss();
        int i = arraysss.totalFruitTwo(ints);
        System.out.println(i);
    }

}
