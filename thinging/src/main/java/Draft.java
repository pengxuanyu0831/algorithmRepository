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


    public static void main(String[] args) {
        Draft draft = new Draft();
        int[] ints = new int[]{1,1};
        List<Integer> numbers = draft.findDisappearedNumbers(ints);
        System.out.println(numbers);
    }




}
