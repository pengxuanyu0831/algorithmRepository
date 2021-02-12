package com.pengxy.algorithm.Tree;

import java.util.Stack;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/02/12 13:59
 */
public class MiddleOrderTraversal1 {
    public static void middleOrderTraversal1(TreeNode root){
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        TreeNode node = root;
        while(node != null || !treeNodeStack.isEmpty()){
            while (node != null){
                treeNodeStack.push(node);
                node = node.left;
            }
            // 如果这个栈里不为空
            if(!treeNodeStack.isEmpty()){
                // 永远先考虑左子树，左子树为空时才访问根节点
                node = treeNodeStack.pop();
                System.out.println(node.val + "");
                node = node.right;
            }
        }
    }
}
