package com.qpm.learn.algorithms.sort;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastSortTest extends TestCase {

    /**
     * 生成随机数字串
     * @return
     */
    private int[] shuffleList() {
        List<Integer> list = Arrays.asList(0,1,2,3,4,5,6,7,8);
        Collections.shuffle(list);
        final int[] arrays = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrays[i] = list.get(i);
        }
        return arrays;
    }

    /**
     * 测试快排的 partition
     */
    public void testPartition() {
        int[] arrays = shuffleList();
        int index = FastSort.partition(arrays, 0, arrays.length-1);
        assertEquals(index, arrays[index]);
        for (int i = 0; i < index; i++) {
            assertTrue(arrays[i] <= arrays[index]);
        }
        for (int i = index + 1; i < arrays.length; i++) {
            assertTrue(arrays[i] >= arrays[index]);
        }
    }


    public void testSwap() {
        int[] a = new int[]{1,2};
        FastSort.swap(a, 0, 1);
        assertEquals(2, a[0]);
        assertEquals(1, a[1]);
    }

    /**
     * 测试快排
     */
    public void testSort() {
        int[] list = shuffleList();
        FastSort.sort(list, 0, list.length - 1);
        for (int i = 0; i < list.length; i++) {
            assertEquals(i, list[i]);
        }
    }
}