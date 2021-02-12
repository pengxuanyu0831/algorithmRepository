package com.pengxy.algorithm.Tree;

import java.util.ArrayList;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/02/12 16:46
 */
public class isSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q){
        ArrayList<TreeNode> listp = new ArrayList<>();
        ArrayList<TreeNode> listq = new ArrayList<>();
        getNode(q,listq);
        getNode(p,listp);
        return listp.equals(listq);
    }

    public void getNode(TreeNode node, ArrayList tempList){
        if(node == null ){
            tempList.add(null);
            return;
        }
        // 先序遍历
        tempList.add(node.val);
        getNode(node.left,tempList);
        getNode(node.right,tempList);
    }

}
