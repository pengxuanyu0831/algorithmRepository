package com.pengxy.algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apple.laf.JRSUIUtils;
import com.pengxy.algorithm.Tree.LeetCode.TreeNode;

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

    /**
     * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     *
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     *
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge0088(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int p = m + n - 1;
        // 从后往前排，比num1大的数丢去num1的最后面
        while (i >= 0 && j >= 0) {
            if (nums1[i] <= nums2[j]) {
                nums1[p] = nums2[j];
                p--;
                j--;
            } else {
                nums1[p] = nums1[i];
                p--;
                i--;
            }
        }
        while (j >= 0) {
            nums1[p] = nums2[j];
            p--;
            j--;
        }
    }


    /**
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     *
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST0108(int[] nums) {
        return this.doSort(nums, 0, nums.length);
    }

    private TreeNode doSort(int[] nums, int l, int r) {
        int mid = l + ((r - l) / 2);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = this.doSort(nums, l, mid);
        root.right = this.doSort(nums, mid + 1, r);
        return root;
    }

    /**
     * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow0109(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int n = 1;
        while (n <= rowIndex) {
            list.add(0);
            // 我自增之后，遍历我自己
            for (int i = list.size() - 1; i > 0; i--) {
                // 杨辉三角的核心
                list.set(i, list.get(i) + list.get(i - 1));
            }
            n++;
        }
        return list;
    }

    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // 错误经验，值得记录一下，仅供参考和反思
        if(prices.length <=1){
            return 0;
        }
        if(prices.length == 2){
            if(prices[1] > prices[0]){
                return prices[1] - prices[0];
            }else{
                return 0;
            }
        }
        int[] n1 = new int[2];
        // 倒着找最小的
        int cur = prices.length - 1;
        for (int i = prices.length - 1; i >= 0; i--) {
            if (prices[cur] > prices[i]) {
                n1[0] = prices[i];
                cur = i;
            }
        }
        int min = cur;
        System.out.println(cur + "pr[cur]:"+prices[cur]);
        int temp = prices[cur];
        for (int j = min; j < prices.length; j++) {
            if (prices[min] < prices[j]) {
                n1[1] = prices[j];
                min = j;
            }
        }
        System.out.println(min + "pr[min]:" + prices[min]);
        if (min > cur) {
            return n1[1] - n1[0];
        } else {
            return 0;
        }

    }


    public int maxProfitTRUE(int[] prices) {
        int low = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            low = Math.min(low, prices[i]);
            max = Math.max(max, prices[i] - prices[low]);
        }
        return max;
    }


    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        // 异或
        int cur = nums[0];
        for (int i = 1; i < nums.length ; i++) {
            cur ^= nums[i];
        }
        return cur;
    }



    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 5, 7, 8};

        // Integer[] integers1 = twoSum(integers, 9);


    }
}
