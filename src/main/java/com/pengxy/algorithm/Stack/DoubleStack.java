package com.pengxy.algorithm.Stack;

import java.util.Stack;

/**
 * @program algorithm
 * @description: 判断字符串括号是否合法  （）一组为合法
 * @author: pengxuanyu
 * @create: 2021/03/06 12:27
 */
public class DoubleStack {
    public boolean isVaild(String str){
        if(str == null || str.length() == 0) {
            return  true;
        }

        if(str.length() % 2 == 1){
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for(int i = 0 ;i< str.length();i++ ){
            char c = str.charAt(i);
            if(c == '('){
                stack.push(c);
            } else if(c == ')'){
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}

