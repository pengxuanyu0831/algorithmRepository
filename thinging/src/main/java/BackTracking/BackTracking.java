package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
