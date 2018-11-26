package chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HeapSort {

    public static void sort(int[] data) {
        // 建立大根堆
        heapify(data);

        // 数组组成：大根堆 + 已排序部分
        // 每轮从大根堆中选择最大值，移至尾部已排序部分
        int end = data.length - 1;
        while (end > 0) {
            swap(0, end, data);
            end -= 1;
            // 重新修复大根堆
            siftDown(0, end, data);
        }
    }

    /**
     * 整个数组建立大根堆
     */
    private static void heapify(int[] data) {
        // 最后一个父节点
        int start = iParent(data.length - 1);

        // 直到第一个父节点
        while (start >= 0) {
            siftDown(start, data.length - 1, data);
            start -= 1;
        }
    }

    /**
     * 从start位置开始，建立大根堆；
     * 然后循环修复被打破大根堆规则的子节点
     */
    private static void siftDown(int start, int end, int[] data) {
        int root = start;
        while (iLeftChild(root) <= end) {
            int leftChild = iLeftChild(root);
            // 默认根节点最大
            int swap = root;
            // 判断是否左节点最大，然后更新
            if (data[swap] < data[leftChild]) {
                swap = leftChild;
            }
            // 判断是否右节点最大，然后更新
            if (leftChild + 1 <= end && data[swap] < data[leftChild + 1]) {
                swap = leftChild + 1;
            }
            if (swap == root) {
                return;
            } else {
                // 最大节点与根节点交换
                swap(root, swap, data);
                // 最大节点下的子节点打破大根堆规则，重新循环修复
                root = swap;
            }
        }
    }

    /**
     * 求父节点下标
     */
    private static int iParent(int i) {
        return (i - 1) / 2;
    }

    /**
     * 求左孩子节点下标
     */
    private static int iLeftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * 求右孩子节点下标
     */
    private static int iRightChild(int i) {
        return 2 * i + 2;
    }

    private static void swap(int i1, int i2, int[] data) {
        int tmp = data[i1];
        data[i1] = data[i2];
        data[i2] = tmp;
    }
}

class HeapSortTestCase {
    public static void main(String[] args) {
        int[] data = dataSource(20);
        System.out.println("Before Sort:" + Arrays.toString(data));
        HeapSort.sort(data);
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