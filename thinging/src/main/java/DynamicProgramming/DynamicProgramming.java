package DynamicProgramming;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/01/31 15:25
 */
public class DynamicProgramming {
    // # 第0个动态规划的题目 ----实现斐波那契数列
    public int fibonacci(int number) {
        if (number <= 1) {
            return number;
        }
        int[] dp = {0,1};
        for (int i = 2; i <= number; i++) {
            int sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }
        return dp[1];
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


    public static void main(String[] args) {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();

        int[] a = {1, 1, 1, 100, 3, 9, 10, 1, 2, 3};
        System.out.println(dynamicProgramming.climb(a));
        System.out.println(dynamicProgramming.climb(a));
    }
}
