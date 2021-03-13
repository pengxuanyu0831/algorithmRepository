package com.pengxy.algorithm.Reverse;
import com.pengxy.algorithm.Reverse.ReverstList;

/**
 * @program algorithm
 * @description: 递归实现翻转单向链表
 * @author: pengxuanyu
 * @create: 2021/03/13 12:32
 */
public class ReversListNode {
    // 递归反转的目的在于 {给定一个头结点head ，返回翻转后的头结点last}
    // 不要纠结于实现，要着眼于递归的目的
    static ReverstList.Node reversNode(ReverstList.Node head){
        if(head == null || head.getNext() ==null) {
            return head;
        }
        ReverstList.Node last = reversNode(head.Next);
        head.Next.Next = head;
        head.Next = null;
        return last;

    }
    public static void main(String[] args) {

    }
}
