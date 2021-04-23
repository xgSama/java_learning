package com.xgsama.jvm.zzm.chapter3;

/**
 * TestSerialGCAllocation
 * VM Args: -XX:+UseSerialGC
 *
 * @author xgSama
 * @date 2020/12/14 14:08
 */
public class TestSerialGCAllocation {
    private static final int _1MB = 1024 * 1024;

    /**
     * 对象优先在Eden分配
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *          -XX:+UseSerialGC
     *
     *[GC (Allocation Failure) [DefNew: 8130K->604K(9216K), 0.0135630 secs] 8130K->6748K(19456K), 0.0227277 secs] [Times: user=0.00 sys=0.00, real=0.02 secs]
     * Heap
     *  def new generation   total 9216K, used 4865K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   eden space 8192K,  52% used [0x00000000fec00000, 0x00000000ff029788, 0x00000000ff400000)
     *   from space 1024K,  58% used [0x00000000ff500000, 0x00000000ff597040, 0x00000000ff600000)
     *   to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     *  tenured generation   total 10240K, used 6144K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *    the space 10240K,  60% used [0x00000000ff600000, 0x00000000ffc00030, 0x00000000ffc00200, 0x0000000100000000)
     *  Metaspace       used 3225K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 349K, capacity 388K, committed 512K, reserved 1048576K
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *          -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
     *
     *Heap
     *  def new generation   total 9216K, used 2150K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   eden space 8192K,  26% used [0x00000000fec00000, 0x00000000fee19980, 0x00000000ff400000)
     *   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     *   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     *  tenured generation   total 10240K, used 4096K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *    the space 10240K,  40% used [0x00000000ff600000, 0x00000000ffa00010, 0x00000000ffa00200, 0x0000000100000000)
     *  Metaspace       used 3231K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];  //直接分配在老年代中
    }


    /**
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     *          -XX:+PrintTenuringDistribution -XX:+UseSerialGC
     *
     *[GC (Allocation Failure) [DefNew
     * Desired survivor size 524288 bytes, new threshold 1 (max 1)
     * - age   1:     908304 bytes,     908304 total
     * : 6338K->887K(9216K), 0.0045322 secs] 6338K->4983K(19456K), 0.0045753 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [DefNew
     * Desired survivor size 524288 bytes, new threshold 1 (max 1)
     * : 4983K->0K(9216K), 0.0011007 secs] 9079K->4979K(19456K), 0.0011213 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
     * Heap
     *  def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
     *   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     *   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     *  tenured generation   total 10240K, used 4979K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *    the space 10240K,  48% used [0x00000000ff600000, 0x00000000ffadcdb0, 0x00000000ffadce00, 0x0000000100000000)
     *  Metaspace       used 3231K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];  // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    /**
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     *          -XX:+PrintTenuringDistribution  -XX:+UseSerialGC
     *
     * [GC (Allocation Failure) [DefNew
     * Desired survivor size 524288 bytes, new threshold 1 (max 15)
     * - age   1:    1048576 bytes,    1048576 total
     * : 6594K->1024K(9216K), 0.0041617 secs] 6594K->5239K(19456K), 0.0042074 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [DefNew
     * Desired survivor size 524288 bytes, new threshold 15 (max 15)
     * : 5120K->0K(9216K), 0.0010571 secs] 9335K->5239K(19456K), 0.0010762 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * Heap
     *  def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
     *   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     *   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     *  tenured generation   total 10240K, used 5239K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *    the space 10240K,  51% used [0x00000000ff600000, 0x00000000ffb1dce0, 0x00000000ffb1de00, 0x0000000100000000)
     *  Metaspace       used 3231K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
     */
    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];   // allocation1+allocation2大于survivo空间一半
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }


    /**
     * VM Args: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
     *          -XX:+UseSerialGC
     * [GC (Allocation Failure) [DefNew: 8130K->631K(9216K), 0.0041597 secs] 8130K->4727K(19456K), 0.0041981 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [DefNew: 6932K->0K(9216K), 0.0010335 secs] 11028K->4723K(19456K), 0.0010585 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     */
    public static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }


    public static void main(String[] args) {
//        testAllocation();
//        testPretenureSizeThreshold();

//        testTenuringThreshold();
//        testTenuringThreshold2();
        testHandlePromotion();
    }

}
