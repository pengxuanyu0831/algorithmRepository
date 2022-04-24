package BackTracking;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program algorithm
 * @description: 回溯算法
 * @author: pengxuanyu
 * @create: 2022/04/18 22:56
 */
public class BackTracking {

    /**
     * #77
     *
     * @param n
     * @param k
     * @return
     */
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();





    public List<List<Integer>> combine(int n, int k) {
        this.combineBackTracking(n, k, 1);
        return result;
    }

    private void combineBackTracking(int n, int k, int index) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i <= n; i++) {
            path.add(i);
            this.combineBackTracking(n, k, i + 1);
            // 回溯
            path.remove(path.size() - 1);
        }
    }


    /**
     * #216
     * @param k- k个数相加 = n
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.combinationSumBackTracking(k, n, 1);
        return result;
    }

    private void combinationSumBackTracking(int k, int n, int index) {
        int sum = 0;
        // 找到一组后
        if (path.size() == k) {
            System.out.println(path);
            for (Integer integer : path) {
                sum += integer;
            }
            System.out.println("sum="+sum);
            if (sum == n) {
                System.out.println("n=" + n + "path=" + path);
                result.add(new ArrayList<>(path));
                System.out.println("result=" + result);
                return;
            } else {
                result.clear();
            }
        }
        for (int i = index; i <= 9; i++) {
            path.add(i);
            this.combinationSumBackTracking(k, n, i + 1);
            path.remove(path.size() - 1);
        }
    }


    /**
     * #39
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.combinationSumBackTracking(target, candidates, 0, 0);
        return result;
    }

    private void combinationSumBackTracking(int target, int[] candidates,int sum,int index) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length && sum + candidates[i] <= target; i++) {
            sum += candidates[i];
            path.add(candidates[i]);
            // 这里没有传i+1，就代表下次进来可以再次使用自己（重复使用）
            Arrays.sort(candidates);
            this.combinationSumBackTracking(target, candidates,sum,i);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }


    /**
     * #40
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.combinationSum2BackTracking(target, candidates, 0, 0);
        return result;

    }

    private void combinationSum2BackTracking(int target, int[] candidates,int sum,int index){
        if (sum > target) {
            return;
        }

        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length && sum + candidates[i] <= target; i++) {
            // 同一树层使用过的元素，不允许再使用了 看pdf的图就懂了
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            sum += candidates[i];
            path.add(candidates[i]);

            // 本题不允许重复使用元素
            this.combinationSum2BackTracking(target, candidates,sum,i + 1);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }


    /**
     * #131
     */
    List<String> pathString = new ArrayList<>();
    List<List<String>> resultString = new ArrayList<>();
    public List<List<String>> partition(String s) {
        this.doPartition(s, 0);
        return resultString;
    }

    public void doPartition(String s, int index) {
        if (index >= s.length()) {
            resultString.add(new ArrayList<>(pathString));
            return;
        }
        char[] chars = s.toCharArray();
        for (int i = index; i < chars.length; i++) {
            if (isBack(chars, index, i)) {
                pathString.add(s.substring(index, i+ 1));
            } else {
                continue;
            }
            doPartition(s, i + 1);
            pathString.remove(pathString.size() - 1);
        }
    }

    boolean isBack(char[] chars, int index, int end) {
        for (int i = index, j = end; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }


    /**
     * #93
     *
     * @param s
     * @return
     */
    List<String> resultStr = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        this.doRestoreIpAddresses(s, 0, 0);
        return resultStr;
    }

    public void doRestoreIpAddresses(String s, int index,int pointNum) {
        // 终止条件
        if (pointNum == 3) {
            if (this.isIpAddress(s, index, s.length() - 1)) {
                resultStr.add(s);
            }
            return;
        }
        for (int i = index; i < s.length(); i++) {
            // index -> i，判断[index,i]这个区间的ip是否合法
            if (this.isIpAddress(s, index, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                pointNum++;
                doRestoreIpAddresses(s, i + 2, pointNum);
                // 回溯
                pointNum--;
                s = s.substring(0, i + 1) + s.substring(i + 2);
            } else {
                break;
            }
        }

    }
    // 判断是否为合法的ip
    private boolean isIpAddress(String s, int index, int end) {
        if (index > end) {
            return false;
        }
        // 如果开头为0，非法
        if (s.charAt(index) == '0' && index != end) {
            return false;
        }
        int num = 0;
        for (int i = index; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 遇到⾮数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) { // 如果⼤于255了不合法
                return false;
            }
        }
        return true;
    }


    /**
     * #78
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        this.doSubSets(nums, 0);
        return result;
    }

    public void doSubSets(int[] nums, int index) {
        result.add(new ArrayList<>(path));
        // 如果起始下标大于等于传入的数组长度，则视为完成一次遍历（一棵树走到底了）
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            // 不允许复用自己，不允许重复
            this.doSubSets(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }


    /**
     * #491
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        this.doFindSubsequences(nums, 0);
        return result;
    }

    public void doFindSubsequences(int[] nums, int index) {
        // 递增子序列长度至少为2 ，且不要return，因为要取所有节点的数据
        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
        }
        int[] used = new int[201];
        for (int i = index; i < nums.length; i++) {
            if (used[nums[i] + 100] == 1 || path.size() > 0 && path.get(path.size() - 1) > nums[i]) {
                continue;
            }

            path.add(nums[i]);
            // 记录一下用过的数字
            used[nums[i] + 100] = 1;
            this.doFindSubsequences(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }


    /**
     * #46
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        this.doPermute(nums, used);
        return result;
    }

    public void doPermute(int[] nums, boolean[] used) {
        // 终止条件肯定没错
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            this.doPermute(nums, used);
            // 所有地方都要回溯到
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }


    /**
     * #47
     * @param
     * @desc 47题取巧方式:直接返回时用流去重
     *         下面是非取巧的方法
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        nums = Arrays.stream(nums).sorted().toArray();
        boolean[] used = new boolean[nums.length];
        this.doPermuteDict(nums, used);
        return result;
    }

    // 非取巧方法
    public void doPermuteDict(int[] nums, boolean[] used) {
        // 终止条件肯定没错
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // 去重
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            this.doPermuteDict(nums, used);
            // 所有地方都要回溯到
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }


    /**
     * #90
     *
     * @param nums
     * @return
     */
    Set<Integer> used ;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        nums = Arrays.stream(nums).sorted().toArray();
        this.doSubSetWithoutDup(nums,0, used);
        return result;
    }

    public void doSubSetWithoutDup(int[] nums,int index, Set<Integer> used) {
        result.add(new ArrayList<>(path));
        // 每次进来新建一个set是为了控制某一节点下的同一层，如果是全局变量，就变成了控制整棵树不允许重复
        used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
            used.add(nums[i]);
            path.add(nums[i]);
            this.doSubSetWithoutDup(nums, i + 1, used);
            path.remove(path.size() - 1);
        }
    }


    public static void main(String[] args) {
        int[] ints = new int[]{4,4,4,1,4};
        BackTracking backTracking = new BackTracking();
        System.out.println(backTracking.subsetsWithDup(ints));
    }


}
