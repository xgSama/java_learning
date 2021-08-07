package com.xgsama.jvm.innerlock;

import com.xgsama.common.crazy.util.ByteUtil;
import com.xgsama.common.crazy.util.Print;
import org.openjdk.jol.info.ClassLayout;

/**
 * ObjectLock
 *
 * @author : xgSama
 * @date : 2021/8/4:52:13
 */
public class ObjectLock {
    private Integer amount = 0;

    public void increase() {
        synchronized (this) {
            amount++;
        }
    }

    /**
     * 输出十六进制、小端模式的hashCode
     */
    public String hexHash() {
        int hashCode = this.hashCode();
        byte[] hashCode_LE = ByteUtil.int2Bytes_LE(hashCode);
        return ByteUtil.byteToHex(hashCode_LE);
    }

    /**
     * 输出二进制、小端模式的hashCode
     */
    public String binaryHash() {
        int hashCode = this.hashCode();
        byte[] hashCode_LE = ByteUtil.int2Bytes_LE(hashCode);
        StringBuffer buffer = new StringBuffer();
        for (byte b : hashCode_LE) {
            buffer.append(ByteUtil.byte2BinaryString(b));
            buffer.append(" ");
        }

        return buffer.toString();
    }

    /**
     * 输出十六进制、小端模式的ThreadId
     *
     * @return threadID_LE
     */
    public String hexThreadId() {
        //当前线程的 threadID，JAVA 默认为大端模式
        long threadID = Thread.currentThread().getId();
//        threadID=threadID<<2;
        //转成小端模式的字节数组
        byte[] threadID_LE = ByteUtil.long2bytes_LE(threadID);

        //转成十六进制形式的字符串
        return ByteUtil.byteToHex(threadID_LE);
    }

    /**
     * 输出二进制、小端模式的ThreadId
     *
     * @return threadID_LE
     */
    public String binaryThreadId() {
        //当前线程的 threadID，JAVA 默认为大端模式
        long threadID = Thread.currentThread().getId();
//        threadID=threadID<<2;
        //转成小端模式的字节数组
        byte[] threadID_LE = ByteUtil.long2bytes_LE(threadID);

        StringBuffer buffer = new StringBuffer();
        for (byte b : threadID_LE) {
            //转成二进制形式的字符串
            buffer.append(ByteUtil.byte2BinaryString(b));
            buffer.append(" ");
        }
        return buffer.toString();
    }

    public void printSelf() {
        // 输出十六进制、小端模式的hashCode
        Print.fo("lock hexHash= " + hexHash());

        // 输出二进制、小端模式的hashCode
        Print.fo("lock binaryHash= " + binaryHash());

        //通过JOL工具获取this的对象布局
        String printable = ClassLayout.parseInstance(this).toPrintable();
        //输出对象布局
        Print.fo("lock = " + printable);

    }

    public void printObjectStruct() {

        String printable = ClassLayout.parseInstance(this).toPrintable();

        //当前线程的 threadID，JAVA 默认为大端模式
//        long threadID = Thread.currentThread().getId();
//         Print.fo("current threadID_BE= " + threadID);
//        Print.fo("current threadID_LE= " + hexThreadId());
//        Print.fo("current binary threadID_LE= " + binaryThreadId());
        Print.fo("lock = " + printable);
        // LockSupport.parkNanos(100);

    }

}
