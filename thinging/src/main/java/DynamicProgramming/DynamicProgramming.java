package DynamicProgramming;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/01/31 15:25
 */
@Slf4j
public class DynamicProgramming {
    // # 第0个动态规划的题目 ----实现斐波那契数列  #509
    public int fib(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 递归
    public int fibSolution2(int number) {
        if (number < 2) {
            return number;
        }
        int[] db = new int[100];
        db[0] = 0;
        db[1] = 1;
        return fibSolution2(number - 1) + fibSolution2(number - 2);
    }

    /**
     * #70 爬楼梯，high层楼梯到顶，每次1阶 或 2阶
     * no.1  爬到第 i 层，有dp[i]种方法
     * no.2 确定递推方式  爬到第i-1 层，有dp[i-1]种方法
     *                  爬到第i-2 层，有di[i-2]种方法，所以推导出到第i层，只有两个路径dp[i-1] + dp[i-2]
     * no.3 初始化数组，找出int[0] int[1] 或是有特殊下标的值
     *                 这里初始值其实可以设置为第一层楼梯和第二层楼梯 ，那么初始值就很好定义了
     * no.4 确定遍历顺序，这里很明显应该是递增，所以是顺序遍历
     * no.5
      */
    public int steps(int high) {
        if (high <= 1) {
            return high;
        }
        int[] steps ={0,1,2};
        for (int i = 3; i <= high; i++) {
            int sum = steps[1] + steps[2];
            steps[1] = steps[2];
            steps[2] = sum;
        }
        return steps[2];
    }

    /**
     *
     * @param cost
     * @return
     * #746
     * no.1 dp[i] 最小开销 i -> 第i个台阶
     * no.2 找关系 确定dp[i] 的最小值，其实就是确定do[i-1] dp[i-2]的最小值  >>>> dp[i] = min(dp[i-1],dp[i-2])
     * no.3 确定初始值 dp[1]
     * no.4 确定顺序 递增
     * no.5 写公式 返回倒数最后两个中，较小的那个
     */
    public int climb(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            // 第i阶的花销 = cost[i]的花销 + 第i阶前两阶中较小的那个
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        // 可以视为到最后一阶台阶就完成了，不需要花费了，所以这里取倒数最后两步中较小的那个
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }


    /**
     * #62
     * @param high
     * @param weight
     * @return
     * dp数组 表示从（0，0）开始，到(i,j)有dp[i][j]种走法
     * d[i][j] = dp[i-1][j] + dp[i][j-1] 要么从上面下来，要么从左边过来
     */
    public int paths(int high, int weight) {
        // 二维数组
        int[][] dp = new int[high][weight];
/**         初始化 即到dp[i][0] 的路程只有1中走法，所以 1 1 1 1 ... weight
 *                                                  1
 *                                                  1
 *                                                 ...
 *                                                 high
 */
        for (int i = 0; i < high; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < weight; j++) {
            dp[0][j] = 1;
        }
        // 循环嵌套
        for (int i = 1; i < high; i++) {
            for (int j = 1; j < weight; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[weight - 1][high - 1];
    }

    /**
     * #62的DFS深度优先搜索实现，leetcode会超时
     * @param m
     * @param n
     * @return
     */
    public int pathsDfs(int m, int n) {
        return doPathDfs(1, 1, m, n);
    }

    private int doPathDfs(int i, int j, int m, int n) {
        // 越界
        if (i > m || j > n) {
            return 0;
        }
        if (i == m && j == n) {
            return 1;
        }
        return this.doPathDfs(i + 1, j, m, n) + this.doPathDfs(i, j + 1, m, n);
    }

    /**
     * 有路障的话 ob[i][j] == 1 意味着路障后边的dp[i][j]都是0
     * @param obstacleGrid
     * @return
     */
    public int path2(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;

        int[][] dp = new int[n][m];

        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] == 0 && dp[i - 1][0] == 1) {
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < m; j++) {
            if (obstacleGrid[0][j] == 0 && dp[0][j - 1] == 1) {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // 如果出现路障
                if (obstacleGrid[i][j] == 1) {
                    continue;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    /**
     * #343
     * i ->> 数字 dp[i] ->>拆分后最大的乘积
     * 最大值dp[i] ->> 假定
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        // 假定i + j = n
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) {
                // 这里两个max是为什么
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[i - j] * j));
            }
        }
        return dp[n];
    }

    /**
     * #96
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        // dp[i] += 已i为头结点的左子树的数量 * 已i为头结点右子树的数量
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // 数量 i = 1  -->  dp[3] = dp[1-1] *
                // !!!!排列组合，所以这里是乘法!!!!
                // 左右子树一一配对，所以乘法
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }


    /**
     * #118
     * @param n
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<Integer> num = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        num.add(1);
        result.add(num);
        for (int i = 1; i < numRows; i++) {
            List<Integer> list = result.get(i - 1);
            List<Integer> current = new ArrayList<>();
            current.add(1);
            for (int k = 1; k < i; k++) {
                current.add(list.get(k) + list.get(k - 1));
            }
            current.add(1);
            result.add(current);

        }
        return result;

    }


    /**
     * #120
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        // 走到
        int[] dp = new int[triangle.size() + 1];
        dp[0] = triangle.get(0).get(0);

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> currList = triangle.get(i);
            for (int k = 0; k < currList.size(); k++) {
                dp[k] = Math.min(dp[k], dp[k + 1]) + currList.get(k);
            }
        }
        return dp[triangle.size() - 1];
    }

    public void bagProblem1() {
        // 物品重量
        int[] weight = {1, 3, 4};
        // 物品价值
        int[] value = {15, 20, 30};
        // 背包最大重量
        int maxWeight = 4;

        // 数组 dp[i][j] --->>> 从下标 0-i 取物，放入容量为j的背包，【总价值】为dp[i][j]
        int[][] dp = new int[weight.length + 1][value.length + 1];

        // 初始化
        for (int j = maxWeight; j >= weight[0]; j--) {
            dp[0][j] = dp[0][j - weight[0] + value[0]];
        }
        // 这一个场景下，先遍历容量，在遍历背包也是可以的
        // 遍历物品
        for (int i = 1; i < weight.length; i++) {
            // 遍历容量
            for (int j = 0; j < maxWeight; j++) {
                // 当物品重量大于背包重量时，取前一个物品的重量
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
    }


    public void bagProblems2() {
        // 物品重量
        int[] weight = {1, 3, 4};
        // 物品价值
        int[] value = {15, 20, 30};
        // 背包最大重量
        int maxWeight = 4;

        int[] dp = new int[weight.length + 1];

        dp[0] = 0;

        for (int i = 0; i < weight.length; i++) {
            // 这里倒序遍历是为了防止东西被放进去两次 【打个草稿就知道了】
            for (int j = maxWeight; j >= weight[i]; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i] + value[i]]);
            }
        }
    }

    /**
     * #416
     * @param nums
     * 01背包问题
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int[] dp = new int[10001];
        // 先算出总和
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 取余
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;

        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }


    /**
     * #1049
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        int[] dp = new int[1501];
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int target = sum /2;
        dp[0] = 0;

        // 遍历物品
        for (int i = 0; i < stones.length; i++) {
            // 遍历背包
            for (int j = 1500; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - dp[target] - dp[target];
    }


    /**
     * #494
     * @param nums
     * @param target
     * @return
     * 有i中方法，使得累加和为dp[i]
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int x = (sum + target) / 2;
        if ((sum + target) % 2 != 0) {
            return 0;
        }

        if(x < 0){
            x = -x;
        }
        int[] dp = new int[x + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = x; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[x];

    }


    /**
     * #474
     * @param strs
     * @param m
     * @param n
     * @return
     * 01背包问题，有i个0 j个1的最大子集数为dp[i][j]
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs.length == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int oneNum = 0;
            int zeroNum = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }

            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }


    /**
     * #518
     * @param amount
     * @param coins
     * @return
     * dp数组的意义 求总和为i的组合种数有dp[i]种
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // 遍历物品
        for (int t = 0; t < coins.length; t++) {
            // 遍历背包
            for (int i = coins[t]; i <= amount; i++) {
                dp[i] += dp[i - coins[t]];
            }
        }
        return dp[amount];
    }

    /**
     * #377
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        // 求排列，先遍历背包
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                    System.out.println("i="+i+" j="+j+" dp[i]="+dp[i]);
                }
            }
        }
        return dp[target];
    }


    /**
     * #70
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // i--->第i层，dp[i]--->走到第i层有几种方法
        dp[0] = 1;

        // 这是完全背包问题，求排列
        // 所以先遍历背包（目标层数）
        for (int i = 0; i <= n; i++) {
            // 再遍历物品(每一层可以选择走几层)
            for (int j = 1; j <= 2; j++) {
                if (i >= j) {
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[n];
    }


    /**
     * #279
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // 和为i的最少数量为dp[i]
        for (int j = 0; j <= n; j++) {
            dp[j] = Integer.MAX_VALUE;
            log.info("dp[{}]={}",j,dp[j]);
        }

        dp[0] = 0;
        // 这里的物品实际上就是{1*1, 2*2,3*3,......n*n}
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
                log.info("i={} j={} dp[i]={}", i, j, dp[i]);
            }
        }
        return dp[n];
    }


    /**
     * #139
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        log.info(wordDict.toString());
        // i--->第i个单词    dp[i] 从1-i能否拼成s
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        // 目标字符串--背包  字典--物品
        // 先遍历背包
        for (int i = 0; i <= s.length(); i++) {
            // 再遍历字典
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    log.info("j = {} i = {} dp[{}] = {}",j,i,i,dp[i]);
                }
            }
        }
        return dp[s.length()];
    }


    /**
     * 进阶：多重背包 有N种物品，需要装满V的背包，第N个物品有In的个数，请问哪种方式装的最多？
     *
     * @param
     *
     */


    /**
     * #198
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        // 从0--i的房间里，偷得最多的金额
        int[] dp = new int[nums.length + 1];

        if (nums.length < 2) {
            return nums[0];
        }

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }


    /**
     * #213
     * @param nums
     */
    public int rob213(int[] nums) {
        int result = this.robRange(nums, 0, nums.length - 2);
        int result2 = this.robRange(nums, 1, nums.length - 1);
        return Math.max(result, result2);
    }

    private int robRange(int[] nums, int begin, int end) {
        int[] dp = new int[nums.length + 1];
        if (begin == end) {
            return nums[begin];
        }

        if (nums.length  == 0) {
            return 0;
        }

        if (nums.length  == 1) {
            return nums[0];
        }

        dp[begin] = nums[begin];
        dp[begin + 1] = Math.max(nums[begin], nums[begin + 1]);

        for (int i = begin + 2 ; i <= end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[end];
    }

    /**
     * #357
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        // dp[n] -->> 10的n次方，有dp[n]个各个位均不同的数字
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= n; i++) {
            /**
             * dp[i-1] --> 比如 n = 3 时 , 0 ~ 999 可以分开看成0 ~ 99 + 100 ~ 999 --> 0~99 正是dp[i-1]
             * (dp[i - 1] - dp[i - 2]) --> 这里找100 ~ 999 的不重复，那么是不是只能由 10 ~ 99 的不重复数去组成 比如 12 组成 120 123 124 125 126 127 128 129 。而10 ~ 99的不重复数--> dp[2] - dp[1]
             * (10 - (i - 1))  --> 若n=3 （3位数）要组成不同的数字，那么只能从 12x  13x 来组成 那么可选的数字就会越来越少，当n=2时，1 后面只能跟0 2 3...9 当n=3时，12x只能从 0 3 4 5...9 越来越少，所以10-(i-1)
             */
            dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2]) * (10 - (i - 1));
        }
        return dp[n];
    }


    /**
     * #53
     * @param nums
     * @deprecated 贪心也可解此题
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // dp[i] -> 到i的最大子序和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }


    /**
     * #64
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        // no1 到i j  最短的长度为dp[i][j]
        int high = grid[0].length;
        int weith = grid.length;

        // 初始化数据
        // 高度
        for (int i = 1; i < high; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        // 宽度
        for (int i = 1; i < weith; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        for (int i = 1 ;i < weith; i++) {
            for (int k = 1; k < high; k++) {
                grid[i][k] = Math.min(grid[i - 1][k], grid[i][k - 1]);
            }
        }
        return grid[weith - 1][high - 1];
    }


    /**
     * #647
     * 双指针法
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int res = 0;
        // 表示 i-j区间 是否为回文串
        boolean[][] ans = new boolean[s.length()][s.length()];

        for (int i = s.length(); i >=0 ; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 1 双指针同时指向一个字符，必是回文
                    // 2 双指针相邻
                    if (j - i <= 1) {
                        res++;
                        ans[i][j] = true;
                        // 3 双指针距离超过1时，就看中间的字符串是不是回文即可
                    } else if (ans[i + 1][j - 1]) {
                        res++;
                        ans[i][j] = true;
                    }
                }
            }
        }
        return res;
    }


    /**
     * #132 ****
     * @param s
     * @return
     * 如果从分割字符串的角度考虑这个问题的话，对于一个区间内的字符串来说，每一个位置都将是可能的分割点，
     * 可以用暴力递归的方式找出答案，但是时间复杂度太高，加上预处理回文数组能勉强通过。
     * 换个角度想想，当切割次数最少使得切割后的所有字符串都是回文时，也正是这些回文子串最长的时候，那么如果说能找到以每个字符为中心的最长回文串，实际上就已经找到了答案
     */
    public int minCut(String s) {
        if (s.length() == 1) {
            return 0;
        }
        int res = 0;
        // i -> 下标 dp[i] -> 以i下标对应的字符为中心的最长回文串
        int[] dp = new int[s.length()];
        Arrays.fill(dp, s.length() - 1);

        for (int i = 0; i < s.length(); i++) {
            this.doCut(s,i,i,dp);
            this.doCut(s,i,i+1,dp);
        }
        return dp[s.length() - 1];
    }

    private void doCut(String s, int i, int j, int[] dp) {
        //以每个字符为中心的最长回文串
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            dp[j] = Math.min(dp[j], (i == 0 ? -1 : dp[i - 1]) + 1);
            i++;
            j--;
        }
    }


    /**
     * #1480
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0] + nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        return dp;
    }


    /**
     * #724
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int sumLeft = 0;
        int sumRight = 0;
        int sum = 0;
        // 先求总和
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            sumLeft += nums[i];
            sumRight = sum - sumLeft + nums[i];
            if (sumLeft == sumRight) {
                return i;
            }
        }

        return -1;
    }





    public static void main(String[] args) {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();
        int[] dp = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] test = { 1, 7, 3, 6, 5, 6};

        //List<String> list = new ArrayList<>();
        // list.add("leet");
        //list.add("Code");
        //log.info(list.toString());

        //boolean b = dynamicProgramming.wordBreak("leetCode", list);
        // System.out.println(b);;
        System.out.println(dynamicProgramming.pivotIndex(test));



    }
}
