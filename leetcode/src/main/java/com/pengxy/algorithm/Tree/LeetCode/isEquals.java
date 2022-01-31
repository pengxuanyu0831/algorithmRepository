package com.pengxy.algorithm.Tree.LeetCode;

/**
 * @program algorithm
 * @description: 树的深度是否等于给定的值？ 非递归方式
 * @author: pengxuanyu
 * @create: 2021/02/21 12:44
 */
public class isEquals {
    public static boolean result;
    public static boolean isEquals(TreeNode root , int sum){
        if(root == null){
            return false;
        }
        deep(root , sum);
        // boolean deep;
        return result;
    }
    // 遍历树，每深入一层，都比较一下sum 和根节点的深度，相等返回true
    public static void deep(TreeNode root , int sum){
        if(root == null){
            return ;
        }
        if(root.left == null && root.right == null){
            if(root.val - sum == 0){
                boolean result = true;
            }
        }
        deep(root.left , sum - root.val);
        deep(root.right,sum - root.val);
    }
}
