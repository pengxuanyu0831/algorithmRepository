package Greedy;

import java.util.Arrays;

/**
 * @program algorithm
 * @description: 贪心算法
 * @author: pengxuanyu
 * @create: 2022/04/26 22:24
 */
public class Greedy {


    /**
     * #455
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


    public static void main(String[] args) {
        Greedy g = new Greedy();
        int[] ins = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int i = g.wiggleMaxLength(ins);
        System.out.println(g.maxSubArray(ins));
    }
}
