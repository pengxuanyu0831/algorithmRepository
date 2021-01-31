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
   class Node{
       private int Data;
       // 指针
       private Node Next;

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
       if(head ==null){
           return head;
       }
       // 指针指向pre
       Node pre = head;
       Node cur = head.getNext();
       Node tmp;
       while(cur != null){
           tmp = cur.getNext();
           cur.setNext(pre);
           pre = cur;
           cur = tmp;
       }
       head.setNext(null);
       return pre;

    }
}
