package cn.jiang.station.platform.service;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {
        String lock1 = "lock1";
        String lock2 = "lock2";
        new Thread(new DeadLock(lock1, lock2), "A").start();
        new Thread(new DeadLock(lock2, lock1), "B").start();
    }

    static class DeadLock implements Runnable {
        private String val1;
        private String val2;

        public DeadLock(String val1, String val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        @Override
        public void run() {
            synchronized (val1) {
                System.out.println(Thread.currentThread().getName() + "\t 当前持有" + val1 + "\t 准备获取" + val2);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (val2) {
                    System.out.println(Thread.currentThread().getName() + "\t 已获取" + val2);
                }
            }
        }
    }


}
