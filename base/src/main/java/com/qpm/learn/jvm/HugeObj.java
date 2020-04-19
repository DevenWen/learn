package com.qpm.learn.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * 大对象生成进入old区
 *
 *
 * @author kangqiang.w
 * @Date 2020-04-19
 **/
public class HugeObj {

    /**
     * -Xmx30M -Xms30M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1024000
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<Integer, byte[]> m = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            byte[] b = new byte[1024 * 1024];
            m.put(i, b);
        }
    }

    /* 没设置：-XX:PretenureSizeThreshold=1024000
    Heap
 def new generation   total 9216K, used 7665K [0x00000007be200000, 0x00000007bec00000, 0x00000007bec00000)
  eden space 8192K,  93% (使用eden区) used [0x00000007be200000, 0x00000007be97c548, 0x00000007bea00000)
  from space 1024K,   0% used [0x00000007bea00000, 0x00000007bea00000, 0x00000007beb00000)
  to   space 1024K,   0% used [0x00000007beb00000, 0x00000007beb00000, 0x00000007bec00000)
 tenured generation   total 20480K, used 0K(未使用old区) [0x00000007bec00000, 0x00000007c0000000, 0x00000007c0000000)
   the space 20480K,   0% used [0x00000007bec00000, 0x00000007bec00000, 0x00000007bec00200, 0x00000007c0000000)
 Metaspace       used 3178K, capacity 4556K, committed 4864K, reserved 1056768K
  class space    used 343K, capacity 392K, committed 512K, reserved 1048576K
     */

    /* 设置：-XX:PretenureSizeThreshold=1024000 1M 的对象直接写入老年代
    Heap
 def new generation   total 9216K, used 2545K [0x00000007be200000, 0x00000007bec00000, 0x00000007bec00000)
  eden space 8192K,  31%(新生代小了很多) used [0x00000007be200000, 0x00000007be47c4f8, 0x00000007bea00000)
  from space 1024K,   0% used [0x00000007bea00000, 0x00000007bea00000, 0x00000007beb00000)
  to   space 1024K,   0% used [0x00000007beb00000, 0x00000007beb00000, 0x00000007bec00000)
 tenured generation   total 20480K, used 5120K(直接使用了old区) [0x00000007bec00000, 0x00000007c0000000, 0x00000007c0000000)
   the space 20480K,  25% used [0x00000007bec00000, 0x00000007bf100050, 0x00000007bf100200, 0x00000007c0000000)
 Metaspace       used 3177K, capacity 4556K, committed 4864K, reserved 1056768K
  class space    used 343K, capacity 392K, committed 512K, reserved 1048576K
     */

}
