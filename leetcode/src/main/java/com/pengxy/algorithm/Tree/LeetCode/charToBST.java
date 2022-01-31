package com.pengxy.algorithm.Tree.LeetCode;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/02/28 17:28
 */
public class charToBST {
    public TreeNode charToBST(int[] nums){
        return getMid(nums,0,nums.length);
    }

    public TreeNode getMid(int[] nums,int left, int right){
        if(left == right){
            return null;
        }else{
            int mid = (left + right) / 2;
            // 中间值作为树的根节点
            TreeNode node = new TreeNode(nums[mid]);
            node.left = getMid(nums,left,mid);
            node.right = getMid(nums,mid + 1,right);
            return node;
        }

    }
}
