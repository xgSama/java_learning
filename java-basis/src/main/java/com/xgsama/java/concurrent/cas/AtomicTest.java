package com.xgsama.java.concurrent.cas;

import com.xgsama.common.crazy.util.Print;
import com.xgsama.common.model.User;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * AtomicTest
 *
 * @author : xgSama
 * @date : 2021/8/8 11:12:20
 */
public class AtomicTest {

    @Test
    public void atomicIntegerTest() {
        int tempValue = 0;

        AtomicInteger i = new AtomicInteger(0);

        tempValue = i.getAndSet(3);

        Print.fo("tempValue：" + tempValue + "; i = " + i.get());

        tempValue = i.getAndIncrement();
        Print.fo("tempValue：" + tempValue + "; i = " + i.get());

    }

    @Test
    public void atomicReferenceTest() {

        AtomicReference<User> userRef = new AtomicReference<>();
        User user = new User("1", "张三", 10);
        userRef.set(user);
        Print.tco("userRef is : " + userRef.get());

        User updateUser = new User("2", "李四", 23);
        boolean success = userRef.compareAndSet(user, updateUser);
        Print.tco("cas result is : " + success);
        Print.tco("after cas, userRef is : " + userRef.get());


        // 属性更新
        AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        Print.tco(updater.getAndIncrement(user));
        Print.tco(updater.getAndAdd(user, 100));
        Print.tco(updater.get(user));

    }
}
