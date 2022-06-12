package com.pengxy.algorithm.AboutList;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/02/21 13:37
 */
public class ListNode {
    public int val;
    public ListNode next;
    ListNode(int x){
        val = x;
    }

    public ListNode() {
    }
    // 节点的构造函数(有两个参数)
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
