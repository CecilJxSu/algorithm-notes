package chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeSort {

    public static void sort(int[] data) {
        int[] temp = new int[data.length];
        recursive(data, temp, 0, data.length - 1);
    }

    /**
     * divide and conquer
     */
    public static void recursive(int[] data, int[] temp, int start, int end) {
        // 如果长度是1，返回
        if (start >= end) {
            return;
        }

        // divide，分成2半
        int mid = start + (end - start) / 2;
        // 排好左边数组
        recursive(data, temp, start, mid);
        // 排好右边数组
        recursive(data, temp, mid + 1, end);
        // 选择最小的插入到temp中，归并
        int from1 = start;
        int from2 = mid + 1;
        int from3 = start;
        while (from1 <= mid && from2 <= end) {
            if (data[from1] < data[from2]) {
                temp[from3++] = data[from1++];
            } else {
                temp[from3++] = data[from2++];
            }
        }
        // 左边数组仍剩余，全部复制给temp
        while (from1 <= mid) {
            temp[from3++] = data[from1++];
        }
        // 右边数组仍剩余，全部复制给temp，左右不可能同时仍剩余
        while (from2 <= end) {
            temp[from3++] = data[from2++];
        }
        // 从temp复制回给data，完成该分区的排序
        for (int i = start; i <= end; i++) {
            data[i] = temp[i];
        }
    }
}

class MergeSortTestCase {
    public static void main(String[] args) {
        int[] data = dataSource(20);
        System.out.println("Before Sort:" + Arrays.toString(data));
        MergeSort.sort(data);
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
