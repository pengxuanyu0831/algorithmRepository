package com.pengxy.algorithm.AboutList;

import com.pengxy.algorithm.Tree.LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/05/23 17:30
 */
public class QueueForTree {
    public List<List<Integer>> queueForTree(TreeNode node) {
        Queue<TreeNode> tree = new LinkedList<>();
        if (node != null) {
            tree.offer(node);
        }
        List<List<Integer>> record = new LinkedList<>();
        if (tree.size() > 0) {
            // 遍历当前层
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < tree.size(); i++) {
                TreeNode curr = tree.poll();
                temp.add(node.val);
                if (curr.left != null) {
                    tree.offer(curr.left);
                }

                if (curr.right != null) {
                    tree.offer(curr.right);
                }
            }
            record.add(temp);
        }
        return record;
    }
}
