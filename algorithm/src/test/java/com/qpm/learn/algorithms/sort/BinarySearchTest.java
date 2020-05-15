package com.qpm.learn.algorithms.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest {

    @Test
    public void rank() {
        int[] list = {1,2,3,4,5};
        assertEquals(-1, BinarySearch.rank(0, list));
        assertEquals(0, BinarySearch.rank(1, list));
        assertEquals(2, BinarySearch.rank(3, list));
        assertEquals(4, BinarySearch.rank(5, list));
    }
}