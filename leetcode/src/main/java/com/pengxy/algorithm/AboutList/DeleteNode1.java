package com.pengxy.algorithm.AboutList;

/**
 * @program algorithm
 * @description: 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点,返回删除后的链表的头节点，迭代的方式
 * @author: pengxuanyu
 * @create: 2021/02/21 16:23
 */
public class DeleteNode1 {
    public static ListNode deletenode(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        // 需要一个临时节点作为头结点，否则第一个数就是要删的节点的情况，会空指针
        ListNode tempnode = new ListNode();
        tempnode.next = head;
        ListNode pre = tempnode;
        ListNode cur = head;
        // 只要节点不为空，就要循环
        while(cur != null){
            ListNode temp = cur.next;
            // 当数值等于给的值时
            if(cur.val == val){
                // 前一节点的指针，略过相等的值得指针，指向相等数下一位的地址
                pre.next = temp;
                break;
            }
            // 要一直循环，往下走，当前变为上一个，下一个变为当前
            pre = cur;
            cur = cur.next;
        }
        // 返回临时节点的下一位，这里是因为临时节点本身就不存在，仅作为一个参照物
        return tempnode.next;
    }
}
