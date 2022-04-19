package BackTracking;

import java.util.ArrayList;
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

        for (int i = index; i < candidates.length; i++) {
            sum += candidates[i];
            path.add(candidates[i]);
            this.combinationSumBackTracking(target, candidates,sum,i);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }
}
