package com.pengxy.algorithm.Reverse;

import javax.swing.*;
import java.util.List;

/**
 * @program algorithm
 * @description: 指针反转的方法
 * @author: pengxuanyu
 * @create: 2021/01/24 22:06
 */
public class ReverstList {
   static class Node{
       int Data;
       // 指针
       Node Next;

       public int getData() {
           return Data;
       }

       public void setData(int data) {
           this.Data = data;
       }

       public Node getNext() {
           return Next;
       }

       public void setNext(Node next) {
           this.Next = next;
       }
   }



    public static Node reverseList(Node head){
       Node pre = null;
       Node next = null;
       while(head != null){
           // 1 记录下一节点位置
           next = head.Next;
           // 2 断开本节点指向下一节点的指针，重新指向pre
           head.Next = pre;
           // 3 用pre指针保存当前节点
           pre = head;
           // 4 head 指向next保存的下一节点
           head = next;
       }
       // 当head指向Null后，pre指向最后一个不为空的节点，故而返回pre
       return pre;
    }
}
