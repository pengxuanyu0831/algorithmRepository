package com.pengxy.algorithm.Tree.LeetCode;

/**
 * @program algorithm
 * @description: 判断路径是否存在 ，递归方式
 * @author: pengxuanyu
 * @create: 2021/02/21 13:01
 */
public class isEquals1 {
    public static boolean isEuqals(TreeNode root , int sum){
        if(root == null ){
            return false;
        }
        if(root.left == null && root.right == null){
            return root.val == sum;
            // return root.val - sum == 0;
        }
        return isEuqals(root.left, sum - root.val )
                || isEuqals(root.right, sum - root.val);
    }
}
