package com.xgsama.common.lock;

import java.util.List;

/**
 * DistributedLock
 *
 * @author : xgSama
 * @date : 2021/11/13 19:38:35
 */
public interface DistributedLock {

    boolean lock();

    boolean tryLock() throws Exception ;

    boolean unlock();

    boolean await() throws Exception;
}
