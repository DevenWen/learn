package com.qpm.learn.algorithms.string;

/**
 *
 */
public class BFString {


    /**
     *
     * @param mainStr
     * @param subStr
     * @return
     */
    public static boolean BFString(String mainStr, String subStr) {
        char[] main = mainStr.toCharArray();
        char[] sub = subStr.toCharArray();

        for (int i = 0; i < main.length; i++) {
            int i2 = i;
            int j;
            for (j = 0; j < sub.length; j++, i2++) {
                if (main[i2] == sub[j]) {
                    continue;
                } else {
                    break;
                }
            }
            if (j == sub.length) {
                return true;
            }
        }

        return false;
    }

}
