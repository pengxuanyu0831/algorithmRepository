package com.pengxy.algorithm.AboutList;

/**
 * @program algorithm
 * @description: 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点,返回删除后的链表的头节点，迭代的方式
 * @author: pengxuanyu
 * @create: 2021/02/21 16:23
 */
public class DeleteNode1 {
    public static ListNode deletenode(ListNode head, int val) {
        ListNode temp = new ListNode();
        temp.next = head;
        ListNode cur = temp;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return temp.next;
    }
}
