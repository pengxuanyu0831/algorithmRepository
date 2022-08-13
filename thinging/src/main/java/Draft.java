import java.util.*;

/**
 * @program algorithm
 * @description: 草稿类
 * @author: pengxuanyu
 * @create: 2022/04/26 21:57
 */
public class Draft {
    List<String> path;
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> resolveNQueen(int n) {
        // 1 先创建一个棋盘，构建一个n x n的棋盘
        char[][] chars = new char[n][n];
        // 2 初始化棋盘，先把所有可能的位置处理好
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chars[i][j] = '.';
            }
        }
        this.doResolveNQueen(chars, 0);
        return result;
    }

    void doResolveNQueen(char[][] chars, int index) {
        // 3 终止条件，当遍历到最后一行时，把Q可能的位置放入结果集
        if (index == chars.length) {
            // 3.5 每次存入结果集时需要生成一个新的【棋盘】
            path = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < chars.length; j++) {
                    sb.append(chars[i][j]);
                }
                path.add(sb.toString());
            }
            result.add(new ArrayList<>(path));
        }

        // 4 开始回溯 (这里其实是遍历行)
        for (int k = index; k < chars.length; k++) {
            if (this.isValid(k, index, chars)) {
                chars[k][index] = 'Q';
                this.doResolveNQueen(chars, k + 1);
                // 回溯
                chars[k][index] = '.';
            }
        }

    }


    boolean isValid(int x,int y,char[][] chars) {
        // 5 上面有遍历行了，这里遍历行下标所对应的列即可
        for (int i = 0; i <= y; i++) {
            if (chars[i][y] == 'Q') {
                return false;
            }
        }

        // 6 判断右上方是否有Q 行++ 列--
        for (int i = x + 1, j = y - 1; i <= chars.length && j >= 0; i++, j--) {
            if (chars[i][j] == 'Q') {
                return false;
            }
        }

        // 7 判断左上方是否有Q 行-- 列--
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (chars[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }


    public List<Integer> findDisappearedNumbers(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(i+1);
        }

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            }
        }
        return new ArrayList<>(set);
/*
        for (int i = 0; i < nums.length; i++) {

            // Math.abs() -> 求绝对值  将下标置为负数，则正数的下标就是确实的位置
            if (nums[Math.abs(nums[i] ) - 1] > 0) {
                nums[Math.abs(nums[i] ) - 1] = - nums[Math.abs(nums[i] ) - 1];
            }
        }
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;*/
    }


    /**
     * #179
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        String[] chars = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            chars[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(chars, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < chars.length; k++) {
            sb.append(chars[k]);
        }
        if (sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }


    /**
     * #1413
     * @param nums
     * @return
     */
    public int minStartValue(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0] + nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        Arrays.sort(dp);
        return dp[0] > 0 ? 1 : 0 - dp[0] + 1;
    }


    public void bagDraft(int[] thingsValues,int[] thingsWeight,int maxWeight) {
        // step1: 确定数组的意义,从0-i下标取物品，放到容量为j的包里，最大放置重量为dp[i][j]
        int[][] dp = new int[thingsWeight.length + 1][maxWeight + 1];
        // 初始化数据
        for (int i = 0; i <= thingsWeight.length; i++) {
            dp[i][0] = thingsValues[0];
        }
        // 先遍历物品
        for (int i = 1; i <= thingsWeight.length; i++) {
            // 再遍历背包
            for (int j = 1; j <= maxWeight; j++) {
                // 当背包容量小于当前遍历的物品时，最大容量是截止到前一个物品的总容量
                if (j < thingsWeight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 这里是当前背包容量j >=当前物品，但是当前物品不一定会放进背包里，因为有可能背包此时已经有的物品总和价值大于当前物品
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - thingsWeight[i]] + thingsValues[i]);
                }
            }
        }
    }


    public static void main(String[] args) {
        Draft draft = new Draft();
        int[] ints = new int[]{1,1};
        int[] intttt = new int[]{-3,2,-3,4,2};
        List<Integer> numbers = draft.findDisappearedNumbers(ints);
        int i = draft.minStartValue(intttt);
        System.out.println(i);
    }




}
