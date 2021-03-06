package com.pengxy.algorithm.Stack;

/**
 * @program algorithm
 * @description: 判断字符串括号是否合法  （）一组为合法 （不使用栈）
 * @author: pengxuanyu
 * @create: 2021/03/06 16:15
 */
public class DoubleStack2 {
    public boolean isVaild(String str){
        if(str.isEmpty() || str.length() == 0){
            return true;
        }
        int count = 0;
        //char c = str.charAt();
        for(int i = 0 ;i <= str.length();i++){
            char c = str.charAt(i);
            if(c == '('){
                // 如果是(，标记+1
                count++;
            }else if(c == ')') {
                if(count <= 0){
                    return false;
                }
                // 如果标记)，标记-1
                --count;
            }
        }
        return count == 0;
    }
}
