package com.pengxy.algorithm.Tree.LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program algorithm
 * @description: 二叉树的层序遍历（难度：中等），
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * @author: pengxuanyu
 * @create: 2021/03/01 20:39
 */
public class levelOrder {
    public List<List<Integer>> levelOrder(TreeNode root){
        if(root == null) return new ArrayList<>();
        // 队列放各个节点
        Queue<TreeNode> queue1 = new LinkedList<>();
        // 放外层链表的
        List<List<Integer>> list1 = new ArrayList<>();
        // 先把所有节点放到队列里
        queue1.add(root);
        while(!queue1.isEmpty()){
            int count = queue1.size();
            // 一个链表放子树的路径
            List<Integer> list2 = new ArrayList<Integer>();
            // 只要队列里还有节点
            while(count > 0){
                TreeNode node = queue1.poll();
                // 把节点值放入到内层的链表里
                list2.add(node.val);
                // 只要还有左节点，就一直遍历
                if(node.left != null){
                    queue1.add(node.left);
                }
                if(node.right != null){
                    queue1.add(node.right);
                }

                count--;
            }
            list1.add(list2);
        }
        return list1;
    }
}
