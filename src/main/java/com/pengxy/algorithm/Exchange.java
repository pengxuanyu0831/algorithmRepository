package com.pengxy.algorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/01/24 20:06
 */
public class Exchange{
    public static String change(String str){
        if(str == null || str.length() == 0) {
            return str;
        }
        // 栈 先进后出
        Stack<Character> stringStack = new Stack<>();
        // 把字符串转化成数组
        char[] arrary = str.toCharArray();
        for(Character c:arrary){
            stringStack.push(c);
        }
        for(int i = 0;i < str.length();i++){
            arrary[i] = stringStack.pop();
        }
        return new String(arrary);
    }
    public static void main(String[] args) {
/*        Scanner scanner = new Scanner(System.in);
        String str = null;
        char[] arrary = str.toCharArray();
        System.out.println("请输入字符串:");
        str = scanner.nextLine();

        for(int i = 0;i < str.length();i++){
            arrary[i] = str.charAt(str.length()-1-i);
        }*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串: ");
        String str1 = scanner.nextLine();
        System.out.println("你输入的字符串为:"+ str1);
        String result = change(str1);
        System.out.println("翻转的结果为: "+result);



    }
}
