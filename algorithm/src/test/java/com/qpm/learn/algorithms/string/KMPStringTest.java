package com.qpm.learn.algorithms.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class KMPStringTest {

    @Test
    public void KMPSearchSubString() {
        assertTrue(KMPString.KMPSearchSubString("abababababababbbbaa1", "abbbbaa1") > 0);
        assertTrue(KMPString.KMPSearchSubString("abcd", "adc") == -1);
    }
}