package com.qpm.learn.jvm;

/**
 * 动态对象年龄判定
 *
 * @author kangqiang.w
 * @Date 2020-04-19
 **/
public class TenuringThresholdTest implements Const {

    /**
     *
     * -verbose:gc
     * -Xms20M
     * -Xmx20M
     * -Xmn10M
     * -XX:+PrintGCDetails
     * -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=15
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * Heap
     *  PSYoungGen      total 9216K, used 7153K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     *   eden space 8192K, 87% used [0x00000007bf600000,0x00000007bfcfc5c0,0x00000007bfe00000)
     *   from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
     *   to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
     *  ParOldGen       total 10240K, used 8192K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     *   object space 10240K, 80% used [0x00000007bec00000,0x00000007bf400020,0x00000007bf600000)
     *  Metaspace       used 3193K, capacity 4556K, committed 4864K, reserved 1056768K
     *   class space    used 344K, capacity 392K, committed 512K, reserved 1048576K
     */

}
