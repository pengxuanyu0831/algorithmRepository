package com.pengxy.algorithm.Tree;

import com.pengxy.algorithm.Tree.LeetCode.TreeNode;

import java.util.Stack;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/02/12 15:22
 */
public class RightOrderTraversal {
    public static void rightOrderTraversal(TreeNode root){
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        TreeNode node = root;
        // 这里需要一个节点是因为，后序遍历需要遍历完左子树才能去遍历右子树
        TreeNode lastNode = root;
        while (!treeNodeStack.isEmpty() || node != null){
            while (node != null){
                treeNodeStack.push(node);
                node = node.left;
            }
            // 查看当前栈顶元素
            node = treeNodeStack.peek();
            // 如果右子树为空，或是最新的值就是右子树，那么说明此时左子树遍历完成，直接输出根节点即可
            if(node.right == null || node.right == node ){
                System.out.println(node.val + " ");
                treeNodeStack.pop();
                lastNode = node;
            }else {
                node = node.right;
            }
        }
    }
}
