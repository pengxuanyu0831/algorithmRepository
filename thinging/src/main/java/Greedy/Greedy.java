package Greedy;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @program algorithm
 * @description: 贪心算法
 * @author: pengxuanyu
 * @create: 2022/04/26 22:24
 */
@Slf4j
public class Greedy {


    /**
     * #455
     *
     * @param g -> 小朋友
     * @param s -> 饼干
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        g = Arrays.stream(g).sorted().toArray();
        s = Arrays.stream(s).sorted().toArray();
        int count = 0;
        int xpy = g.length - 1;
        // 遍历饼干
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i] >= g[i]) {
                xpy--;
                count++;
            }
        }
        return count;
    }


    /**
     * #376
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        int currDiff = 0;
        int preDiff = 0;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            currDiff = nums[i + 1] - nums[i];
            // 这有啥说法吗？
            // 默认峰值在最左侧                         默认峰值在最右侧
            if ((currDiff > 0 && preDiff <= 0) || (currDiff < 0 && preDiff >= 0)) {
                count++;
                preDiff = currDiff;
            }
        }
        return count;
    }

    /**
     * #53 dp可解此题
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int num : nums) {
            // 这里算总和
            count += num;
            // 跟随每次遍历，给结果赋值
            if (count >= result) {
                result = count;
            }
            // 当累计和小于0时，则重新计数
            if (count <= 0) {
                count = 0;
            }
        }
        return result;
    }


    /**
     * #122
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;

        // 解法2 这个用时更短
        // 构造一个利润数组
/*        int[] made = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++) {
            made[i - 1] = prices[i] - prices[i - 1];
        }

        int count = 0;
        for (int k = 0; k < made.length; k++) {
            if (made[k] > 0) {
                count += made[k];
            }
        }
        return count;*/
    }


    /**
     * #55
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int steps = 0;
        for (int i = 0; i <= steps; i++) {
            steps = Math.max(i + nums[i], steps);
            if (steps >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }


    /**
     * #45
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int steps = 0;
        int curr = 0;
        int next = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 里面的next -> 下一步最远距离，其实是前一个下标的最远距离，一直在比较，看看当前下标是否能大于前一个下标的最远距离
            next = Math.max(next, i + nums[i]);
            if (i == curr) {
                curr = next;
                steps++;
            }
        }
        return steps;
    }


    /**
     * 409
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int[] in = new int[128];
        int result = 0;
        for (char ss : s.toCharArray()) {
            in[ss]++;
            // 如果出现偶数次的字母
            if (in[ss] % 2 == 0) {
                result += 2;
            }
        }
        for (char a = 'A'; a < 'z'; a++) {
            if ((in[a] % 2) == 1) {
                result++;
                break;
            }
        }

        return result;
    }


    /**
     * #1005
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (k > 0 && nums[i] < 0) {
                k--;
                nums[i] = -nums[i];
            }
            sum += nums[i];
            min = Math.min(min, nums[i]);
        }

        if (k % 2 != 0 && k > 0) {
            sum = sum - 2 * min;
        }
        return sum;
    }


    /**
     * #134
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 需要的油量
        int need = 0;
        // 起始的下标
        int start = 0;
        // 剩余的油量
        int have = 0;
        for (int i = 0; i < cost.length; i++) {
            // 这里起始抽象成了两个站点，总的所需油量，必须大于等于提供的油量，start -> k 如果所需油量不足，则应当将起始位置设为k+1
            need += gas[i] - cost[i];
            have += gas[i] - cost[i];
            if (need < 0) {
                start = i + 1;
                need = 0;
            }
        }
        return have < 0 ? -1 : start;
    }


    /**
     * #135
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int[] candys = new int[ratings.length];
        Arrays.fill(candys, 1);
        // 先 从左往右
        for (int i = 1; i < ratings.length; i++) {
            // 当右边评分高于左边时
            if (ratings[i] > ratings[i - 1]) {
                candys[i] = candys[i - 1] + 1;
            }
        }
        // 再返过来遍历当左边评分大于右边时
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i + 1] < ratings[i]) {
                candys[i] = Math.max(candys[i], candys[i + 1] + 1);
            }
        }

        int sum = 0;
        for (int i = 0; i < candys.length; i++) {
            System.out.println(candys[i]);
            sum += candys[i];
        }

        return sum;
    }


    /**
     * #860
     * @param bills
     * 当解题没有思路时，可以从各种情况的角度开始分析，比如本题，只可能会收到3种钱，那么分别处理即可。
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        int tw = 0;
        for (int i : bills) {
            switch (i) {
                case 5:
                    five++;
                    break;
                case 10:
                    if (five <= 0) {
                        return false;
                    }
                    five--;
                    ten++;
                    break;
                case 20:
                    // 当收入20时，优先支付10元+5元，再支付3张5元
                    if (five > 0 && ten > 0) {
                        tw++;
                        five--;
                        ten--;
                        break;
                    }
                    five = five - 3;
                    if (five < 0) {
                        return false;
                    }
            }
        }
        return true;
    }

    /**
     * #406
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        // 按身高排序，相同身高再按k小的站前面
        Arrays.sort(people, (a, b) ->{
            log.info("a->{} b->{}",a,b);
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return b[0] - a[0];
            }
        });
        LinkedList<int[]> qu = new LinkedList<>();
        log.info("people{}", (Object) people);
        for (int[] person : people) {
            int pos = person[1];
            qu.add(pos, person);
        }
        return qu.toArray(new int[people.length][]);
    }


    /**
     * #561
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 != 0) {
                sum += nums[i];
            }

        }
        return sum;
    }


    /**
     * #11
     * @param height
     * @return
     * 傻屌测试用例 双指针嵌套循环必超时，弃用
     */
    public int maxArea(int[] height) {
        int maxS = 0;
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        // 双指针遍历 --超时
/*        for (int i = 0; i < height.length; i++) {
            for (int k = height.length - 1; k > i; k--) {
                maxS = Math.max(maxS, (k - i) * Math.min(height[i], height[k]));
            }
        }*/

        // 面积最大 -> 尽量长且尽量高，先从尽量长开始
        int close = 0;
        int far = height.length - 1;
        while (close <= far) {
            if (height[close] <= height[far]) {
                maxS = Math.max(maxS, (far - close) * height[close]);
                close++;
            } else {
                maxS = Math.max(maxS, (far - close) * height[far]);
                far--;
            }
        }
        return maxS;
    }


    /**
     * #452
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] < o2[1] ? -1 : 1;
            }
        });
        int arr = 1;
        for (int i = 1; i < points.length; i++) {
            // 气球i 和 气球i-1 是否挨着 -> i的左边界，和i-1的右边界比较
            if (points[i][0] > points[i - 1][1]) {
                arr++;
            } else {
                // 更新重叠气球的最小右边界
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        Greedy g = new Greedy();
        int[] ins = new int[]{1,8,6,2,5,4,8,3,7};
        int[] ins2 = new int[]{3, 4, 3};
        int[][] people1 = new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        //int i = g.wiggleMaxLength(ins);
        // log.info("{}", (Object) g.reconstructQueue(people1));
        log.info("{}+++{}", people1, g.findMinArrowShots(people1));
    }
}
