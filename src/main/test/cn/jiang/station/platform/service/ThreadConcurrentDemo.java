package cn.jiang.station.platform.service;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 多线程并发测试
 * @Description TODO
 * @Created jiang
 */
public class ThreadConcurrentDemo {
    public static void main(String[] args) {
        //main方法执行
        System.out.println("定义semaphore信号量，进行多线程抢占资源");
        int num = 3;
        Semaphore semaphore = new Semaphore(num);
        for (int i = 0; i < 9; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "  线程占用资源中");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "  3秒后释放资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();

        }
    }

    @Test
    public void test3() {
        //main方法执行
        System.out.println("定义semaphore信号量，进行多线程抢占资源");
        int num = 3;
        Semaphore semaphore = new Semaphore(num);
        for (int i = 0; i < 9; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "  线程占用资源中");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "  3秒后释放资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();

        }
    }

    @Test
    public void test2() {
        System.out.println("定义cyclicBarrier计数器");
        int num = 6;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> {
            System.out.println("分线程执行完毕，交回主线程");
        });
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "分线程执行中");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

    @Test
    public void test1() throws InterruptedException {
        System.out.println("定义countDownLatch计数器");
        int cdNum = 6;
        CountDownLatch countDownLatch = new CountDownLatch(cdNum);
        for (int i = 0; i < cdNum; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "分线程结束，并计数");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "等待分线程全部结束后继续主线程");
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "主线程结束");
    }
}
