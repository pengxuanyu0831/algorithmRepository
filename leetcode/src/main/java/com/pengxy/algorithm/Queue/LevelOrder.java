package com.pengxy.algorithm.Queue;

import com.pengxy.algorithm.Tree.LeetCode.TreeNode;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/07/05 21:47
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root){
        // 队列
        Queue<TreeNode> queue = new LinkedList<>();
        if (Objects.nonNull(root)) {
            queue.add(root);
        }
        // 保存每层的结果
        List<List<Integer>> list = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        while (queue.size() > 0) {
            for (int i = 1; i < queue.size(); i++) {
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            list.add(temp);
        }
        return list;
    }
}
