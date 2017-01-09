/**
 * Created by kevinto on 1/8/17.
 */
public class FindMinInRotatedSortedArray {
    public static void main(String[] args) {
        int[] test1 = {5, 1, 2, 3, 4}; // 1 is shift point
        int[] test2 = {4, 5, 1, 2, 3}; // 2 is shift point
        int[] test3 = {4, 5, 6, 7, 1}; // 4 is shift point

        System.out.println(findShift(test1));
        System.out.println(findShift(test2));
        System.out.println(findShift(test3));
    }

    // TODO: the rest of the problem

    public static int findShift(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = ((end - start) / 2) + start;
            if (mid - 1 >= 0 && arr[mid - 1] > arr[mid]) {
                return mid;
            } else if (mid + 1 < arr.length && arr[mid + 1] < arr[mid]) {
                return mid + 1;
            } else if (arr[mid] <= arr[end]) {
                end = mid - 1;
            } else { // arr[mid] > arr[end]
                start = mid;
            }
        }

        return -1;
    }
}
