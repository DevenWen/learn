package com.qpm.learn.algorithms.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class RKStringTest {

    @Test
    public void TestRKString() {
        assertTrue(RKString.RKString("abcd", "bc"));
        assertFalse(RKString.RKString("abcd", "adc"));
    }
}