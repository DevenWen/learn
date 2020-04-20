package com.qpm.learn.jvm;

/**
 * 关于失败担保机制的测试
 *
 * @author kangqiang.w
 * @Date 2020-04-19
 **/
public class PromotionFailureTest {

    public static void main(String[] args) {
        byte[] allcation1, allcation2;
        allcation1 = new byte[28000 * 1024];
        allcation2 = new byte[12000 * 1024];

    }

    /**
     * 失败分配担保：在进行 MinorGC 的时候，假如发现某个对象 Survivor 区无法保存，就会
     * 通过失败分配担保机制，直接把对象转移到老年代中去。
     */

    /**
     * Heap
     *  PSYoungGen      total 38400K, used 31994K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
     *   eden space 33280K, 96% used [0x0000000795580000,0x00000007974be960,0x0000000797600000)
     *   from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
     *   to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
     *  ParOldGen       total 87552K, used 0K [0x0000000740000000, 0x0000000745580000, 0x0000000795580000)
     *   object space 87552K, 0% used [0x0000000740000000,0x0000000740000000,0x0000000745580000)
     *  Metaspace       used 3176K, capacity 4556K, committed 4864K, reserved 1056768K
     *   class space    used 343K, capacity 392K, committed 512K, reserved 1048576K
     */

}
