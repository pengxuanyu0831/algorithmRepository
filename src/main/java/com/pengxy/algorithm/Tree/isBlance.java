package com.pengxy.algorithm.Tree;
import com.pengxy.algorithm.Tree.TreeNode;


/**
 * @program algorithm
 * @description: 判断是否为平衡二叉树
 * @author: pengxuanyu
 * @create: 2021/02/02 22:13
 */
public class isBlance {
    public boolean isBlance(TreeNode root){
        if(root == null){
            return true;
        }else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBlance(root.left) && isBlance(root.right);
        }
    }

    public int height(TreeNode root){
        if(root == null){
            return 0;
        }else{
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }
}
