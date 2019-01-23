package chapter03;

import java.util.ArrayList;
import java.util.List;

import static chapter03.LinearInterpolationSearch.search;

public class LinearInterpolationSearch {

    public static int search(int target, int[] data) {
        int low = 0, height = data.length - 1;
        int mid;
        // 1. 分母不能为0
        // 2. target要落在[data[low], data[height]]区间，在这个区间预测
        while (data[low] != data[height] && target >= data[low] && target <= data[height]) {
            mid = low + (target - data[low]) * (height - low) / (data[height] - data[low]);
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
class LinearInterpolationSearchTestCase {
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
