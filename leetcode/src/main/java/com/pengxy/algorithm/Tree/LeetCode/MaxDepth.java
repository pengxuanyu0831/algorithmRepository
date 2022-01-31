package com.pengxy.algorithm.Tree.LeetCode;

import java.util.Stack;

/**
 * @program algorithm
 * @description: 究极递归 耗时最短，开销略大
 * @author: pengxuanyu
 * @create: 2021/02/15 12:10
 */
public class MaxDepth {
    public int maxDepth(TreeNode root){
        // 左右子树中更大的那个
        return root == null ? 0 : Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}
