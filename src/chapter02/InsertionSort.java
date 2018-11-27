package chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InsertionSort {

    public static void sort(int[] data) {
        int swap, j;
        // 数组组成：有序区 + 无序区
        // 从1开始，第一个元素是有序的，直到数组尾部，共N - 1轮
        for (int i = 1; i < data.length; i++) {
            // 无序区的第一个元素，待插入到有序区中
            swap = data[i];
            // 有序区的最未位置
            j = i - 1;
            // 从右往左扫描有序区，比待插入元素swap大的，往后移动
            while(j >= 0 && swap < data[j]) {
                data[j + 1] = data[j];
                j--;
            }
            // 直到找到位置，插入待排序元素
            data[j + 1] = swap;
        }
    }
}


class InsertionSortTestCase {
    public static void main(String[] args) {
        int[] data = dataSource(20);
        System.out.println("Before Sort:" + Arrays.toString(data));
        InsertionSort.sort(data);
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