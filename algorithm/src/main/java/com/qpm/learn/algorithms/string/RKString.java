package com.qpm.learn.algorithms.string;

public class RKString {

    /**
     *
     * RK 算法的全称叫 Rabin-Karp 算法，是由它的两位发明者 Rabin 和 Karp 的名字来命名
     *
     * @param mainStr
     * @param subStr
     * @return
     */
    public static boolean RKString(String mainStr, String subStr) {
        char[] main = mainStr.toCharArray();
        char[] sub = subStr.toCharArray();

        int subHashCode = buildHash(sub, 0, sub.length);

        for (int i = 0; i < main.length; i++) {
            // 计算子串的code
            if (i + sub.length > main.length) break;

            int mainCode = buildHash(main, i, i+sub.length);
            if (mainCode == subHashCode) {
                // 遇到子串code一致时，会判断是否匹配。这里需要考虑冲突问题
                int j = i;
                for (; j < sub.length + i; j++) {
                    if (main[j] == sub[j-i]) {
                        continue;
                    }
                    break;
                }
                if (j-i == sub.length) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * hash 冲突的关键
     * @param str
     * @param begin
     * @param end
     * @return
     */
    private static int buildHash(char[] str, int begin, int end) {
        int code = 0;
        for (int i = begin; i < end; i++) {
            code += str[i]*26;
        }
        return code;
    }

}
