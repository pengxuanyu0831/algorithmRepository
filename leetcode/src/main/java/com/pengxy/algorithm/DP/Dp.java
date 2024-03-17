package com.pengxy.algorithm.DP;

/**
 * @Author xuanyu peng
 * @Package com.pengxy.algorithm.DP
 * @Date 2024/3/17 0017 20:59
 * @Description
 * @Github https://github.com/pengxuanyu0831
 */
public class Dp {
    /**
     * #121
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 2 && prices[1] > prices[0]) {
            return prices[1] - prices[0];
        }
        int buy = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 1; i < prices.length; i++) {
            buy = Math.min(buy, prices[i]);
            result = Math.max(result, prices[i]-buy);
        }
        return result;
    }

}
