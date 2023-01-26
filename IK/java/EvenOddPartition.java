import java.util.Arrays;

/**
 * Created by kevinto on 2/28/17.
 */
public class EvenOddPartition {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        evenOddSort(nums);
        System.out.print(Arrays.toString(nums));
    }

    private static void evenOddSort(int[] nums) {
        int i = 0, j = nums.length - 1;

        while (i < j) {
            while (i < j && nums[i] % 2 == 0) {
                i++;
            }

            while (i < j && nums[j] % 2 == 1) {
                j--;
            }

            if (i < j) {
                swap(nums, i++, j--);
            }
        }
    }

    private static void swap(int[] nums, int i, int i1) {
        int temp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = temp;
    }
}
