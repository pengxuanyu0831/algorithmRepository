package Sort;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/05/22 23:45
 */
@Slf4j
public class sort {
    /**
     * #242
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] record = new int[26];
        // 初始化
        for (int k = 0; k < record.length; k++) {
            record[k] = 0;
        }
        for (int j = 0; j < s.length(); j++) {
            record[s.charAt(j) - 'a'] += 1;
        }

        for (int l = 0; l < t.length(); l++) {
            record[t.charAt(l) - 'a'] -= 1;
        }

        for (int m = 0; m < record.length; m++) {
            if (record[m] != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * #347
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }

        }
        // 按value排序
        int[] result = new int[k];
        // 但是最后取得是key
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ins = new int[]{1,8,6,2,5,4,8,3,7};
        sort sort = new sort();
        int[] ints = sort.topKFrequent(ins, 2);
        log.info("{}",ints);

    }

}
