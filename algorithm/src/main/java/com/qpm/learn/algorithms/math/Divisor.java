package com.qpm.learn.algorithms.math;

/**
 * 最大公约数算法
 *
 * @Author kangqiang.w
 * @Date 2020/6/3
 */
public class Divisor {

    /*
     * 最大公约数定理：
     * A1、A2 ... 的最大公约数满足结合律，可以使用分治算法进行计算
     */

    /**
     * 求最大公约数
     *
     * @param args
     * @return
     */
    public static int divisor(int[] args, int begin, int end) {
        if (begin == end) {
            return args[begin];
        }
        int mid = (end+begin) / 2;
        return divisor(divisor(args, begin, mid), divisor(args, mid + 1, end));
    }

    public static int divisor(int a, int b) {
        do {
            int c = a % b;
            if (c == 0) {
                return b;
            }
            a = b;
            b = c;
        } while (true);
    }


}
