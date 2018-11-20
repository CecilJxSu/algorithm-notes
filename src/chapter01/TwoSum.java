package chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSum {

    public static void main(String[] args) {
        // 集合，不能重复
        int[] nums = {1, 2, 5, -1, -2, -5, 0};
        for (Integer[] integers : twoSum1(nums)) {
            System.out.println(Arrays.toString(integers));
        }
    }

    /**
     * 平方级别
     */
    public static List<Integer[]> twoSum1(int[] nums) {
        List<Integer[]> results = new ArrayList<>();
        for(int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == 0) {
                    Integer[] result = new Integer[2];
                    result[0] = nums[i];
                    result[1] = nums[j];
                    results.add(result);
                }
            }
        }
        return results;
    }

    /**
     * 线性对数级别
     * 1. 排序；
     * 2. 遍历所有元素，每次查询元素的相反数（二分查找）；
     * 3. 如果存在，且相反数的位置大于当前位置，则添加进结果中。
     */
    public static List<Integer[]> twoSum2(int[] nums) {
        List<Integer[]> results = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (BinarySearch.binarySearch(-nums[i], nums) > i) {
                Integer[] result = new Integer[2];
                result[0] = nums[i];
                result[1] = -nums[i];
                results.add(result);
            }
        }
        return results;
    }
}
