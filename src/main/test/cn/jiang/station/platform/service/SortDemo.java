package cn.jiang.station.platform.service;

import org.junit.Test;

import java.util.Arrays;

/**
 * 排序测试
 */
public class SortDemo {
    public static int[] arr = {34, 53, 2, 213, 47, 479, 87, 68, 5, 9, 11, 15, 342, 64, 35, 57, 122};

    @Test
    public void maopao() {
        int num = 0;
        for (int outer = 0; outer < arr.length; outer++) {
            //外层循环，定位初始位置
            for (int inner = 0; inner < arr.length - 1 - outer; inner++) {
                //内层循环，比较大小
                if (arr[inner] > arr[inner + 1]) {
                    int temp = arr[inner];
                    arr[inner] = arr[inner + 1];
                    arr[inner + 1] = temp;
                    num++;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("排序所用次数： " + num);
    }

    @Test
    public void charu() {
        int num = 0;
        for (int outer = 1; outer < arr.length; outer++) {
            //外层循环，定位初始位置1
            for (int inner = outer - 1; inner >= 0; inner--) {
                //内层循环，从右边取值比较插入左边
                if (arr[inner + 1] < arr[inner]) {
                    int temp = arr[inner];
                    arr[inner] = arr[inner + 1];
                    arr[inner + 1] = temp;
                    num++;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("排序所用次数： " + num);
    }
}
