import java.util.Arrays;

/**
 * Created by kevinto on 1/26/17.
 * http://rosettacode.org/wiki/Sorting_algorithms/Insertion_sort
 */

public class InsertionSortIK {
    public static void main(String[] args) {
        int[] nums = {7, 1, 3, 6, 100};
        System.out.println("original: " + Arrays.toString(nums));

        insertionSortNoExtra(nums);
        System.out.println("sorted: " + Arrays.toString(nums));
    }

    public static void insertionSortNoExtra(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        for(int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int j = i - 1;

            while (j >= 0 && nums[j] > curr) {
                nums[j + 1] = nums[j];
                j--;
            }

            // I know that this number ahead of me was already shifted.
            nums[j + 1] = curr;
        }
    }

    public static void insertionSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] > curr) {
                    shift(nums, j, i);
                    nums[j] = curr;
                    break;
                }
            }
        }
    }

    private static void shift(int[] nums, int start, int end) {
        for (int i = end; i >= start + 1; i--) {
            nums[i] = nums[i - 1];
        }
    }
}
