package com.pengxy.algorithm.AboutList;

/**
 * @program algorithm
 * @description: 链表删除重复值，只保留一个
 * @author: pengxuanyu
 * @create: 2021/03/01 21:54
 */
public class deleteDuplicates {
    public ListNode deleteDuplicates(ListNode head){
        if(head == null){
            return head;
        }
        if(head.next == null){
            return head;
        }
        ListNode temp = new ListNode();
        while(head != null){
            if(temp.val == temp.next.val){
                temp.next = temp.next.next;
            }else{
                temp = temp.next;
            }
        }
        return head;
    }
}
