package com.pengxy.algorithm.Stack;

import java.util.Stack;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/05/23 16:38
 */
public class IncrStack {
    public static Integer[] incrStack(Integer[] ints) {
        // 单调栈，放的是下标，比较的是数值，每次放一个数，都遍历左侧，比左侧小，则弹出左侧值
        Stack<Integer> stack = new Stack<>();
        // 记录栈
        Integer[] record = new Integer[ints.length];
        for (int i = 0; i < ints.length; i++) {
            // 只要栈没弹空，栈中元素比数组对应的数值小
            while (!stack.isEmpty() && ints[stack.peek()] < ints[i]) {
                stack.push(i);
            }
            stack.pop();
            record[stack.peek()] = i;
        }

        while (!stack.isEmpty()) {
            record[stack.peek()] = -1;
            stack.pop();
        }
        return record;
    }

}
