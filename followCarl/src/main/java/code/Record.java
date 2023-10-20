package code;

import com.pengxy.algorithm.AboutList.ListNode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/10/11 23:39
 */
@Slf4j
public class Record {


    /**
     * Day 1
     * #704
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        while (left <= right) {
            int idx = (left + right) / 2 ;

            if (target < nums[idx]) {
                right = idx -1  ;
            } else if (target > nums[idx]) {
                left = idx + 1;
            } else {
                return idx;
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
        int left = 0;
        int right = 0;

        while (right < nums.length) {

            if (nums[right] != val) {

                nums[left] = nums[right];

                left++;
            }
            right++;
        }
        return left;
    }


    /**
     * Day2 #977
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] copy = new int[nums.length];
        int k = nums.length-1;
        // 精髓：原式自乘之后，最大的数字一定在两边，然后两头比较，更大的放在新数组的右边
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
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = nums.length + 1;
        while (right < nums.length) {
            sum += nums[right];
            right++;
            while (sum >= target) {
                res = right - left > res ? res : right - left;
                // 滑动窗口的精髓：左边动的时候，要减掉左边的原值
                sum -= nums[left];
                left++;
            }
        }
        return res > nums.length ? 0 : res;
    }


    /**
     * Day 3 #203
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode();
        // 保存头结点
        node.next = head;
        ListNode cur = node;

        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return node.next;
    }


    /**
     * #206
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head){
        if (head == null) {
            return head;
        }
        ListNode beg = null;
        ListNode mid = head;
        ListNode end = head.next;

        while (true) {
            mid.next = beg;
            if (end == null) {
                break;
            }

            beg = mid;
            mid = end;
            end = end.next;
        }

        head = mid;
        return head;
    }


    /**
     * Day4 #24
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 利用栈
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode node = new ListNode();
        ListNode temp = head;
        // 先保存头结点
        head = node;

        while (temp != null && temp.next !=null) {
            stack.add(temp);
            stack.add(temp.next);

            // 当前节点往前走两步，走到第二组要交换的节点
            temp = temp.next.next;

            node.next = stack.pop();
            node = node.next;
            node.next = stack.pop();
            node = node.next;

        }

        if (temp != null) {
            node.next = temp;
        } else {
            node.next = null;
        }

        return head.next;
    }


    /**
     * Day5 #242
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (char c : s.toCharArray()) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }

        for (char e : t.toCharArray()) {
            tMap.put(e, tMap.getOrDefault(e, 0));
        }

        return Objects.equals(sMap, tMap);
    }


    /**
     * #349
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> res = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            res.add(nums1[i]);
        }

        for (int k = 0; k < nums2.length; k++) {
            res.add(nums2[k]);
        }

        int[] resss = new int[res.size()];
        int sis = 0;
        for (Integer i : res) {
            resss[sis++] = i;
        }

        return resss;
    }


    /**Day7  #15
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i>0&& nums[i] == nums[i - 1]) {
                continue;
            }

            // 双指针在内循环
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 题目条件
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 里面要移动指针
                    // 什么情况要动，就是相等的时候要动
                    while (j < k && nums[j] == nums[j + 1]) {

                        j++;
                    }

                    while (j < k && nums[k] == nums[k - 1]) {

                        k--;
                    }
                    // 正常移动
                    j++;
                    k--;
                }
                // 从小到大排序，如果和>0意味着右边太大，k 要往左
                if (sum > 0) {
                    k--;
                }

                if (sum < 0) {
                    j++;
                }
            }


        }
        return res;
    }


    /**
     * Day8  #344
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp;

            temp = s[left];

            s[left] = s[right];

            s[right] = temp;

            left++;
            right--;
        }
    }


    /**
     * #541
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] ss = s.toCharArray();

        for (int i = 0; i < s.length(); i = i + 2 * k) {
            // 右指针
            int rr = i + k - 1;
            this.doReverseStr(ss, i, Math.min(rr, ss.length - 1));
        }
        return String.valueOf(ss);

    }

    private void doReverseStr(char[] s, int left, int right) {
        while (left < right) {
            char temp;

            temp = s[left];

            s[left] = s[right];

            s[right] = temp;

            left++;
            right--;
        }
    }


    /**
     * #151
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        int left = 0;
        int right = words.length - 1;

        while (left < right) {
            String temp = "";

            temp = words[left];

            words[left] = words[right];

            words[right] = temp;

            left++;

            right--;
        }
        StringBuffer sb = new StringBuffer();

        for (String d : words) {
            if (d.equals("")) {
                continue;
            }
            sb.append(d).append(" ");
        }

        return sb.toString().substring(0, sb.length() - 1);
    }


    /**
     * Day9  #28
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        char[] chars = haystack.toCharArray();
        char[] charss = needle.toCharArray();
        for (int i = 0; i < Math.max(haystack.length(), needle.length()); i++) {
            // 如果第一个字符串能对上，则开始匹配，看能否完整匹配
            haystack.indexOf(needle);
        }

        return -1;
    }


    /**
     * Day10 #232  栈>>>先进后出
     */
    class MyQueue {
        Stack<Integer> sa;
        Stack<Integer> cache;

        public MyQueue() {
            sa = new Stack<>();
            cache = new Stack<>();
        }

        public void push(int x) {
            sa.push(x);
        }

        public int pop() {
            if (cache.isEmpty()) {
                this.toCache();
            }

            return cache.pop();
        }

        public int peek() {
            if (cache.isEmpty()) {
                this.toCache();
            }

            return cache.peek();
        }

        public boolean empty() {
            return sa.isEmpty() && cache.isEmpty();
        }


        private void toCache() {
            while (!sa.isEmpty()) {
                cache.push(sa.pop());
            }
        }
    }


    /**
     *  #225  X 
     */
    class MyStack {
        // 队列>>>>先进先出
        Queue<Integer> queue;
        Queue<Integer> cache;

        public MyStack() {
            queue = new LinkedList<Integer>();
            cache = new LinkedList<Integer>();
        }

        public void push(int x) {
            queue.offer(x);
        }

        public int pop() {
            while (queue.size() != 1) {
                cache.offer(queue.poll());
            }

            Integer value = queue.poll();

            while (!cache.isEmpty()) {
                queue.offer(cache.poll());
            }

            return value;
        }

        public int top() {
            while (queue.size() != 1) {
                cache.offer(queue.poll());
            }

            Integer value = queue.peek();

            cache.offer(value);

            while (!cache.isEmpty()) {
                queue.offer(cache.poll());
            }

            return value;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int target =2 ;
        Record record = new Record();
//        log.info("res >>>{} >>{}", record.sortedSquares(nums));
//        log.info("res >>>{}", record.minSubArrayLen(11,nums));

        String a = "hello";
        String b = "ll";
        log.info(  ">>>>>>>{}" ,record.strStr(a, b));


    }
}
