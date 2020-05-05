package cn.jiang.station.platform.service;

import org.junit.Test;

import java.util.*;

public class Demo001 {
    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
//        Collections.synchronizedMap()


    }


    @Test
    public void test1() throws InterruptedException {
        String key;
        key = "test";
        int h;
//        int value = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        h = key.hashCode();
        System.out.println(h);
        System.out.println(h >>> 16);
        h = h ^ (h >>> 16);
        System.out.println(h);
    }
}
