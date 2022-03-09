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
