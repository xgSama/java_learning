package com.xgsama.java.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * CompletableFutureDemo
 *
 * @author : xgSama
 * @date : 2022/2/20 17:51:30
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        AtomicLong num = new AtomicLong(0);
        CompletableFuture
                .supplyAsync(

                        () -> {
                            for (long i = 0; i < 1000; i++) {
                                num.getAndIncrement();

                                Thread.yield();
                            }
                            return num.get();
                        },
                        executor
                )
                .thenAcceptAsync(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) {
                        System.out.println(integer);
                    }
                })
                .thenComposeAsync(unused -> CompletableFuture.supplyAsync(() -> {
                            num.incrementAndGet();
                            System.out.println(num.get());
                            return num.toString();
                        })
                        , executor);

        executor.execute(() -> {
            System.out.println(num + "-<");
        });
    }
}
