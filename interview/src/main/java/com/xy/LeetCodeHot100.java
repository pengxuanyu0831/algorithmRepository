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
        Arrays.sort(nums);
        if (nums.length == 0) {
            return 0;
        }

        int res = 1;

        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[right] - nums[left] == 1) {
                log.info("left >>[{}]--[{}]  right >>[{}]--[{}]",left,nums[left],right,nums[right]);
                res += 1;
            }else {
                res = 1;
            }
            left = right;
            right += 1;
        }
        return res;
    }


    public static void main(String[] args) {
        LeetCodeHot100 leetCodeHot100 = new LeetCodeHot100();

        int[] ints1 = {9,1,4,7,3,-1,0,5,8,-1,6};
        int[] ints2 = {100,4,200,1,3,2};

        int longested = leetCodeHot100.longestConsecutive(ints2);
        System.out.println(longested);
    }


    /**
     * #11
     * @param height
     * @return
     */
//    public int maxArea(int[] height) {
//
//    }


    /**
     * #283
     * @param nums
     */
//    public void moveZeroes(int[] nums) {
//
//    }
}
