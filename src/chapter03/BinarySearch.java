package chapter03;

import java.util.ArrayList;
import java.util.List;

import static chapter03.BinarySearch.search;

public class BinarySearch {

    public static int search(int target, int[] data) {
        int low = 0, height = data.length - 1;
        // 'mid = (low + hight) / 2' may exceed Integer.MAX_VALUE.
        int mid;
        while (low <= height) {
            mid = low + (height - low) / 2;
            if (target < data[mid]) {
                height = mid - 1;
            } else if (target > data[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

/**
 * jvm opts: -ea
 */
class BinarySearchTestCase {
    public static void main(String[] args) {
        int[] data = dataSource(20000);
        assert search(-1, data) == -1 : "Should return -1.";
        assert search(data[data.length - 1] + 1, data) == -1 : "Should return -1.";
        for (int d : data) {
            assert search(d, data) == d : "Should return " + d + ".";
        }
    }

    private static int[] dataSource(int scale) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < scale; i++) {
            data.add(i);
        }
        return data.stream().mapToInt(i -> i).toArray();
    }
}