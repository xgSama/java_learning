package com.xgsama.jvm.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * HeapOOM
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author xgSama
 * @date 2020/12/12 14:45
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}

/*
 * java.lang.OutOfMemoryError: Java heap space
 * Dumping heap to java_pid5708.hprof ...
 * Heap dump file created [28282699 bytes in 0.127 secs]
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3210)
 * 	at java.util.Arrays.copyOf(Arrays.java:3181)
 * 	at java.util.ArrayList.grow(ArrayList.java:265)
 * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
 * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
 * 	at java.util.ArrayList.add(ArrayList.java:462)
 * 	at com.xgsama.jvm.chapter2.HeapOOM.main(HeapOOM.java:21)
 */
