package com.xy;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @program algorithmRepository
 * @description: https://leetcode.cn/problems/two-sum/?envType=study-plan-v2&envId=top-100-liked
 * @author: pengxuanyu
 * @create: 2024/04/05 21:30
 * @version: 1.0
 */
@Slf4j
public class LeetCodeHot100 {


    /**
     * #1
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        int[] res = new int[2];
        int left = 0;
        int right = 1;

        while (right > left && right < nums.length) {
            if (target == nums[left] + nums[right]) {
                res[0] = left;
                res[1] = right;
                return res;
            } else {
                if (right == nums.length - 1) {
                    left += 1;
                    right = left + 1;
                } else {
                    right++;
                }
            }
        }
        return res;

    }


    /**
     * #49
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, ArrayList<String>> res = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String s1 = String.valueOf(chars);

            if (res.containsKey(s1)) {
                res.get(s1).add(s);
            } else {
                ArrayList<String> values = new ArrayList<>();
                values.add(s);

                res.put(s1, values);
            }
        }

        return new ArrayList<>(res.values());
    }


    /**
     * #128
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
//        Arrays.sort(nums);
//        if (nums.length == 0) {
//            return 0;
//        }
//
//        int res = 1;
//
//        int left = 0;
//        int right = 1;
//        while (right < nums.length) {
//            if (nums[right] - nums[left] == 1) {
//                res += 1;
//                log.info("left >>[{}]--[{}]  right >>[{}]--[{}] res >> {}",left,nums[left],right,nums[right],res);
//            }else {
//                res = 1;
//            }
//            left = right;
//            right += 1;
//        }
//        log.info("res >>> {}" , res);
//        return res;

        Set<Integer> resSet = new HashSet<>();

        for (int i : nums) {
            resSet.add(i);
        }

        int res = 0;

        for (int i : resSet) {
            // 遍历每个数字，看这个数字-1是否存在，即判断当前数字是否为一组顺序数字的开头，如果不存在，即当前数字是一组顺序的开头
            if (!resSet.contains(i - 1)) {
                int currentInt = i;
                int currentRes = 1;

                while (resSet.contains(currentInt + 1)) {
                    currentInt += 1;
                    currentRes += 1;
                }

                res = Math.max(res, currentRes);
            }
        }

        return res;
    }


    /**
     * #283
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow++] = temp;
            }
//            log.info("nums >>>>{}", nums);
        }

    }

    /**
     * #11
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;

        for (int i = 0; i < height.length; i++) {
            int temp = Math.min(height[left], height[right]) * (right - left);
            log.info("temp >>>{}", temp);
            res = Math.max(res, temp);
            log.info("res >>>>{}", res);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }

    /**
     * #42
     * @param height
     * @deprecated 双重遍历，从左至右
     * https://www.bilibili.com/video/BV1Qg411q7ia/?vd_source=9e70f870291cdea2bd0ead8dd57117fb
     * @return
     */
    public int trap1(int[] height) {
        // 左至右
        int n = height.length;
        int[] leftMaxs = new int[n];
        leftMaxs[0] = height[0];
        for (int i = 1; i < n; i++) {
            // 当前值和前一个最大值取较大值
            leftMaxs[i] = Math.max(height[i], leftMaxs[i - 1]);
        }

        // 从右边开始
        int[] rightMaxs = new int[n];
        rightMaxs[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(height[i], rightMaxs[i + 1]);
        }
        int res = 0;

        for (int i = 0; i < n; i++) {
            // Math.min(leftMaxs[i], rightMaxs[i])  >>>> 意思是水桶的高度取决于较短的那块
            // - height[i] 减去高度，就是减去木桶下边的底的高度，就是可以装水的大小
            res += Math.min(leftMaxs[i], rightMaxs[i]) - height[i];
        }

        return res;

    }


    public static void main(String[] args) {
        LeetCodeHot100 leetCodeHot100 = new LeetCodeHot100();

        int[] ints1 = {9,1,4,7,3,-1,0,5,8,-1,6}; // 3 4 5 6 7 8 9
        int[] ints2 = {100,4,200,1,3,2};
        int[] ints3 = {0,1,0,3,12};
        int[] ints4 = {1, 1};
        int[] ints5 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

//        int longested = leetCodeHot100.longestConsecutive(ints1);
//        leetCodeHot100.moveZeroes(ints3);
        leetCodeHot100.maxArea(ints4);

//        System.out.println(longested);
    }



}
