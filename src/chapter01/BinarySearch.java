package chapter01;

public class BinarySearch {

    public static void main(String[] args) {
        int[] sortedArr1 = {2, 4, 6, 8, 9};
        int[] sortedArr2 = {1, 3, 5, 7, 9};
        System.out.println(binarySearch(4, sortedArr1));
        System.out.println(binarySearch(3, sortedArr2));
    }

    /**
     * 二分查询
     * @param target    待查询目标元素
     * @param sortedArr 升序数组
     * @return          目标元素下标，-1为不存在
     */
    public static int binarySearch(int target, int[] sortedArr) {
        int low = 0;
        int high = sortedArr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sortedArr[mid] < target) {
                low = mid + 1;
            } else if (sortedArr[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
