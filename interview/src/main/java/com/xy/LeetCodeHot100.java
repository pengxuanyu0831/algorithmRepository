package com.xy;

import java.util.*;

/**
 * @program algorithmRepository
 * @description: https://leetcode.cn/problems/two-sum/?envType=study-plan-v2&envId=top-100-liked
 * @author: pengxuanyu
 * @create: 2024/04/05 21:30
 * @version: 1.0
 */
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
}
