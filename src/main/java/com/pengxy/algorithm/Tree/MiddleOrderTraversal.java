package com.pengxy.algorithm.Tree;

import com.pengxy.algorithm.Tree.LeetCode.TreeNode;

/**
 * @program algorithm
 * @description: 中序遍历，递归
 * @author: pengxuanyu
 * @create: 2021/02/12 13:56
 */
public class MiddleOrderTraversal {
    public static void middleOrderTraversal(TreeNode root){
        if(root != null){
            middleOrderTraversal(root.left);
            System.out.println(root.val + " ");
            middleOrderTraversal(root.right);
        }
    }
}
