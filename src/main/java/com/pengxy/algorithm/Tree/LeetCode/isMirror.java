package com.pengxy.algorithm.Tree.LeetCode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @program algorithm
 * @description: 判断一颗二叉树是不是堆成的   迭代的实现
 * @author: pengxuanyu
 * @create: 2021/02/14 16:03
 */
public class isMirror {
    public boolean isMirror(TreeNode root){
        if(root == null){
            return true;
        }

        Queue<TreeNode> treeQueue = new LinkedList<>();
        treeQueue.offer(root.left);
        treeQueue.offer(root.right);

        while (!treeQueue.isEmpty()){
            TreeNode node1 = treeQueue.poll();
            TreeNode node2 = treeQueue.poll();
            if(node1 == null && node2 == null){
                continue;
            }
            if(node1 == null || node2 == null || node1.val != node2.val){
                return false;
            }
            treeQueue.offer(node1.left);
            treeQueue.offer(node2.right);

            treeQueue.offer(node1.right);
            treeQueue.offer(node2.left);
        }
        return true ;
    }
}
