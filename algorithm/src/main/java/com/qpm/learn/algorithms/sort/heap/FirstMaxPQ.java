package com.qpm.learn.algorithms.sort.heap;

/**
 * 初级实现<br>
 *     思路来自《算法》
 *
 * 无序数组版本
 *
 * @author kangqiang.w@gmail.com
 * @Date 2020-05-31
 **/
public class FirstMaxPQ implements MaxPQ {

    /**
     * 数组
     */
    private Comparable[] array;

    /**
     * 当前数组下标
     */
    private int index;

    public FirstMaxPQ() {
        this(16);
    }

    public FirstMaxPQ(int size) {
        this.array = new Comparable[size];
    }

    @Override
    public void insert(Comparable v) {
        this.array[index ++] = v;
    }

    @Override
    public Comparable max() {
        // 遍历，以获得最大值
        return array[maxIndex()];
    }

    /**
     * 获取最大值的下标
     *
     * @return
     */
    private int maxIndex() {
        int maxIndex = 0;
        for (int i = 0; i < index; i++) {
            if (array[maxIndex].compareTo(array[i]) < 0) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    @Override
    public Comparable delMax() {
        if (index == 0) return null;
        // 遍历，并与 index 下标的数据交换
        int max = maxIndex();
        swap(max, index-1);
        return array[--index];
    }

    private void swap(int i, int j) {
        if (i == j) return;
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public int size() {
        return index;
    }
}
