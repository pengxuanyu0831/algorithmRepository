package com.pengxy.algorithm.Tree.LeetCode;

/**
 * @program algorithm
 * @description: 判断一棵树是不是对称  递归的实现
 * @author: pengxuanyu
 * @create: 2021/02/14 16:13
 */
public class isMirror1 {
    public boolean isMirror(TreeNode root){
        if(root == null ){
            return true;
        }
        return cmp(root.left,root.right);
    }
    // 这个问题递归的关键思路在于 A方法（左子树，右子树）{左值 == 右值；
    // A(左树的左子树 == 右树的右子树) ;
    // && A（左树的右子树 == 右树的左子树）;}
    private boolean cmp(TreeNode node1, TreeNode node2){
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 == null || node2 == null || node1.val != node2.val){
            return false;
        }
        return cmp(node1.left ,node2.right) && cmp(node1.right, node2.left);

    }
}
