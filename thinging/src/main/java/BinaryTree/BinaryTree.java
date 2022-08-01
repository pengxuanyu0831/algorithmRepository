package BinaryTree;

import DynamicProgramming.Node;
import DynamicProgramming.PrefectNode;
import DynamicProgramming.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/03/07 22:54
 */
@Slf4j
public class BinaryTree {
    /**
     * 我们把根节点存储在下标 i=1 的位置，它的左子节点存储在下标为 2 * i 的位置，右子节点存储在下标为 2*i+1 的位置。以此类推，B 节点、C 节点的左右子节点都按照这种规律进行存储
     */

    /**
     * 迭代实现前序遍历
     */
    List<Integer> preOrder(TreeNode root) {
        if (null == root) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        // 先把根节点压栈
        stack.push(root);
        if (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            // 前序遍历是中-->左-->右，但是这里压栈为什么是先压右子树？  栈的特性是后进先出，所以先压右子树，左子树后压栈，才能先出左子树
            if (null != root.right) {
                stack.push(root.right);
            }
            if (null != root.left) {
                stack.push(root.left);
            }
        }
        return result;
    }


    /**
     * 迭代实现中序遍历
     * 首先依次将左子节点全部加入栈中，所以第一个while循环后栈顶元素对应一个子树的最左子节点，然后将该元素出栈加入list，并判断该元素的遍历该节点的右子树
     *
     * @param list
     * @return
     */
    List<Integer> midOrder(TreeNode root) {
        if (null == root) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        do {
            // 先将左子节点全部压入栈，
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 建议画个图，就很清晰了
            if (!stack.isEmpty()) {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        } while (null != root || !stack.isEmpty());
        return result;

    }


    /**
     * 后序遍历，感觉就是跟前序遍历反过来
     *
     * @param root
     * @return
     */
    List<Integer> afterOrder(TreeNode root) {
        if (null == root) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        // 先把根节点压栈
        stack.push(root);
        if (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (null != node.left) {
                stack.push(node.left);
            }
            if (null != node.right) {
                stack.push(node.right);
            }

            result.add(0, node.val);
        }
        return result;
    }


    public TreeNode createBinaryTree(LinkedList<Integer> list) {
        TreeNode node = new TreeNode();
        if (list.isEmpty()) {
            return null;
        } else {
            Integer data = list.removeFirst();
            if (data != null) {
                node = new TreeNode(data);
                node.left = createBinaryTree(list);
                node.right = createBinaryTree(list);
            }
        }
        return node;
    }

    /**
     * 二叉树 -先序遍历-递归 #144
     */
    public void preOrderTreeNode(TreeNode root) {
        if (Objects.nonNull(root)) {
            log.info("value is {}", root.val);
            preOrderTreeNode(root.left);
            preOrderTreeNode(root.right);
        }
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        this.doPreOrder(root, list);
        return list;
    }

    private void doPreOrder(TreeNode root, ArrayList<Integer> list) {
        if (null == root) {
            return;
        } else {
            list.add(root.val);
            this.doPreOrder(root.left, list);
            this.doPreOrder(root.right, list);
        }

    }

    /**
     * 二叉树 -中序遍历-递归
     *
     * @param args
     */
    public List<Integer> midOrderTreeNode(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        this.doMidOrder(root, result);
        return result;
    }

    private void doMidOrder(TreeNode node,List<Integer> list) {
        if (node == null) {
            return;
        }
        this.doMidOrder(node.left, list);
        list.add(node.val);
        this.doMidOrder(node.right, list);
    }


    /**
     * #107 二叉树层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            // 每次大循环时，这个值才会变化
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.addFirst(list);
        }
        return result;
    }


    /**
     * #109
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 是否遍历到单层最后面的元素
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }


    /**
     * 637
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                System.out.println("node值为" + node.val + "层和为" + sum + "平均数为" + (double) sum / size);
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
            result.add(sum / size);
        }
        return result;
    }

    /**
     * #429
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> value = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.children.size() > 0) {
                    queue.addAll(node.children);
                }
                value.add(node.val);
            }
            result.add(value);
        }
        return result;
    }


    /**
     * #515
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 精髓
            int max = queue.peek().val;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val > max) {
                    max = node.val;
                }
                if (node.right != null ) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
            result.add(max);
        }
        return result;
    }


    /**
     * #116
     * @param args
     * 此题有递归解法
     */
    public PrefectNode connect(PrefectNode root) {
        PrefectNode pNode = new PrefectNode();
        if (root == null) {
            return null;
        }
        Queue<PrefectNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 本层的头部节点
            PrefectNode header = null;
            for (int i = 0; i < size; i++) {
                PrefectNode node = queue.poll();
                if (header != null) {
                    header.next = node;
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null ) {
                    queue.add(node.right);
                }
                header = node;
            }
        }
        return root;
    }

    /**
     * 翻转二叉树 递归写法
     * 交换每个节点的左右子树即可实现翻转二叉树
     * @param args
     */
    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        root.right = left;
        root.left = right;
        this.invertTree(root.left);
        this.invertTree(root.right);

