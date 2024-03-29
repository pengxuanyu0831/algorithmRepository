package Arraysss;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/05/30 23:05
 */
@Slf4j
public class Arraysss {
    /**
     * #704
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            // 中间值大于目标值 -----> 目标值落在了左半区间
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * #27
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow;
        for (slow = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }


    /**
     * #26 快慢指针 返回慢指针的索引位置+1即是长度
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int fast = 1;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
                fast++;
            } else {
                fast++;
            }
        }
        return slow + 1;
    }


    /**
     * #283
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                fast++;
                slow++;
            } else {
                fast++;
            }
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


    /**
     * #844
     * @param s
     * @param t
     * @return
     * 画图 画图 画图
     */
    public boolean backspaceCompare(String s, String t) {
        StringBuilder ssb = new StringBuilder();
        StringBuilder sst = new StringBuilder();
        for (char ss : s.toCharArray()) {
            if (ss != '#') {
                ssb.append(ss);
            } else if (ssb.length() > 0) {
                ssb.deleteCharAt(ssb.length() - 1);
            }
        }

        for (char tt : t.toCharArray()) {
            if (tt != '#') {
                sst.append(tt);
            } else if (sst.length() > 0) {
                sst.deleteCharAt(sst.length() - 1);
            }
        }
        return sst.toString().equals(ssb.toString());
    }


