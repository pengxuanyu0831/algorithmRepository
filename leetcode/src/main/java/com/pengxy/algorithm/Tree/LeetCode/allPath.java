package com.pengxy.algorithm.Tree.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @program algorithm
 * @description: 所有路径 递归
 * @author: pengxuanyu
 * @create: 2021/02/28 16:27
 */
public class allPath {
    List<String> list = new ArrayList<>();
    public List<String> allPath(TreeNode root){

        if(root == null){
            return null;
        }
        resolve(root , "",list);
        return list;
    }

    public void resolve(TreeNode root,String str,List<String> list){
        if(root == null) return;
        // 每个节点都要做一件事，就是打印自己的值，拼到str中
        str += root.val;
        // 走到底则将节点值放入List中
        if(root.right == null && root.left == null) {
            list.add(str);
        }else{
            resolve(root.left,str+ "->",list);
            resolve(root.right,str+"->",list);
        }
    }
}
