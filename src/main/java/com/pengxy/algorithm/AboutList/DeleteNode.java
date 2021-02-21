package com.pengxy.algorithm.AboutList;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @program algorithm
 * @description: 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点,返回删除后的链表的头节点
 * 递归实现
 * @author: pengxuanyu
 * @create: 2021/02/21 13:37
 */
public class DeleteNode {
    public static ListNode deleteNode(ListNode head , int val) {
        if (head == null) {
            return head;
        }
        if (head.val == val) {
            return head.next;
        }
        return head.next = deleteNode(head.next, val);
    }
    public static List printf(ListNode head){
        List<ListNode> listNodeStack = new Stack<ListNode>();
        for(int i = 0; i < listNodeStack.size(); i ++){
            listNodeStack.add(head);
            System.out.println(head);
        }
        return listNodeStack;
    }



}
