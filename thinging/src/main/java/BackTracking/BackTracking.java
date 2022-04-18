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
}
