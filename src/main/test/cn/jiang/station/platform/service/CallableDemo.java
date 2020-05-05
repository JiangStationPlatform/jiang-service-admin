package cn.jiang.station.platform.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    /**
     * callable接口实现线程调用
     *
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> oft = new FutureTask<>(new MyThread());
        new Thread(oft, "1").start();
        int num1 = 100;
        System.out.println(Thread.currentThread().getName() + "\t 获取Callable线程结果，未完成则堵塞等待");
        int num2 = oft.get();
        System.out.println(Thread.currentThread().getName() + "\t 处理结果： \t" + (num1 + num2));
    }
    static class MyThread implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "\t Callable进行处理中！");
            TimeUnit.SECONDS.sleep(3);
            return 100;
        }
    }

}
