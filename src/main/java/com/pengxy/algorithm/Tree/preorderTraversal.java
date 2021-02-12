package com.pengxy.algorithm.Tree;

/**
 * @program algorithm
 * @description: 二叉树的先序遍历，递归方式
 * @author: pengxuanyu
 * @create: 2021/02/12 12:45
 */
public class preorderTraversal {
    public static void preorderTraversal(TreeNode root){
        if(root == null){
            System.out.println("根节点为空");
        }else {
            System.out.println(root.val + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);

        }
    }
}
