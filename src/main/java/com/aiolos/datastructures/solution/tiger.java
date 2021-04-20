package com.aiolos.datastructures.solution;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Aiolos
 * @date 2020-01-27 18:27
 */
public class tiger {

    public static void main(String[] args) {

        int[][] arr = {{1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 0}, {1, 2, 3, 4, 0, 0}, {1, 2, 3, 0, 0, 0},
                {1, 2, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0}};
        BigDecimal[] money = {new BigDecimal("0.88"), new BigDecimal("1.88"),
                            new BigDecimal("2.88"), new BigDecimal("5.88"),
                            new BigDecimal("8.88"), new BigDecimal("18.88")};
        BigDecimal coin = new BigDecimal("2.99");
        BigDecimal sum = BigDecimal.ZERO;
        int count = 100;
        for (int i = 0; i < count; i++) {
            sum = sum.subtract(coin);
            System.out.print("第" + (i + 1) + "次游戏开始，");
            int first = new Random().nextInt(6);
            System.out.print("第一次投掷骰子：" + (first + 1) + " ；");
            int second = new Random().nextInt(arr[first].length);
            System.out.print("第二次投掷骰子：" + (second + 1) + " ；");
            if (arr[first][second] == 0) {
                System.out.print(" 本次游戏没有中奖；");
            } else {
                System.out.print("恭喜中奖：" + money[first] + "；");
                sum = sum.add(money[first]);
            }
            System.out.println("当前余额：" + sum);
        }
    }
}
