package chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        // 集合，不能重复
        int[] nums = {2, -1, 4, -2, -3, 5, -4, 1, 3, 0};
        for (Integer[] integers : threeSum1(nums)) {
            System.out.println(Arrays.toString(integers));
        }
    }

    /**
     * 立方级别
     */
    public static List<Integer[]> threeSum1(int[] nums) {
        List<Integer[]> results = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        Integer[] result = new Integer[3];
                        result[0] = nums[i];
                        result[1] = nums[j];
                        result[2] = nums[k];
                        results.add(result);
                    }
                }
            }
        }
        return results;
    }

    /**
     * 平方对数级别
     * 1. 排序；遍历元素；最外2层遍历，固定2个数；
     * 2. 然后再判断2个数的和的相反数是否存在（二分查找）；
     * 3. 如果存在，且相反数的位置大于当前位置，则添加到结果中。
     */
    public static List<Integer[]> threeSum2(int[] nums) {
        List<Integer[]> results = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (BinarySearch.binarySearch(-nums[i] - nums[j], nums) > j) {
                    Integer[] result = new Integer[3];
                    result[0] = nums[i];
                    result[1] = nums[j];
                    result[2] = -nums[i] - nums[j];
                    results.add(result);
                }
            }
        }
        return results;
    }
}
