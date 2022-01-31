package com.pengxy.algorithm.Stack;

import java.time.temporal.ChronoUnit;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @program algorithm
 * @description: 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效
 * @author: pengxuanyu
 * @create: 2021/03/06 16:22
 */
public class DoubleCheck {
    public boolean DoubleCheck(String str){
        if(str.length() == 0 ||str.isEmpty() ){
            return true;
        }
        Deque<Character> deque = new LinkedList<>();
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            deque.push(c);
            Character characterL = deque.getFirst();
            Character characterR = deque.getLast();
            if(characterL == '(' && characterR ==')'){
                return true;
            }else if(characterL == '{' && characterR == '}'){
                return true;
            }else if(characterL == '[' && characterR == ']'){
                return true;
            }
        }
        return deque.isEmpty();
    }
}
