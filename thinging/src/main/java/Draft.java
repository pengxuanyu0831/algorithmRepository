import DynamicProgramming.TreeNode;
import com.pengxy.algorithm.AboutList.ListNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import sun.reflect.generics.tree.Tree;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @program algorithm
 * @description: 草稿类
 * @author: pengxuanyu
 * @create: 2022/04/26 21:57
 */
@Slf4j
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


    /**
     * #1413
     * @param nums
     * @return
     */
    public int minStartValue(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0] + nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        Arrays.sort(dp);
        return dp[0] > 0 ? 1 : 0 - dp[0] + 1;
    }


    public void bagDraft(int[] thingsValues,int[] thingsWeight,int maxWeight) {
        // step1: 确定数组的意义,从0-i下标取物品，放到容量为j的包里，最大放置重量为dp[i][j]
        int[][] dp = new int[thingsWeight.length + 1][maxWeight + 1];
        // 初始化数据
        for (int i = 0; i <= thingsWeight.length; i++) {
            dp[i][0] = thingsValues[0];
        }
        // 先遍历物品
        for (int i = 1; i <= thingsWeight.length; i++) {
            // 再遍历背包
            for (int j = 1; j <= maxWeight; j++) {
                // 当背包容量小于当前遍历的物品时，最大容量是截止到前一个物品的总容量
                if (j < thingsWeight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 这里是当前背包容量j >=当前物品，但是当前物品不一定会放进背包里，因为有可能背包此时已经有的物品总和价值大于当前物品
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - thingsWeight[i]] + thingsValues[i]);
                }
            }
        }
    }


    public void bagDraftOne(int[] thingsValues, int[] thingsWeight, int maxWeight) {
        // 容量为j的背包可以获得的最大价值为dp[j]
        int[] dp = new int[maxWeight + 1];
        // 先遍历物品的原因是，如果先遍历背包，物品会重复累计
        for (int i = 0; i < thingsWeight.length; i++) {
            for (int j = maxWeight; j >= thingsWeight[i]; j--) {
                // 初始化完成时，整个dp数组都是0，这一行执行完才覆盖数据
                dp[j] = Math.max(dp[j], dp[j - thingsWeight[i]] + thingsValues[i]);
            }
        }
    }

    public void interview(int[] force, int[] cost, int selectNum, int maxSize, int gold) {
        // dp[i][j]:最大数量为i 对应的金币消耗为j时最大战力为dp[i][j]
        int[][] dp = new int[maxSize + 1][gold + 1];
        for (int j = 0; j < cost.length; j++) {
            dp[0][j] = force[0];
        }
        for (int i = 1; i <= force.length; i++) {
            for (int j = 0; j < maxSize; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - force[i]] + cost[i]);
            }
        }
    }


    /**
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        Integer integer = x;
        char[] chars = integer.toString().toCharArray();
        int left = 0;
        int right = chars.length - 1;
        for (int i = 0; i < right; i++) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     *
     * @param n
     * @return
     */

    public List<String> generateParenthesis(int n) {
        this.doGenerateParenthesis(n, n, "");
        return path;
    }

    private void doGenerateParenthesis(int left,int right, String str) {
        if (left == 0 && right == 0) {
            path.add(str);
            return;
        }
        if (left > 0) {
            this.doGenerateParenthesis(left - 1, right, str + "(");
        }
        if (right > left) {
            this.doGenerateParenthesis(left, right - 1, str + ")");
        }
    }


    /**
     * #1619
     * @param arr
     * @return
     */
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        log.info("arr->{}",arr);
        int index = arr.length / 20;
        log.info("index->{}", index);
        int[] result = new int[arr.length - 2 * index];
        int count = 0;
        for (int i = index; i < arr.length - index; i++) {
            result[count] = arr[i];
            count++;
        }
        double sum = 0;
        for (int k = 0; k < result.length; k++) {
            sum += result[k];
        }
        log.info("sum->{} length->{}",sum,result.length);
        return sum / result.length;
    }

    List<Integer> pathInt = new ArrayList<>();
    List<List<Integer>> resultInt = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        doCombinationSum3(k, n, 0);
        return resultInt;
    }

    /**
     * #216(练习)
     * @param k
     * @param n
     * @param index
     */
    public void doCombinationSum3(int k, int n, int index) {
        int sum = 0;
        if (pathInt.size() == k) {
            for (Integer i : pathInt) {
                sum += i;
            }
            if (sum == n) {
                resultInt.add(new ArrayList<>(pathInt));
                return;
            }
        }
        for (int i = index; i <= 9; i++) {
            pathInt.add(i);
            doCombinationSum3(k, n, index + 1);
            pathInt.remove(pathInt.size() - 1);
        }
    }


    /**
     *
     * @param nums
     * @return
     */
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        list.sort((a, b) ->{
            if (map.get(a) == map.get(b)) {
                return b - a;
            } else {
                return map.get(a) - map.get(b);
            }
        });
        return list.stream().mapToInt(Integer::intValue).toArray();
    }


    /**
     * Offer offer03
     * @param nums
     * @deprecated
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[nums[i]]++;
            if (result[nums[i] ]> 1) {
                return nums[i];
            }
        }
        return -1;
    }


    /**
     * offer27
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = right;
        root.right = left;
        this.mirrorTree(root.left);
        this.mirrorTree(root.right);
        return root;
    }

    public TreeNode mirrorTree2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            TreeNode left = node.left;
            TreeNode right = node.right;

            node.left = right;
            node.right = left;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return root;
    }


    /**
     * offer04
     * @param matrix
     * @param target
     *
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int l = matrix.length;
        int k = matrix[0].length;

        int row = 0;
        int col = k-1;
        // 从右上角开始查找比较--->当比较的基准过多时，要想到取极值，减少比较的基准
        while (row < l && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 有序数组转为二叉搜索树
     * @param num
     * @return
     */
    public TreeNode sortedArrayToBst(int[] num) {
        return this.doSortedArrayToBst(num, 0, num.length - 1);
    }

    public TreeNode doSortedArrayToBst(int[] num, int start, int end) {
        int mid = (start + end) >> 1;
        TreeNode root = new TreeNode(num[mid]);
        root.left = this.doSortedArrayToBst(num, start, mid - 1);
        root.right = this.doSortedArrayToBst(num, mid + 1, end);
        return root;
    }


    /**
     * offer06
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        int size = 0;
        // 这里新建一个变量保存head，只是为了求链表长度而已，求完长度还需要用head，所以head不能变
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        int[] result = new int[size];
        while (head != null) {
            result[--size] = head.val;
            head = head.next;
        }
        return result;
    }


    /**
     * offer07
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLength = preorder.length;
        int inLength = inorder.length;

        if (preLength == 0 || inLength == 0) {
            return null;
        }

        // 前序遍历的首位必是根节点，按照根节点的下标去切分中序遍历的结果数组，切分为左右两个数组
        TreeNode root = new TreeNode(preorder[0]);
        int splitIndex = 0;
        for (int i = 0; i < inLength; i++) {
            if (inorder[i] == root.val) {
                splitIndex = i;
                break;
            }
        }

        int[] midLeft = Arrays.copyOfRange(inorder, 0, splitIndex);
        int[] midBehind = Arrays.copyOfRange(preorder, 1, splitIndex+1);
        root.left = this.buildTree(midBehind, midLeft);

        int[] midRight = Arrays.copyOfRange(inorder, splitIndex + 1, inLength);
        int[] midBehind1 = Arrays.copyOfRange(preorder, splitIndex + 1, preLength);
        root.right = this.buildTree(midBehind1, midRight);
        return root;
    }


    /**
     * #788
     * @param n
     * @return
     */
    public int rotatedDigits(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (this.doRotateDigits(i)) {
                result += 1;
            }
        }
        return result;
    }

    public boolean doRotateDigits(int n) {
        Integer integer = n;
        String str = integer.toString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            log.info("ccccccchar{}",c);
            if (c != '0' && c != '1' && c != '9' && c != '8' && c != '2' && c != '5' && c != '6' && c != '9') {
                return false;
            }
            int tran = this.doTran(c);
            log.info("trannnnnnn{}", tran);
            sb.append(tran);
        }
        if (Integer.parseInt(sb.toString()) == n) {
            return false;
        } else {
            return true;
        }
    }

    public int doTran(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 5;
            case '5':
                return 2;
            case '6':
                return 9;
            case '8':
                return 8;
            case '9':
                return 0;
            default:
                return c;
        }
    }


    /**
     * offer10-1 （取模）
     * @param n
     * @return
     */
    public int fib(int n) {
        if(n==0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }


    /**
     * offer10-2
     * @param n
     * @return
     */
    public int numWays(int n) {
        int[] dp = new int[n];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[n] = dp[i - 1] + dp[i - 2];
            dp[n] %=1000000007;
        }
        return dp[n];

    }


    /**
     * offer09
     */
    class CQueue {
        LinkedList<Integer> list;
        LinkedList<Integer> list1;

        public CQueue() {
            list = new LinkedList<>();
            list1 = new LinkedList<>();
        }

        public void appendTail(int value) {
            list.add(value);
        }

        public int deleteHead() {
            Integer integer = list.pollFirst();
            return integer == null ? -1 : integer;
        }
    }


    /**
     * offer11
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[0];
    }


    /**
     * offer12
     * @param board
     * @param word
     * 这题一看就是回溯
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        boolean[][] result = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (doExist(board, result, word.toCharArray(), 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean doExist(char[][] board, boolean[][] result, char[] wordChar, int index, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || result[i][j] || wordChar[index] != board[i][j]) {
            return false;
        }

        if (index == wordChar.length - 1) {
            return true;
        }

        result[i][j] = true;
        boolean answer = false;
        answer =  doExist(board, result, wordChar, index + 1, i + 1, j)
                || doExist(board, result, wordChar, index + 1, i - 1, j)
                || doExist(board, result, wordChar, index + 1, i, j + 1)
                || doExist(board, result, wordChar, index + 1, i, j - 1);
        result[i][j] = false;
        return answer;
    }


    /**
     * offer14
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        int[] dp = new int[n];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = i - 1; j >= 1; --j) {
                // 取左边最大和右边最大的乘积
                dp[i] = Math.max(dp[i], Math.max(dp[j], j) * Math.max(dp[i - j], i - j));
            }
        }
        return dp[n];
    }


    /**
     * offer15
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            n = n & (n - 1);
            result++;
        }
        return result;
    }


    /**
     * offer16
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        if (n == -1) {
            return 1 / x;
        }

        double half = myPow(x, n / 2);
        double mod = myPow(x, n % 2);
        return half * half * mod;



    }


    /**
     * offer17
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        // Math.pow(a,b) --->a的b次方
        int pow = (int)Math.pow(10, n);
        log.info("pow--->{}",pow);
        int[] a = new int[pow - 1];
        for (int i = 0; i < pow - 1; i++) {
            a[i] = i + 1;
        }
        return a;
    }


    /**
     * #1114
     */
    class Foo {
        CountDownLatch second;
        CountDownLatch third;

        public Foo() {
            second= new CountDownLatch(1);
            third = new CountDownLatch(1);

        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            second.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {

            second.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            third.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            third.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            third.countDown();
        }
    }


    /**
     * offer21
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {

        int left = 0;
        int temp;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] % 2 == 1) {
                left += 1;
            } else if (nums[right] % 2 == 0) {
                right -= 1;
            } else {
                temp = nums[left];
                log.info("temp-->{} nums[left]-->{} nums[right]-->{}", temp, nums[left], nums[right]);
                nums[left] = nums[right];
                log.info("temp-->{} nums[left]-->{} nums[right]-->{}", temp, nums[left], nums[right]);
                nums[right] = temp;
                log.info("temp-->{} nums[left]-->{} nums[right]-->{}", temp, nums[left], nums[right]);
                left += 1;
                right -= 1;
                log.info("left -->{} right -->{}",left,right);
            }
        }
        return nums;
    }


    /**
     * offer22
     * @param head
     * @param k
     * @desc 快慢指针
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = new ListNode();
        ListNode slow = new ListNode();
        fast = head;
        slow = head;
        while (null != fast && k > 0) {
            fast = fast.next;
            k -= 1;
        }
        while (null != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * offer24
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode temp = new ListNode();
        while (null != cur) {
            // step1 先保存cur的下一位
            temp = cur.next;
            // step2 改变cur.next的指向，指向pre
            cur.next = pre;
            // step3 移动pre到cur
            pre = cur;
            // step4 移动cur 到保存好的下一位继续
            cur = temp;
        }
        return pre;
    }


    /**
     * offer26
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubStructure(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return false;
        }
        return this.isSubStructure(root.left, subRoot) || this.dfs(root, subRoot) || this.dfs(root.right, subRoot);
    }

    private boolean dfs(TreeNode node, TreeNode subNode) {
        if (subNode == null) {
            return true;
        }

        if (node == null) {
            return false;
        }

        return node.val == subNode.val && this.dfs(node.left, subNode.left) && dfs(node.right, subNode.right);
    }


    /**
     * offer28
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode mirror = mirrorTreeNew(root);
        TreeNode node = root;
        return this.isTheSame(node, mirror);

    }

    public TreeNode mirrorTreeNew(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);

        newRoot.left = this.mirrorTreeNew(root.right);
        newRoot.right = this.mirrorTreeNew(root.left);
        return newRoot;
    }

    private boolean isTheSame(TreeNode node, TreeNode otherNode) {
        if (node == null || otherNode == null) {
            return node == otherNode;
        }

        return node.val == otherNode.val && this.isTheSame(node.left, otherNode.left) && this.isTheSame(node.right, otherNode.right);
    }

    /**
     * offer33
     * @param postorder
     * @return
     * @see https://blog.csdn.net/weixin_43954951/article/details/124697359
     */
    public boolean verifyPostorder(int[] postorder) {
        Deque<Integer> tree = new LinkedList<>();
        int parent = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (parent < postorder[i]) {
                return false;
            }
            while (!tree.isEmpty() && tree.peek() > postorder[i]) {
                parent = tree.pop();
            }
            tree.push(postorder[i]);
        }
        return true;
    }


    /**
     * offer29
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return matrix[0];
        }

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

        if (result.size()<1) {
            int[] res = new int[0];
            return res;
        } else {
            int[] res = new int[result.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = result.get(i);
            }
            return res;
        }
    }


    /**
     * #1758
     * @param s
     * @deprecated 这是个错误示范，但是思路很值得记录一下
     * @return
     */
    public int minOperations(String s) {
        // 正确答案
//        //定义dp[i][0]为 走到i时 i处不变 的最小操作数
//        //dp[i][1]为 走到i时 i处变 的最小操作数
//        int n=s.length();
//        int dp[][]=new int[n+1][2];
//        dp[0][1]=1;
//        for (int i = 1; i < n; i++) {
//            char cur = s.charAt(i);
//            char last = s.charAt(i - 1);
//            if (cur==last){
//                //当前和前面的一样，要么当前不变，选择上一次变,要么当前变,那就选择上一次
//                dp[i][0]=dp[i-1][1];
//                dp[i][1]=dp[i-1][0]+1;
//            }else { //当前和前面不一样,要么现在不变，选择上一次不变  要么现在变，选择上一次也变的
//                dp[i][0]=dp[i-1][0];
//                dp[i][1]=dp[i-1][1]+1;
//            }
//        }
//        return Math.min(dp[n-1][0],dp[n-1][1]);

        int size = s.length();
        // 定义 走到第i位，最小的操作数为dp[i]
        int[] dp = new int[size];
        dp[0] = 0;
        for (int i = 1; i < size; i++) {
            dp[i] = s.charAt(i - 1) == s.charAt(i) ? dp[i - 1] + 1 : dp[i - 1];
            if (s.charAt(i - 1) == s.charAt(i)) {
                log.info("i-1 = {} i = {} iiiii= {}",s.charAt(i-1),s.charAt(i),i);
                if (s.charAt(i) == '1') {
                    log.info("变更  {}-->{}=={}",s.toCharArray()[i],0,dp[i]);
                    s = s.substring(0, i) + "0" + s.substring(i+1);
                    log.info("======{}",s.toCharArray());
                } else {
                    log.info("变更  {}-->{}=={}",s.toCharArray()[i],1,dp[i]);
                    s = s.substring(0, i) + "1" + s.substring(i+1);
                    log.info("++++++{}",s.toCharArray());
                }
            }
        }
        log.info("~~~~~~{}",s.toCharArray());
        return dp[size-1];
    }



    public static void main(String[] args) {
        Draft draft = new Draft();
//        int[] ints = new int[]{1,1};
        int[] intttt = new int[]{-3,2,-3,4,2};
        int[] intttt11 = new int[]{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3};
        int[] intttt2222 = new int[]{1,2,3,4,5,6,7};
//        List<Integer> numbers = draft.findDisappearedNumbers(ints);
        log.info("result-->{}", draft.minOperations("10010100"));
//        int i = draft.minStartValue(intttt);
//        double v = draft.trimMean(intttt11);
        //System.out.println(v);
    }


    // 二叉树的前序遍历  2023-08-06
    // mid -> left -> right
    public List<Integer> preOrder1(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if (node == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        while (stack != null) {
            TreeNode treeNode = stack.pop();
            result.add(treeNode.val);

            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }

            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
        return result;
    }

    public List<Integer> preOrder12(TreeNode node) {
        List<Integer> res = new ArrayList<>();

        doPreOrder12(node, res);
        return res;
    }

    public void doPreOrder12(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }

        res.add(node.val);
        doPreOrder12(node.left, res);
        doPreOrder12(node.right, res);
    }




}
