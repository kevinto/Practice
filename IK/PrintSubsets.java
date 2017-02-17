/**
 * Created by kevinto on 2/17/17.
 */
public class PrintSubsets {
    public static void main(String[] args) {
        int[] nums = {1, 2};
        printSubsets(nums, 0, 0);
    }

    public static void printSubsets(int[] nums, int read, int write) {
        if (write >= nums.length) {
            for (int i = 0; i < read; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
            return;
        }

        printSubsets(nums, read, write + 1);
        swap(nums, read, write);
        printSubsets(nums, read + 1, write + 1);
        swap(nums, read, write);
    }

    private static void swap(int[] nums, int i1, int i2) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}
