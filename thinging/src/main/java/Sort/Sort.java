package Sort;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/05/22 23:45
 */
@Slf4j
public class Sort {
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
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // 按value排序
        int[] result = new int[k];
        // 但是最后取得是key
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));

        for (Integer j :  map.keySet()) {
            if (queue.size() < k) {
                queue.offer(j);
            } else if (map.get(j) > map.get(queue.peek())) {
                queue.poll();
                queue.offer(j);
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll();
        }
        return result;
    }

    /**
     * 寻找前k小的数--->使用大顶堆 思路：遍历数组，用一个大小为k的大顶堆保存当前找到的前k个最小数，如果下一个数组元素比堆顶小，那堆顶元素必然不是前k小，将堆顶元素出堆，此数组元素入堆
     * 大顶堆：每个结点的值都大于或等于其左右孩子结点的值
     * 寻找前k大的数--->使用小顶堆 思路：遍历数组，用一个大小为k的小顶堆保存当前找到的前k个最大数，如果下一个数组元素比堆顶大，那堆顶元素必然不是前k大，将堆顶元素出堆，此数组元素入堆
     * 小顶堆：每个结点的值都小于或等于其左右孩子结点的值
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKMin(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            // >0 从大到小 排列   <0 从小到大排列
            @Override
            public int compare(Integer o1, Integer o2) {
                // 大顶堆---o2-o1
                // 小顶堆 o1-o2
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            // 不足k个是，直接推入
            if (pq.size() < k) {
                pq.add(nums[i]);
                // 小于堆顶是，说明堆顶不是最小，弹出堆顶，推入nums[i]
            } else if (nums[i] < pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        return result;
    }


    /**
     * #215
     * @param nums
     * @param k
     * @return
     * 秒杀写法  findKthLargest2：正经写法
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 大顶堆---o2-o1 小顶堆 o1-o2
                return o2 - o1;
            }
        });
        for (int i : nums) {
            if (pq.size() < k) {
                pq.offer(i);
            } else if (i > pq.peek()) {
                pq.poll();
                pq.offer(i);
            }
        }

        return pq.poll();
    }


    /**
     * #295
     * 这题得画图，把两个栈画出来就懂了
     */
    static class MedianFinder {
        PriorityQueue<Integer> max;
        PriorityQueue<Integer> min;
        public MedianFinder() {
            max = new PriorityQueue<Integer>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            min = new PriorityQueue<Integer>();
        }

        public void addNum(int num) {
            // 这里只能加给max，否则如果这个num是最小的，会被min。remove出来
            max.add(num);
            min.add(max.remove());
            if (min.size() > max.size()) {
                max.add(min.remove());
            }
        }

        public double findMedian() {
            if (max.size() == min.size()) {
                return (max.peek() + min.peek()) / 2.0;
            } else {
                return max.peek();
            }
        }



    }


    /**
     * #658
     * @param arr
     * @param k
     * @param x
     * @return
     * 滑动窗口
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - 1;
        while (hi - lo >= k) {
            if (x - arr[lo] > arr[hi] - x) {
                lo++;
            }else {
                hi--;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (; lo <= hi ; lo++) {
            result.add(arr[lo]);
        }
        return result;
    }


    /**
     * #692
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        // 前K个要用
        PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));
        for (String s : map.keySet()) {
            if (queue.size() < k) {
                queue.offer(s);
            }else if (map.get(queue.peek()) < map.get(s)) {
                queue.poll();
                queue.offer(s);
            }

        }
        List<String> result = new ArrayList<>();
        for (String s : queue) {
            result.add(s);
        }
        return result;
    }






    public static void main(String[] args) {
        int[] ins = new int[]{1,8,6,2,5,4,8,3,7};
        int[] arr = new int[]{1,2,3,4,5};
        int[] arr1 = new int[]{1,1,1,10,10,10};
        Sort sort = new Sort();
        // int[] ints = sort.topKFrequent(ins, 2);
        // log.info("{}",ints);
        List<Integer> closestElements = sort.findClosestElements(arr1, 1, 9);
        log.info("closestElements {}",closestElements);

    }

}
