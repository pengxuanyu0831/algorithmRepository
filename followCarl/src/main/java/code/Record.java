package code;

import com.pengxy.algorithm.AboutList.ListNode;
import com.pengxy.algorithm.Tree.LeetCode.TreeNode;
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


    /**
     * Day.11  #20   X
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<String, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {

            switch (c) {
                case '(':
                    map.put("(", map.getOrDefault("(", 0) + 1);
                    break;
                case '[':
                    map.put("[", map.getOrDefault("[", 0) + 1);
                    break;
                case '{':
                    map.put("{", map.getOrDefault("{", 0) + 1);
                    break;
            }

        }


        for (char ar : s.toCharArray()) {
            if (ar == '(' || ar == '[' || ar == '{') {
                continue;
            }

            switch (ar) {
                case ')':
                    Integer integer = map.get("(");
                    if (integer == null || integer < 1) {
                        return false;
                    }

                    map.replace("(", integer - 1);
                    break;
                case ']':
                    Integer integer1 = map.get("[");
                    if (integer1 == null || integer1 < 1) {
                        return false;
                    }

                    map.replace("[", integer1 - 1);
                    break;
                case '}':
                    Integer integer2 = map.get("{");
                    if (integer2 == null || integer2 < 1) {
                        return false;
                    }

                    map.replace("{", integer2 - 1);
                    break;
            }
        }

        for (String cc : map.keySet()) {
            if (map.get(cc) >0) {
                return false;
            }
        }

        return true;
    }


    /**
     * #1047
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        while (!isTheSame(s)) {
            char[] chars = s.toCharArray();

            for (int k = 0; k < chars.length - 2; k++) {

                if (chars[k] == chars[k + 1]) {
                    chars[k] = '0';
                    chars[k + 1] = '0';
                }
            }
            s = String.copyValueOf(chars).replaceAll("00", "");
            log.info("chars >>>{}", chars);
        }
        return s;
    }

    public boolean isTheSame(String str) {

        char[] chars = str.toCharArray();
        log.info("chars >>>{}", chars);
        if (str.length() == 2) {
            if (chars[0] == chars[1]) {
                return false;
            }
        }

        for (int i = 0; i < chars.length - 2; i++) {
            if (chars[i + 1] == chars[i]) {
                return false;
            }
        }

        return true;
    }


    /**
     * Day13 #239   X 暴力解法并不可取
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }

        int[] res = new int[nums.length - k + 1];

        int left = 0;
        int right = k - 1;
        while (right < nums.length) {
            int[] temp = new int[right - left + 1];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = nums[left + i];
            }

            Integer max = this.getMax(temp);
            res[left] = max;
            left++;
            right++;
        }
        return res;
    }


    private Integer getMax(int[] numss) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < numss.length ; i++) {
            max = Math.max(max, numss[i]);
        }
        return max;
    }



    /**
     * #347  堆排序，关注+1
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
     * Day15  #101
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        // 参见其他#101 的写法
        return true;
    }


    /**
     * Day16 #111
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
                // 最小深度---到叶子节点---左右子树都为空就是叶子节点
                if (node.right == null && node.left == null) {
                    return depth;
                }
            }
        }
        return depth;
    }

    /**
     * #222
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                count++;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return count;
    }


    /**
     * Day17  #110
     * @param
     * @return
     */


    /**
     * Day21 #530
     *
     * @param root
     * @return
     */
    TreeNode pre;

    Integer res = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        this.doGetMinimumDiff(root);
        return res;
    }

    private void doGetMinimumDiff(TreeNode node) {
        if (node == null) {
            return;
        }

        // 左
        this.doGetMinimumDiff(node.left);

        // 中
        if (pre != null) {
            res = Math.min(res, node.val - pre.val);
        }

        pre = node;

        // 右
        this.doGetMinimumDiff(node.right);
    }


    /**
     * Day22  #235
     * @param root
     * @param p
     * @param q
     * @return
     * @deprecated 二叉搜索树特性：左子节点小于根节点 右节点大于根节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果p q 都在右侧
        if (p.val > root.val && q.val > root.val) {
            return this.lowestCommonAncestor(root.right, p, q);
        }

        if (p.val < root.val && q.val < root.val) {
            return this.lowestCommonAncestor(root.left, p, q);
        }

        return root;
    }


    /**
     * #701
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            // 小于该在根节点左边
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }


    /**
     * Day.23  #216
     *
     * @param k
     * @param n
     * @return
     */
    List<List<Integer>> sumRes = new ArrayList<>();
    List<Integer> sumResMember = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.doCombinationSum3(k, n, 1);
        return sumRes;
    }


    private void doCombinationSum3(int k, int n, int index) {
        int sum = 0;
        // 结束条件
        if (sumResMember.size() == k) {
            for (Integer i : sumResMember) {
                sum += i;
            }
            if (sum == n) {
                sumRes.add(new ArrayList<>(sumResMember));
                return;
            }
        }

        for (int i = index; i <= 9; i++) {
            sumResMember.add(i);
            this.doCombinationSum3(k, n, i + 1);
            sumResMember.remove(sumResMember.size() - 1);
        }
    }





    /**
     * Day23 #77
     * @param n
     * @param k
     * @return
     * @deprecated 回溯第一题，用于掌握思想
     */
    List<Integer> combinePath = new ArrayList<>();
    List<List<Integer>> combineResult = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        this.doCombine(n, k, 1);
        return combineResult;
    }

    private void doCombine(int n, int k, int index) {
        // 1 终止条件
        if (combinePath.size() == k) {
            combineResult.add(new ArrayList<>(combinePath));
            return;
        }

        for (int i = index; i <= n; i++) {
            combinePath.add(i);
            this.doCombine(n, k, i + 1);
            // 回溯
            combinePath.remove(combinePath.size() - 1);
        }
    }


    /**
     * Day24  #39
     * @param candidates
     * @param target
     * @return
     */
    List<Integer> combinationSumPath = new ArrayList<>();
    List<List<Integer>> combinationSumeResult = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.doCombinationSum(candidates, target, 0, 0);
        return combinationSumeResult;
    }


    private void doCombinationSum(int[] cans, int target, int sum,int index) {
        // 停止条件
        if (sum > target) {
            return;
        }

        if (sum == target) {
            combinationSumeResult.add(new ArrayList<>(combinationSumPath));
            return;
        }

        for (int i = index; i < cans.length && cans[i] + sum <= target; i++) {
            sum += cans[i];
            combinationSumPath.add(cans[i]);

            this.doCombinationSum(cans, target, sum, i);
            sum -= cans[i];
            combinationSumPath.remove(combinationSumPath.size() - 1);
        }

    }

    /**
     * Day33 #90
     *
     * @param nums
     * @return
     */
    Set<Integer> users;
    List<Integer> subsetsWithDupPath = new ArrayList<>();
    List<List<Integer>> subsetsWithDupRes = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        nums = Arrays.stream(nums).sorted().toArray();
        this.doSubsetsWithDub(nums, 0, users);
        return subsetsWithDupRes;
    }

    public void doSubsetsWithDub(int[] nums, int index, Set<Integer> users) {
        subsetsWithDupRes.add(new ArrayList<>(subsetsWithDupPath));
        users = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (users.contains(nums[i])) {
                continue;
            }
            users.add(nums[i]);
            subsetsWithDupPath.add(nums[i]);

            this.doSubsetsWithDub(nums, i + 1, users);

            subsetsWithDupPath.remove(subsetsWithDupPath.size() - 1);
        }
    }


    /**
     * #93
     * @return
     */


    /**
     * Day36  #455
     * @param g：饼干
     * @param s：小孩
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        int count = 0;

        for (int i = 0; i < s.length && index < g.length; i++) {
            if (s[i] >= g[index]) {
                index++;
                count++;
            }
        }

        return count;
    }


    /**
     * Day37 #738
     * 贪心！ 当发现当前数字 > 下一个数字时，就把当前数字 - 1，后面的所有数字都设置成 9。
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                nums[i]--;
                for (int j = i + 1; j < nums.length; ++j) {
                    nums[j] = '9';
                }
            }
        }
        return Integer.parseInt(new String(nums));
    }



    public static void main(String[] args) {
        int target =2 ;
        Record record = new Record();
//        log.info("res >>>{} >>{}", record.sortedSquares(nums));
//        log.info("res >>>{}", record.minSubArrayLen(11,nums));

        String a = "aaaaaaaa";
        String b = "ll";
//        log.info(">>>>>>>{}", record.maxSlidingWindow(nums, 5000));


    }
}
