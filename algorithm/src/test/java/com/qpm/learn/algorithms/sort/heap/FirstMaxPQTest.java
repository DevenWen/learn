package com.qpm.learn.algorithms.sort.heap;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FirstMaxPQTest {

    FirstMaxPQ pq;

    @Before
    public void before() {
        pq = new FirstMaxPQ();
    }

    @Test
    public void insert() {
        pq.insert(1);
        assertEquals(1, pq.size());
    }

    @Test
    public void max() {
        pq.insert(1);
        pq.insert(2);
        pq.insert(10);
        pq.insert(4);
        assertEquals(10, pq.max());
    }

    @Test
    public void delMax() {
        pq.insert(1);
        pq.insert(2);
        pq.insert(10);
        pq.insert(4);
        assertEquals(10,pq.max());
        assertEquals(10,pq.delMax());
        assertEquals(3,pq.size());
        assertEquals(4,pq.delMax());
    }

    @Test
    public void isEmpty() {
        assertEquals(true, pq.isEmpty());
        pq.insert(1);
        assertEquals(false, pq.isEmpty());
        pq.delMax();
        assertEquals(true, pq.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(0, pq.size());
        pq.insert(1);
        assertEquals(1, pq.size());
        pq.insert(2);
        assertEquals(2, pq.size());
        pq.insert(10);
        assertEquals(3, pq.size());
        pq.delMax();
        assertEquals(2, pq.size());
    }
}