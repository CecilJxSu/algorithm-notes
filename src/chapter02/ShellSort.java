package chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShellSort {

    /**
     * input: [2, 5, 6, 4, 1, 8, 9, 7, 3, 0]
     * gap: len / 2 --> 5
     *
     * 1st round:
     *
     * 2 5 6 4 1        2 5 6 3 0
     * ↓ ↓ ↓ ↓ ↓    →   ↓ ↓ ↓ ↓ ↓
     * 8 9 7 3 0        8 9 7 4 1
     * ---------------------------
     * 1st round sorted: [2, 5, 6, 3, 0, 8, 9, 7, 4, 1]
     * gap: 5 / 2 --> 2
     *
     * 2nd round:
     * insertion sort from 6 to 3, then to 0, to 8 until end (left to right, up to down).
     *
     * 2 5      0 1
     * 6 3      2 3
     * 0 8  →   4 5
     * 9 7      6 7
     * 4 1      9 8
     * ---------------------------
     * 2nd round sorted: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
     * gap: 2 / 2 --> 1
     *
     * 3rd round: should arrange vertically
     *
     * 0 1 2 3 4 5 6 7 8 9 → 0 1 2 3 4 5 6 7 8 9
     */
    public static void sort(int[] data) {
        int swap, i, j;
        // 步长每次减半，直到步长为1，变成普通插入排序
        for (int gap = data.length >> 1; gap > 0; gap >>= 1) {
            // 从gap开始，从左往右，从上到下，直到数组尾部，直接插入排序
            for (i = gap; i < data.length; i++) {
                swap = data[i];
                j = i - gap;
                while (j >= 0 && swap < data[j]) {
                    data[j + gap] = data[j];
                    j = j - gap;
                }
                data[j + gap] = swap;
            }
        }
    }
}

class ShellSortTestCase {
    public static void main(String[] args) {
        int[] data = dataSource(20);
        System.out.println("Before Sort:" + Arrays.toString(data));
        ShellSort.sort(data);
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
