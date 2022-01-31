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
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }


    public static void main(String[] args) {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();

        System.out.println(dynamicProgramming.steps(5));
    }
}
