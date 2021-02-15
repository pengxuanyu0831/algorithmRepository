package com.pengxy.algorithm.Tree.LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program algorithm
 * @description: 求树的深度，非递归方式
 * @author: pengxuanyu
 * @create: 2021/02/15 15:24
 */
public class MaxDepth1 {
    public static int maxDepth1(TreeNode root){
        if(root == null ){
            return  0;
        }
        int deep = 0;
        // 感觉树的操作很多都是转换成队列来考虑的
        Queue<TreeNode> treeNodeQueue = new LinkedList<TreeNode>();
        treeNodeQueue.add(root);
        if(!treeNodeQueue.isEmpty()){
            // int size = treeNodeQueue.size();
            deep++;
            for(int i = 0;i <= treeNodeQueue.size();i++){
                TreeNode node = treeNodeQueue.remove();
                if(node.left != null){
                    treeNodeQueue.add(node.left);
                }
                if(node.right != null){
                    treeNodeQueue.add(node.right);
                }
                return deep;
            }
        }
        return deep;
    }
}