        return root;
    }


    /**
     * 递归写法 翻转二叉树 深度优先遍历
     *
     * @param args
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode left = root.left;
            TreeNode right = root.right;

            root.right = left;
            root.left = right;

            if (root.right != null) {
                stack.push(root.right);
            }

            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return root;
    }


    /**
     * 递归写法 广度优先遍历 层序遍历
     *
     * @param args
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = stack.poll();
                TreeNode left = root.left;
                TreeNode right = root.right;

                root.right = left;
                root.left = right;

                if (root.left != null) {
                    stack.add(root.left);
                }

                if (root.right != null) {
                    stack.add(root.right);
                }
            }
        }
        return root;
    }


    /**
     * #101
     * 对称的递归写法
     * @param args
     */
    public boolean isSymmetric(TreeNode root){
        if (null == root) {
            return true;
        }
        return cmp(root.left, root.right);
    }

    public boolean cmp(TreeNode node1, TreeNode node2) {
        // 相比较的两个节点如果都为空则为T
        if (node1 == null && node2 == null) {
            return true;
        }

        if(node1 == null || node2 == null || node1.val != node2.val){
            return false;
        }
        return cmp(node1.left , node2.right) && cmp(node1.right ,node2.left);
    }

    /**
     * #101 的遍历写法
     *
     * @param args
     */
    public boolean isSymmetric1(TreeNode root) {
        if (null == root) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (null == node1 && null == node2) {
                continue;
            }
            if(node1 == null || node2 == null || node1.val != node2.val){
                return false;
            }
            // 这里是对应的
            queue.add(node1.left);
            queue.add(node2.right);

            queue.add(node1.right);
            queue.add(node2.left);

        }
        return true;
    }

    /**
     * #104
     * 递归法
     * @param args
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * #104迭代法
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

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
            }
        }
        return depth;
    }


    /**
     * #559
     * N叉树
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int j = 0; j < node.children.size(); j++) {
                    if (node.children.get(j) != null) {
                        queue.add(node.children.get(j));
                    }
                }
            }
        }
        return depth;
    }


    /**
     * #111
     * @param root
     * @return
     * 层序遍历
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

    public int countDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        int leftCount = 0;
        int rightCount = 0;

        while (left != null) {
            leftCount++;
            left = left.left;
        }
        while (right != null) {
            rightCount++;
            right = right.right;
        }

        if (rightCount == leftCount) {
            return (int)Math.pow(2, rightCount)  - 1;
        }
        return this.countDepth(root.left) + this.countDepth(root.left) + 1;
    }


    /**
     * #110
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root){
        if(root == null){
            return true;
        }else{
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root){
        if(root == null){
            return 0;
        }else{
            return Math.max(height(root.left),height(root.right)) + 1;
        }
    }


    /**
     * #257
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root){
        List<String> reslut = new ArrayList<>();
        if (root == null) {
            return reslut;
        }
        List<Integer> paths = new ArrayList<>();
        this.doBinaryTreePaths(root, paths, reslut);
        return reslut;
    }

    public void doBinaryTreePaths(TreeNode root, List<Integer> paths, List<String> reslut) {
        paths.add(root.val);
        // 终止判断
        if (root.right == null && root.left == null) {
            StringBuffer sb = new StringBuffer();
            for (Integer i : paths) {
                sb.append(i).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            reslut.add(sb.toString());
        }

        if (root.left != null) {
            this.doBinaryTreePaths(root,paths,reslut);
            // 回溯
            paths.remove(paths.get(paths.size() - 1));
        }

        if (root.right != null) {
            this.doBinaryTreePaths(root,paths,reslut);
            // 回溯
            paths.remove(paths.get(paths.size() - 1));
        }


    }


    /**
     * #572
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        // subRoot要么是root的左子树，要么是右子树 ，要么就是自己
        return this.isSubtree(root.left, subRoot) || this.isSubtree(root.right,subRoot) || this.isTheSame(root,subRoot);

    }

    boolean isTheSame(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }
        return isTheSame(node1.left, node2.left) && isTheSame(node1.right , node2.right);
    }


    /**
     * #404
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = 0;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 左叶子节点的判定：父节点不为空 且 某个子节点的左右子树均为空 ，则为左叶子
            if (node.left != null && node.left.left == null && node.left.right == null) {
                result += node.left.val;
            }
            if (node.left != null) {
                stack.add(node.left);
            }

            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return result;
    }


    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;


        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }


    /**
     * #113
     * @param root
     * @param targetSum
     * @return
     */
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return result;
        }
        this.subPathSum(root, targetSum);
        return result;
    }

    void subPathSum(TreeNode node, int targetSum) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        targetSum -= node.val;
        // 走到低了
        if (node.left == null && node.right == null && targetSum == 0) {
            result.add(new LinkedList<>(path));
        }
        this.subPathSum(node.left, targetSum);
        this.subPathSum(node.right, targetSum);
        // 回溯， 删去不符合要求的路径
        path.removeLast();
    }


    TreeNode init(int[] inorder, int[] postorder) {
        int inOrderLength = inorder.length;
        int postOrderLength = postorder.length;
        if (postOrderLength == 0 || inOrderLength == 0) {
            return null;
        }
        // 第一步，从后续数组最后一个作为分割点，分割中序数组
        int point = postorder[postOrderLength - 1];
        TreeNode node = new TreeNode(point);

        // 第二步，找到了分割点，拿到分割点的下标，再去切割后续数组
        int split = 0;
        for (int i = 0; i < inOrderLength ;i++) {
            if (inorder[i] == point) {
                split = i;
                break;
            }
        }

        // 切分中序数组，左闭右开
        int[] leftMid = Arrays.copyOfRange(inorder, 0, split);
        int[] leftBehind = Arrays.copyOfRange(postorder, 0, split);
        node.left = this.init(leftMid, leftBehind);

        int[] rightMid = Arrays.copyOfRange(inorder, split + 1, inOrderLength);
        int[] rightBegind = Arrays.copyOfRange(postorder, split, postOrderLength - 1);
        node.right = this.init(rightMid, rightBegind);

        return node;
    }


    /**
     * #105
     * @param preorder
     * @param inorder
     * @return
     *    简洁进阶版  参考
     *         if(preorder.length = = 0 | | inorder.length = = 0){
     *             return null;
     *         }
     *         TreeNode root=new TreeNode (preorder[0]);
     *         for(int i=0;i<preorder.length;i++){
     *             if(preorder[0]==inorder[i]){
     *                 root.left=buildTree(Arrays.copyOfRange(preorder,1,i+1),Arrays.copyOfRange(inorder,0,i));
     *                 root.right=buildTree(Arrays.copyOfRange(preorder,i+1,preorder.length),Arrays.copyOfRange(inorder,i+1,inorder.length));
     *                 break;
     *             }
     *         }
     *         return root;
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preOrderLength = preorder.length;
        int inOrderLength = inorder.length;
        if (preOrderLength == 0 || inOrderLength == 0) {
            return null;
        }

        // 前序遍历的第一个就是根节点
        int value = preorder[0];
        TreeNode root = new TreeNode(value);

        int split = 0;
        for (int limit = 0; limit < inOrderLength; limit++) {
            if (inorder[limit] == root.val) {
                split = limit;
                break;
            }
        }
        // 开始构造左右子树
        int[] leftTree = Arrays.copyOfRange(inorder, 0, split);
        int[] rightTree = Arrays.copyOfRange(inorder, split + 1, inOrderLength);

        int[] leftPreOrderTree = Arrays.copyOfRange(preorder, 1, split + 1);
        int[] rightPreOrderTree = Arrays.copyOfRange(preorder, split + 1, preOrderLength);
        root.right = this.buildTree(rightTree, rightPreOrderTree);
        root.left = this.buildTree(leftTree, leftPreOrderTree);

        return root;
    }


    /**
     * #654
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int maxValue = 0;
        int maxValueIndex = 0;
        // 先找到最大的值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxValueIndex = i;
            }
        }
        // 构建根节点
        TreeNode root = new TreeNode(maxValue);
        // 参照题目，递归左侧数组构造左子树，递归右侧数组构造右子树
        int[] leftTree = Arrays.copyOfRange(nums, 0, maxValueIndex);
        int[] rightTree = Arrays.copyOfRange(nums, maxValueIndex + 1, nums.length);
        root.left = this.constructMaximumBinaryTree(leftTree);
        root.right = this.constructMaximumBinaryTree(rightTree);
        return root;
    }


    /**
     * #98
     *
     * @param root
     * @return
     */

    TreeNode pre = new TreeNode();
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        boolean b = this.isValidBST(root.left);
        if (pre != null && pre.val > root.val) {
            return false;
        }
        pre = root;
        return b  && this.isValidBST(root.right);
    }


    /**
     * #589
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 要用栈
        Stack<Node> queue = new Stack<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node node = queue.pop();
            result.add(node.val);
            for (int i = node.children.size() - 1; i >= 0 ; i--) {
                queue.push(node.children.get(i));
            }
        }
        return result;
    }


    /**
     * #102
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> chi = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                chi.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(chi);
        }

        return result;

    }






    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        LinkedList list = new LinkedList();
/*        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null);
        list.add(null);
        list.add(4);
        list.add(5);
        list.add(null);
        list.add(6);
        list.add(null);
        list.add(null);
        list.add(7);

        TreeNode node = bt.createBinaryTree(list);
        System.out.println("前序遍历");
        bt.preOrderTreeNode(node);
        System.out.println("中序遍历");
        bt.midOrderTreeNode(node);*/


        int[] pre = new int[]{3, 9, 20, 15, 7};
        int[] mid = new int[]{9, 3, 15, 20, 7};
        TreeNode treeNode = bt.buildTree(pre, mid);
        System.out.println(treeNode);
    }
}
