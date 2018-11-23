package chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static void sort(int[] data) {
        quickSort(0, data.length - 1, data);
    }

    private static void quickSort(int low, int height, int[] data) {
        if (low >= height) {
            return;
        }
        // 分区点
        int p = partition(low, height, data);
        // 快排左边数组
        quickSort(low, p - 1, data);
        // 快排右边数组
        quickSort(p + 1, height, data);
    }

    private static int partition(int low, int height, int[] data) {
        // 随机获取基准点
        int randomIndex = ThreadLocalRandom.current().nextInt(low, height + 1);
        int pivot = data[randomIndex];
        // 将基准值和尾部的元素交换，如果直接选取尾部元素，可不用交换
        swap(randomIndex, height, data);
        while (low < height) {
            while (data[low] < pivot && low < height) {
                low++;
            }
            data[height] = data[low];
            while (data[height] > pivot && low < height) {
                height--;
            }
            data[low] = data[height];
        }
        data[low] = pivot;
        return low;
    }

    private static void swap(int i1, int i2, int[] data) {
        int tmp = data[i1];
        data[i1] = data[i2];
        data[i2] = tmp;
    }
}

class QuickSortTestCase {
    public static void main(String[] args) {
        int[] data = dataSource(20);
        System.out.println("Before Sort:" + Arrays.toString(data));
        QuickSort.sort(data);
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