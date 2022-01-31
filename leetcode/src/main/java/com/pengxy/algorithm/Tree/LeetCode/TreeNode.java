package com.pengxy.algorithm.Tree.LeetCode;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/02/02 22:17
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode (int val ){
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

