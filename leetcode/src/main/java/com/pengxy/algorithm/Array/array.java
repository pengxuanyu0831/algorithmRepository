package com.pengxy.algorithm.Array;

import java.util.*;

import com.pengxy.algorithm.Tree.LeetCode.TreeNode;
import lombok.extern.slf4j.Slf4j;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/11/14 15:39
 */
@Slf4j
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


    /**
     * 给定一个有序数组 nums，对数组中的元素进行去重，使得原数组中的每个元素只有一个。最后返回去重以后数组的长度值。
     * @param nums
     * @return
     */
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
     * #88
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
     * #80
     * @param nums
     * @deprecated 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (i < 2 || nums[j] != nums[i - 2]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }


    /**
     * #169
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 1;
        int major = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == major) {
                count++;
            }else{
                count--;
            }
            if (count == 0) {
                major = nums[i];
                count = 1;
            }
        }
        return major;
    }




    /**
     * #189
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = 0;
        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    public void rotate1(int[] nums, int k) {
        if (nums.length == 1) {
            return;
        }
        int kTemp = k;
        int[] temp = new int[nums.length + k];
        for (int i = 0; i < nums.length; i++) {
            temp[k] = nums[i];
            k++;
        }
        log.info("-------{}", temp);

        for (int f = 0; f < kTemp; f++) {
            int fff = temp[f + nums.length];
            temp[f] = fff;
        }
        log.info("temp >>>> {}", temp);

        for (int o = 0; o < nums.length; o++) {
            nums[o] = temp[o];
        }
        log.info("========{}", nums);

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        array array = new array();
        array.rotate1(nums, 3);
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
     * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
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


    /**
     * 给定一个已按照 非递减顺序排列 的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
     *
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     *
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int j = numbers.length - 1;
        for (int i = 0;i < j ;) {
            int sum = numbers[i] + numbers[j];
            System.out.println("j="+j+"sum="+sum);
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return null;
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 参考资料 摩尔算法：https://www.zhihu.com/question/49973163/answer/235921864
     * @param nums
     * @return
     */
    public int majorityElement0169(int[] nums) {
        int count = 1;
        int major = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == major) {
                count++;
            }else{
                count--;
            }
            if (count == 0) {
                major = nums[i];
                count = 1;
            }
        }
        return major;
    }


    /**
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false
     * @param nums
     * @return
     */
    public boolean containsDuplicate0217(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        if (set.size() == nums.length) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，使得nums [i] = nums [j]，并且 i 和 j的差的 绝对值 至多为 k

     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (Math.abs(map.get(nums[i]) - i) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }


    public List<String> summaryRanges(int[] nums) {
        int[] numss = Arrays.copyOf(nums, nums.length + 1);
        numss[numss.length - 1] = numss[numss.length -2] + 1;
        List<String> list = new ArrayList<String>();
        int j = numss[0];
        int start = numss[0];
        for (int i = 0; i < numss.length; i++) {
            if (numss[i] == j) {
                j++;
            } else {
                System.out.println("===="+"j:"+j + "start:"+start+"i:"+i);
                if (start == numss[i - 1]) {
                    list.add(start + "");
                } else {
                    list.add(start + "->" + numss[i-1]);
                }
                start = numss[i];
                j = numss[i] + 1;
                System.out.println("j:"+j + "start:"+start+"i:"+i);
            }
        }
        return list;
    }



    // #268
    public int missingNumber(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
            result ^= i;
        }
        return result;
    }


    // #283  不用排序的沃日
    public void moveZeroes(int[] nums) {
        int current = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] != 0){
                nums[current] = nums[i];
                if(current++ != i){
                    nums[i] = 0;
                }
            }
        }
    }



    // #349
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet set1 = new HashSet();
        HashSet result = new HashSet();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }

        for (int j = 0; j < nums2.length; j++) {
            if (set1.contains(nums2[j])) {
                result.add(nums2[j]);
            }
        }

        int[] resultArr = new int[result.size()];
        int index = 0;
        for (int k = 0;k < result.size();k++) {
            resultArr[index++] = k;
        }

        return resultArr;

    }



    // #350
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();

        for (int i : nums1) {
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }

        int index = 0;
        for (int i : nums2) {
            int temp = map1.getOrDefault(i, 0);
            if (temp > 0) {
                // 之所以要覆盖，是因为已经有了才去覆盖，如果没有
                nums1[index++] = i;
                map1.replace(i, temp - 1);
            }
        }

        int[] res = new int[nums1.length];
        System.arraycopy(nums1,0,res,0,index);
        return res;
    }



    // #414
    //执行用时：
    //5 ms
    //在所有 Java 提交中击败了
    //24.88%
    //的用户
    //内存消耗：
    //38.1 MB
    //, 在所有 Java 提交中击败了
    //82.28%
    //的用户
    //通过测试用例：
    //31 / 31
    public static int thirdMax(int[] nums) {
        TreeSet<Integer> treeSet = (TreeSet<Integer>) new TreeSet<Integer>().descendingSet();

        for (int i = 0; i < nums.length; i++) {
            treeSet.add(nums[i]);
        }


        Iterator<Integer> iterator = treeSet.iterator();
        int i = iterator.next();


        if (treeSet.size() == 1) {

            return treeSet.first();
        }
        int j = iterator.next();

        if (treeSet.size() == 2) {

            if (i > j) {
                return i;
            } else {
                return j;
            }
        }

        int k = iterator.next();
        return i ;
    }


    // #448
    /**
     *
     * 找出 1 - n 中没有出现的数字。不能使用额外的空间，两次循环时间复杂度为 2O(n)，即为 O(n)。
     *
     * 解题思路：使用数组的下标来标记数字的出现于否，通过一遍遍历即可标记出全部已经出现的数组
     *
     * [4,3,2,7,8,2,3,1] 初始数据
     *
     * [4,3,2,-7,8,2,3,1] 第一个数据 4 出现，将数组的第四个也就是下标 3 的数据修改为负数。-7 计算时，通过绝对值处理一下即可不影响数据的计算
     * [4,3,-2,-7,8,2,3,1]
     * [4,-3,-2,-7,8,2,3,1]
     * [4,-3,-2,-7,8,2,-3,1]
     * [4,-3,-2,-7,8,2,-3,-1]
     * [4,-3,-2,-7,8,2,-3,-1]
     * [4,-3,-2,-7,8,2,-3,-1]
     * [-4,-3,-2,-7,8,2,-3,-1]
     *
     * 计算结束，数组的第五个，第六个依然为整数，证明 5,6 没有出现
     *
     * @param nums
     * @return
     *
     * O(2n)
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {

            if (nums[Math.abs(nums[i] - 1)] > 0) {
                nums[Math.abs(nums[i] - 1)] = 0 - nums[Math.abs(nums[i] - 1)];
            }
        }
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;

    }



    // #455 贪心算法
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        int count = 0;
        // 用最多的饼干满足最多的小孩，所以遍历饼干而不是小孩
        for (int i = 0; i < s.length && index < g.length; i++) {
            if (s[i] >= g[index]) {
                index++;
                count++;
            }
        }
        return count;
    }



    // #485  soluation1
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                target++;
                if (target > count) {
                    count = target;
                }
            } else {
                target = 0;
            }
        }
        return count;
    }


    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0;
        int temp = timeSeries[0];
        for (int i = 0; i < timeSeries.length; i++) {
            if (timeSeries[i] - temp <= duration) {
                res += timeSeries[i] - temp;
            } else {
                res += duration;
            }
            temp = timeSeries[i];
        }
        return res + duration;
    }
}
