package com.xgsama.jvm.chapter2;

import java.util.HashSet;
import java.util.Set;

/**
 * RuntimeConstantPoolOOM
 * VM Args: -XX:PermSize=6M -XX:MaxPermSize=6M
 *
 * @author xgSama
 * @date 2020/12/12 15:15
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String > set = new HashSet<String>();

        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
