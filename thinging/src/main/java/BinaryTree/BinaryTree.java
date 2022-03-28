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

            if (null != node.right) {
                stack.push(node.right);
            }
            if (null != node.left) {
                stack.push(node.left);
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
     * 二叉树 -先序遍历-递归
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
        this.preOrder(root, list);
        return list;
    }

    void preOrder(TreeNode root, ArrayList<Integer> list) {
        if (null == root) {
            return;
        } else {
            list.add(root.val);
            this.preOrder(root.left, list);
            this.preOrder(root.right, list);
        }

    }

    /**
     * 二叉树 -中序遍历-递归
     *
     * @param args
     */
    public void midOrderTreeNode(TreeNode root) {
        if (Objects.nonNull(root)) {

            preOrderTreeNode(root.left);
            log.info("value is {}", root.val);
            preOrderTreeNode(root.right);
        }
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
        return root == null? 0 : Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
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



    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        LinkedList list = new LinkedList();
        list.add(1);
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
        bt.midOrderTreeNode(node);
    }
}
