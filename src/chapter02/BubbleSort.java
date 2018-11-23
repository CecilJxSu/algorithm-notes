package chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BubbleSort {

    public static void sort(int[] data) {
        // 需要N - 1轮
        for (int i = 0; i < data.length - 1; i++) {
            // 从底部开始，直到i（不包括i，i之前已经排序好）
            for (int j = data.length - 1; j > i; j--) {
                // 两两比较，比前面一位小，交换
                if (data[j] < data[j - 1]) {
                    swap(j, j - 1, data);
                }
            }
        }
    }

    private static void swap(int i1, int i2, int[] data) {
        int temp = data[i1];
        data[i1] = data[i2];
        data[i2] = temp;
    }
}

class BubbleSortTestCase {
    public static void main(String[] args) {
        int[] data = dataSource(20);
        System.out.println("Before Sort:" + Arrays.toString(data));
        BubbleSort.sort(data);
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