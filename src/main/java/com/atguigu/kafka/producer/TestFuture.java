package com.atguigu.kafka.producer;

import java.util.concurrent.*;

public class TestFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("i=" + i);
                }
            }
        });
        future.get();//阻塞
        System.out.println("=====================");
        
        executor.shutdown();

    }
}
