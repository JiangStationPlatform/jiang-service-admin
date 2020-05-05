package cn.jiang.station.platform.service;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ReferenceDemo {
    public static void main(String[] args) {

    }

    @Test
    public void test4() {
        System.out.println("虚引用");
        Object object = new Object();
        ReferenceQueue<Object> ref = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, ref);
        System.out.println("object: " + object);
        System.out.println("虚引用: " + phantomReference.get());
        System.out.println("应用队列：" + ref.poll());
        System.out.println("垃圾回收====");
        object = null;
        System.gc();
        System.out.println("object: " + object);
        System.out.println("虚引用: " + phantomReference.get());
        System.out.println("应用队列：" + ref.poll());
    }

    @Test
    public void test3() {
        System.out.println("弱引用");
        Object strong = new Object();
        WeakReference<Object> weak = new WeakReference<>(strong);
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        String key = "name";
        String value = "zhangsan";
        weakHashMap.put(key, value);
        System.out.println("strong：" + strong);
        System.out.println("soft：" + weak.get());
        System.out.println("weakHashMap：" + weakHashMap);
        strong = null;
        System.gc();
        System.out.println("strong：" + strong);
        System.out.println("soft：" + weak.get());
        System.out.println("weakHashMap：" + weakHashMap);
    }

    @Test
    public void test2() {
        System.out.println("软引用");
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } finally {
            System.out.println("strong：" + strong);
            System.out.println("soft：" + soft.get());
        }
    }

    @Test
    public void test1() {
        System.out.println("强引用");
        Object strong = new Object();
        Object strong2 = strong;
        strong = null;
        System.gc();
        System.out.println("strong：" + strong);
        System.out.println("strong2：" + strong2);
    }
}
