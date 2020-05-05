package cn.jiang.station.platform.service;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import sun.misc.VM;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ErrorDemo {


    public static void main(String[] args) {
        System.out.println("java.lang.OutOfMemoryError: Metaspace =======");
        int num = 0;
        while (true) {
            num++;
            try {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            } catch (Exception e) {
                System.out.println("创建类超出元空间限制：" + num);
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test6() {

    }

    static class OOMTest {
    }


    @Test
    public void test5() {
        System.out.println("java.lang.OutOfMemoryError: unable to creat new native thread =======");
        for (int i = 0; i >= 0; i++) {
            System.out.println("num--->" + i);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

    @Test
    public void test4() {
        System.out.println("java.lang.OutOfMemoryError: Direct buffer memory =======");
        long maxDirectMemory = VM.maxDirectMemory() / 1024l / 1024l;
        System.out.println("系统最大直接内存：" + maxDirectMemory + "M");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }

    @Test
    public void test3() {
        System.out.println("java.lang.OutOfMemoryError: GC overhead limit exceeded=======");
        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
                list.add(String.valueOf(i));
            }
        } catch (Exception e) {
            System.out.println("-----> i : " + i);
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void test2() {
        System.out.println("java.lang.OutOfMemoryError: Java heap space=======");
        byte[] bytes = new byte[30 * 1024 * 1024];
    }

    @Test
    public void test1() {
        System.out.println("java.lang.StackOverflowError=======");
        method01();
    }

    static void method01() {
        method01();
    }

}
