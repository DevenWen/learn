package com.qpm.learn.algorithms.math;

import junit.framework.TestCase;

public class DivisorTest extends TestCase {

    /**
     * 测试多个参数的最大公约数
     */
    public void testDivisor() {
        int[] args = new int[]{3, 18, 6, 27};
        assertEquals(3, Divisor.divisor(args, 0, args.length - 1));

    }

    /**
     * 测试两个参数的最大公约数
     */
    public void testTestDivisor() {

        assertEquals(9, Divisor.divisor(18, 27));
        assertEquals(2, Divisor.divisor(4, 2));
        assertEquals(1, Divisor.divisor(4, 1));
        assertEquals(1, Divisor.divisor(4, 3));

    }
}