package com.qpm.learn.algorithms.string;

/**
 * KMP 算法
 */
public class KMPString {



    public static int KMPSearchSubString(String mainStr, String subStr) {
        char[] main = mainStr.toCharArray();
        char[] sub = subStr.toCharArray();

        int[] next = next(sub);  //
        int j = 0;
        for (int i = 0; i < main.length; i++) {
            while (j > 0 && main[i] != sub[j]) {
                // 字符串直接滑动，next数组认为可以直接从 next[j - 1] + 1 继续匹配。
                j = next[j - 1] + 1;
            }
            if (main[i] == sub[j]) {
                ++j;
            }
            if (j == sub.length) {
                return i - sub.length + 1;
            }
        }
        return -1;
    }


    /**
     * 计算失效函数 next 数组
     *
     * @param str
     * @return
     */
    private static int[] next(char[] str) {
        int m = str.length;
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && str[k + 1] != str[i]) {
                k = next[k];
            }
            if (str[k + 1] == str[i]) {
                ++k;
            }
            next[i] = k;
        }

        return next;
    }

}
