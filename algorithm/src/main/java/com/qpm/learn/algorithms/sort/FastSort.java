package com.qpm.learn.algorithms.sort;

import com.google.common.annotations.VisibleForTesting;

/**
 * 快速排序
 *
 * @Author kangqiang.w
 * @Date 2020/6/3
 */
public class FastSort {


    public static int[] sort(int[] list, int begin, int end) {
        if (begin >= end) {
            return list;
        }
        int index = partition(list, begin, end);
        sort(list, begin, index - 1);
        sort(list, index + 1, end);
        return list;
    }

    /**
     * 对数组，lo，hi 部分做切分，返回切分点
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int partition(int[] a, int lo, int hi) {
        // int[lo..j) ... int[j] ... int(j..hi]
        int i = lo;
        int j = hi + 1;
        int sign = a[lo];   // 把第一位记为哨兵
        while (true) {
            while (a[++i] < sign) {
                if (i == hi)
                    break;  // 遍历完毕
            }
            while (a[--j] > sign) {
                if (j == lo)
                    break; // 遍历完毕
            }
            if (i >= j) break;
            swap(a, i, j);
        }

        // 哨兵落位
        swap(a, lo, j);
        return j;
    }


    /**
     * 交换位置
     * @param list
     * @param a
     * @param b
     */
    @VisibleForTesting
    static void swap(int[] list, int a, int b) {
        int temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }

}
