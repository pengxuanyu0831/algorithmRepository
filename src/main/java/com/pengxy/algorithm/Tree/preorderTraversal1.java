package com.pengxy.algorithm.Tree;

import java.util.Stack;

/**
 * @program algorithm
 * @description: 先序遍历 非递归方式
 * @author: pengxuanyu
 * @create: 2021/02/12 12:50
 */
public class preorderTraversal1 {
    public static void preorderTraversal1(TreeNode root){
        // 新建一个栈来缓存节点
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        // 游标节点为根节点
        TreeNode node = root;
        while (!treeNodeStack.isEmpty() || node != null){
            // 若当前节点非空，则输出这个节点
            while (node != null){
                System.out.println(node.val + " ");
                treeNodeStack.push(node);
                node = node.left;
            }
            // 当左子树为空时，开始遍历右子树
            // 如果栈为空，即所有节点都遍历完成了
            if(!treeNodeStack.isEmpty()){
                node = treeNodeStack.pop();
                node = node.right;

            }
        }
    }
}
