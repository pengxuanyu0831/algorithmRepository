package com.pengxy.algorithm.Tree.LeetCode;

/**
 * @program algorithm
 * @description: 翻转二叉树 递归
 * @author: pengxuanyu
 * @create: 2021/02/27 10:02
 */
public class invertTree {
    public static TreeNode invertTree(TreeNode root){
        if(root == null){
            return root;
        }
        // 保存一侧，作为基准
        TreeNode lefttree = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(lefttree);
        return root;
    }

}
