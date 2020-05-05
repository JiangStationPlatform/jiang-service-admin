package cn.jiang.station.platform.service;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程池threadpoolexecutor
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(cpuNum),
                Executors.defaultThreadFactory()
//                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
//                new ThreadPoolExecutor.DiscardPolicy()
        );
        for (int i = 0; i < 15; i++) {
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"\t 进入业务处理");
            });
        }
        threadPool.shutdown();
    }

    @Test
    public void test1() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 进入业务处理");
            });
        }
        threadPool.shutdown();
    }

}
