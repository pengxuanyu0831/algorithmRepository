package com.pengxy.algorithm.something;

import java.util.Scanner;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/03/24 09:17
 */
public class Star {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer numn = scanner.nextInt();
        System.out.println("请输入行数：" + numn);

        // 循环控制行数
        for(int i = 1;i <= numn; i++){
            // 控制空格的树木
            for(int k = 1;k < numn - i;k++){
                System.out.print(" ");
            }
            for(int j = 1;j <= 2 * i - 1;j++){
                System.out.print("*");
            }
            System.out.println();
        }



    }
}
