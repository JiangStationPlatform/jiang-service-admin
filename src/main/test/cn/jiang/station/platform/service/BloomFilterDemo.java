package cn.jiang.station.platform.service;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.text.NumberFormat;
import java.util.*;

/**
 * @Description TODO
 * @Created jiang
 */
public class BloomFilterDemo {
    private static final int insertions = 1000000;
    public static void main(String[] args) {
        System.out.println("初始化布隆过滤器，大小为100w，误判率为0.03");
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions,0.03);
        System.out.println("创建示例集合");
        List<String> list = new ArrayList<>(insertions);
        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            list.add(uuid);
        }
        System.out.println("开始过滤，1w数据，100个真的，9900个假的");
        int num=0;
        int right=0;
        int wrong=0;
        for (int i = 0; i < 10000; i++) {
            String data = i % 100 == 0 ? list.get(i / 100) : UUID.randomUUID().toString();
            if (bf.mightContain(data)) {
                num++;
                if (list.contains(data)) {
                    right++;
                } else {
                    wrong++;
                }
            }
        }
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);
        System.out.println("100W中1W数据百分比结果: ");
        System.out.println("布隆认为存在的："+num);
        System.out.println("真实存在的："+right+"\t 正确率"+percentFormat.format((float)right/100));
        System.out.println("不存在的："+wrong+"\t 误判率："+percentFormat.format((float)wrong/10000));
    }
}
