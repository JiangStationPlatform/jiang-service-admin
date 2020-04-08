package cn.jiang.station.platform.service;

import java.util.ArrayList;

public class Demo001 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        ArrayList<Integer> integers = printMatrix(matrix);
        for (Integer abc : integers) {
            System.out.println(abc + " ");
        }

    }

    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        /**
         * 思路，提取一行，整体上移一行，提取首列，整体前移一列
         */
        ArrayList<Integer> integers = new ArrayList<Integer>();
        int m = 0;
        int n = 0;
        //判断行或者列是否都提取完
        while (m < matrix.length || n < matrix[0].length) {
            //提取首列行，整体上移一行
            for (int col = 0; col < matrix[0].length - n; col++) {
                integers.add(matrix[0][col]);
            }
            for (int i = 0; i < matrix.length - 1 - n; i++) {
                matrix[i] = matrix[i + 1];
            }
            if (++m >= matrix.length) {
                break;
            }

            //提取末列，不移动
            for (int row = 0; row < matrix.length - m; row++) {
                integers.add(matrix[row][matrix[0].length - m]);
            }
            if (++n >= matrix[0].length) {
                break;
            }

            //提取末行，不移动
            for (int col = 0; col < matrix[0].length - n; col++) {
                integers.add(matrix[matrix.length - m][matrix[0].length - 1 - n - col]);
            }
            if (++m >= matrix.length) {
                break;
            }

            //提取首列，整体前移一列
            for (int row = 0; row < matrix.length - m; row++) {
                integers.add(matrix[matrix.length - 1 - m - row][0]);
                for (int i = 0; i < matrix[0].length - 1 - n; i++) {
                    matrix[matrix.length - 1 - m - row][i] = matrix[matrix.length - 1 - m - row][i + 1];
                }
            }
            if (++n >= matrix[0].length) {
                break;
            }
        }
        return integers;
    }
}
