package BinaryTree;

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
            result.add(0,node.val);
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
            this.preOrder(root.left,list);
            this.preOrder(root.right, list);
        }

    }

    /**
     * 二叉树 -中序遍历-递归
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
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            // 每次大循环时，这个值才会变化
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null ) {
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
                if (node.left != null ) {
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
                System.out.println("node值为" + node.val + "层和为" + sum + "平均数为" + (double)sum / size);
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
