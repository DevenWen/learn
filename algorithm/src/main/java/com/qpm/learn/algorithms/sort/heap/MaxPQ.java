package com.qpm.learn.algorithms.sort.heap;

/**
 *
 * 优先队列接口
 *
 * @author kangqiang.w@gmail.com
 * @Date 2020-05-31
 **/
public interface MaxPQ<T extends Comparable<T>> {

    // 忽略构造函数

    /**
     * 向优先级队列插入一个元素
     * @param v 元素
     */
    void insert(T v);

    /**
     * 返回最大值
     *
     * @return
     */
    T max();

    /**
     * 删除并返回最大值
     *
     * @return
     */
    T delMax();

    /**
     * 返回队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 返回优先队列中的元素个数
     * @return
     */
    int size();
}
