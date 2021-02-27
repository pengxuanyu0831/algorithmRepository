package com.pengxy.algorithm.Tree.LeetCode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @program algorithm
 * @description: 非递归翻转树
 * @author: pengxuanyu
 * @create: 2021/02/27 10:18
 */
public class invertTree1 {
    public static TreeNode invertTree1(TreeNode root){
        if(root == null ){
            return null;
        }
        Queue<TreeNode> treeNodeStack = new LinkedList<>();
        // 队列里有所有的节点，队列：先进先出
        treeNodeStack.offer(root);
        while(!treeNodeStack.isEmpty() || root != null){
            TreeNode cur = treeNodeStack.poll();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if(cur.left != null){
                treeNodeStack.offer(cur.left);
            }
            if(cur.right != null){
                treeNodeStack.offer(cur.right);
            }
        }
        return root;
    }
}