    /**
     * #977
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] copy = new int[nums.length];
        int k = nums.length-1;
        while (left <= right) {
            if (nums[right] * nums[right] > nums[left] * nums[left]) {
                copy[k--] = nums[right] * nums[right];
                right--;
            } else {
                copy[k--] = nums[left] * nums[left];
                left++;
            }
        }
        return copy;
    }

    /**
     * #209
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int start = 0;
        int end;
        int sum = 0;
        // 右端点一直在动
        for (end = 0; end < nums.length; end++) {
            sum += nums[end];
            // 当总和大于或等于目标值时
            while (sum >= target) {
                result = Math.min(result, end - start + 1);
                sum -= nums[start];
                // 移动左端点
                start++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }


    /**
     * #904
     * @param fruits
     * @return
     * 滑动窗口
     */
    public int totalFruit(int[] fruits) {
        int left = 0;
        int i = 0;
        int basket1 = fruits[0];
        int basket2 = fruits[1];
        int result = 0;
        for (i = 0; i < fruits.length; i++) {
            if (fruits[i] == basket1 || fruits[i] == basket2) {
                result = Math.max(result, i - left + 1);
            } else {
                left = i - 1;
                basket1 = fruits[left];
                // left 要把前面的都清零
                while (left >= 1 && fruits[left] == basket1) {
                    left--;
                }
                basket2 = fruits[i];
                result = Math.max(result, i - left + 1);
            }
        }
        return result;
    }
    //
    public int totalFruitTwo(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0;
        int r = 0;
        int result = Integer.MIN_VALUE;
        for (r = 0; r < fruits.length; r++) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            while (map.size() > 2) {
                Integer integer = map.get(fruits[l]);
                integer--;
                if (integer == 0) {
                    map.remove(fruits[l]);
                }
                l++;
            }
            result = Math.max(result, r - l + 1);
        }
        return result;
    }


    /**
     * #76
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character,Integer> window = new HashMap();  // 用来记录窗口中的字符和数量
        Map<Character,Integer> need = new HashMap();  // 需要凑齐的字符和数量
        // 构建need字符集
        for (int i = 0; i < t.length(); i++) {
            char needChar = t.charAt(i);
            need.put(needChar, need.getOrDefault(needChar, 0) + 1);
        }

        int left = 0, right = 0, valid = 0;
        // valid是用来记录窗口中满足need要求的字符和数量的数目，比如need中要求字符a数量为2，如果window中的a字符的数量等于了2，valid就＋1，反之-1
        int len = Integer.MAX_VALUE;  // 记录最小字串的长度
        int start = 0;  // 记录最小字串的起始位置
        while(right < s.length()){
            char addChar = s.charAt(right);  // 即将要加入window的字符
            window.put(addChar,window.getOrDefault(addChar,0) + 1);
            right++;
            // 如果加入的字符是need中要求的字符，并且数量已经达到了need要求的数量，则valid+1
            // 这里和下面都有个坑，window.get(addChar)和need.get(addChar)返回的都是对象，最好用.equals()方法比较大小
            if(need.containsKey(addChar) && window.get(addChar).equals(need.get(addChar))){
                valid++;
            }
            // 当window中记录的字符和数量满足了need中要求的字符和数量，考虑缩窗口
            while(valid == need.size()){
                // 先判断当前的最小覆盖字串是否比之前的最小覆盖字串短
                if(right - left < len){  // 注意，这里上面已经对right实施了++操作，所以这里的长度不是right - left + 1
                    len = right - left ;
                    start = left;  // 如果最短，则记录下该最小覆盖字串的起始位置
                }
                char removeChar = s.charAt(left);
                // 开始缩减窗口，left右移，如果要从window删除的字符正好是need中需要的并且，数目也等于need中需要的数目，则删减后，该该字符要求的数量
                // 显然不满足need要求的数量，所以valid要-1；
                if(need.containsKey(removeChar) && window.get(removeChar).equals(need.get(removeChar))){
                    valid--;
                }
                window.put(removeChar,window.get(removeChar) - 1);
                left++;
            }
        }
        // 如果最小覆盖字串的长度相对于定义时没变，则t不包含s中所有的字符，返回"",如果长度改变过，说明存在这样的最小覆盖字串，直接输出。
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }


    /**
     * #59
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int res[][] = new int[n][n];
        int loop = n / 2;
        int startX = 0;
        int startY = 0;

        // 偏移量
        int offset = 1;
        // 填充的数字
        int num = 1;
        while (loop > 0) {
            int i = startX;
            int j = startY;
            // 每一次循环的结束都让出一位给下个循环
            // 上横
            for (; j < startY + n - offset; ++j) {
                res[startY][j] = num++;
            }
            // 右竖
            for (; i < startX + n - offset; ++i) {
                res[i][j] = num++;
            }
            // 下横
            for (; j > startY ; j--) {
                res[i][j] = num++;
            }

            // 左竖
            for (; i > startX; i--) {
                res[i][j] = num++;
            }

            loop--;
            startX += 1;
            startY += 1;
            // 这里偏移量需要画图理解
            offset += 2;

        }
        if (n % 2 == 1) {
            res[n / 2][n / 2] = num;
        }
        return res;
    }


    /**
     * #35
     * @param nums
     * @param target
     * @return
     * 二分查找的循环中，坚持循环不变量的原则
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        // 这里决定了target在什么区间中，right = nums.legth --> target在左闭右开区间
        //                            right = nums.length - 1 -->target在左闭右区间
        int right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) / 2);
            // 目标值在右半区间
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                return mid;
            }
        }
        return right;
    }


    /**
     * #54
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int loop = Math.min(matrix.length, matrix[0].length) / 2;
        int startX = 0;
        int startY = 0;

        // 偏移量
        int offset = 1;
        while (loop > 0) {
            int i = startX;
            int j = startY;
            // 上横
            for (; j < startY + matrix[0].length - offset; ++j) {
                result.add(matrix[startY][j]);
            }
            for (; i < startX + matrix.length - offset; ++i) {
                result.add(matrix[i][j]);
            }

            for (; j > startY ; j--) {
                result.add(matrix[i][j]);
            }

            for (; i > startX; i--) {
                result.add(matrix[i][j]);
            }

            loop--;
            startX += 1;
            startY += 1;
            // 这里偏移量需要画图理解
            offset += 2;
        }
        if (matrix[0].length == matrix.length && matrix[0].length % 2 == 1) {
            result.add(matrix[matrix.length / 2][matrix[0].length / 2]);
        }
        int n = matrix.length;
        int m = matrix[0].length;
        //针对列大于行且，行不是偶数的时候；
        if ((m > n && n % 2 != 0)) {
            for (int i = n / 2; i < m - n / 2; i++) {
                result.add(matrix[n / 2][i]);
            }
        }
        //针对行大于列且，列不是偶数的时候；
        if (m < n && m % 2 != 0) {
            for (int i = m / 2; i < n - m / 2; i++) {
                result.add(matrix[i][m / 2]);
            }
        }
        return result;
    }


    /**
     *  #34
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right;
        int index = 0;
        if (nums.length == 1 && nums[0] == target) {
            return new int[]{0, 0};
        }
        List<Integer> result = new ArrayList<>();
        for (right = 0; right < nums.length; right++) {
            if (nums[right] == target) {
                left = right;
                log.info("left = {}",left);
                result.add(left);
                index += 1;
            }
        }
        if (index != 0) {
            return new int[]{result.get(0), result.get(result.size() - 1)};
        }
        return new int[]{-1, -1};
    }


    /**
     * #409
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int[] ints = new int[128];
        for (char c : s.toCharArray()) {
            ints[c]++;
        }
        int result = 0;
        for (int i : ints) {
            // 偶数直接+1
            result += i / 2 * 2;
            if(i % 2 == 1 && result %2==0){
                result += 1;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] ints = new int[]{1,3};
        Arraysss arraysss = new Arraysss();
        arraysss.searchRange(ints, 1);
        System.out.println();
    }

}
