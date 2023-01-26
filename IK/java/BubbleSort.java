import java.util.Arrays;

/**
 * Created by kevinto on 1/26/17.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {7, 1, 3, 6, 100};
        System.out.println("original: " + Arrays.toString(nums));

        bubbleSortDoWhile(nums);
        System.out.println("sorted: " + Arrays.toString(nums));
    }

    public static void bubbleSortDoWhile(int[] nums) {
        boolean swapped = false;

        do {
            swapped = false;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i - 1, i);
                    swapped = true;
                }
            }
        }
        while (swapped);
    }

    public static void bubbleSort(int[] nums) {
        int i = 1;
        boolean swapped = false;

        while (i < nums.length) {
            if (nums[i - 1] > nums[i]) {
                swap(nums, i - 1, i);
                swapped = true;
            }

            if (swapped && i == nums.length - 1) {
                swapped = false;
                i = 1;
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i1, int i2) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}
