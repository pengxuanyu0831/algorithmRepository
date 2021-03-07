package com.pengxy.algorithm.Stack;

import java.util.Stack;

/**
 * @program algorithm
 * @description: 所有的鱼都同时开始游动，每次按照鱼的方向，都游动一个单位距离,当方向相对时，大鱼会吃掉小鱼
 * @author: pengxuanyu
 * @create: 2021/03/07 13:13
 */
public class EattingFish {
    public int solution(int[] fishSize,int[] fishDir){
        final int fishNumber = fishSize.length;
        if(fishNumber <= 1){
            return fishNumber;
        }
        // 向左游为1
        final int left = 1;
        final int right = 0;
        Stack<Integer> t = new Stack<>();
        for(int i = 0;i <= fishSize.length ; i++){
            // 一个记录当前鱼状态的变量
            int curFishNumber = fishSize[i];
            int curFishDir = fishDir[i];
            boolean isEat = false;
            // 只要栈中鱼向右，且 当前鱼向左，就有相遇的可能
            while(!t.isEmpty() && fishDir[t.peek()] == right && curFishDir == left){
                // 如果栈顶peek出来的鱼 比 当前鱼 大
                if(fishSize[t.peek()] > curFishDir){
                    // 大鱼吃掉小鱼
                    isEat = true;
                    break;
                }
                // 反之，如果当前鱼比栈中鱼大，那么大鱼吃掉栈中鱼
                t.pop();
            }
            // 如果当前鱼没有被吃掉
            if(!isEat){
                t.push(i);
            }
        }
        return t.size();
    }
}
