package chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SelectionSort {

    public static void sort(int[] data) {
        int minValue, minIndex, j;
        // 数组组成：已排序部分 + 待排序部分
        // 每轮处理待排序部分，每轮选取最小值，移至头部；N - 1轮
        for (int i = 0; i < data.length - 1; i++) {
            // 开始以头部第一个为最小值；
            minValue = data[i];
            minIndex = i;
            // 往后查询最小值
            for (j = i + 1; j < data.length; j++) {
                // 更换最小值和位置
                if (data[j] < minValue) {
                    minValue = data[j];
                    minIndex = j;
                }
            }
            // 最小值在后面才交换
            if (i != minIndex) {
                swap(i, minIndex, data);
            }
        }
    }

    private static void swap(int i1, int i2, int[] data) {
        int tmp = data[i1];
        data[i1] = data[i2];
        data[i2] = tmp;
    }
}

class SelectionSortTestCase {
    public static void main(String[] args) {
        int[] data = dataSource(20);
        System.out.println("Before Sort:" + Arrays.toString(data));
        SelectionSort.sort(data);
        System.out.println("After Sort:" + Arrays.toString(data));
    }

    private static int[] dataSource(int scale) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < scale; i++) {
            data.add(i);
        }
        Collections.shuffle(data);
        return data.stream().mapToInt(i -> i).toArray();
    }
}