package com.qpm.learn.algorithms.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class BFStringTest {

    @Test
    public void TestBFString() {
        assertTrue(BFString.BFString("abcd", "abc"));
        assertFalse(BFString.BFString("abcd", "adc"));
    }
}