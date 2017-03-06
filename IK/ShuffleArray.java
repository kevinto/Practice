import java.util.Arrays;
import java.util.Random;

/**
 * Created by kevinto on 3/6/17.
 */
public class ShuffleArray {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        shuffle(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void shuffle(int[] nums) {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int swapIdx = random.nextInt(nums.length);
            swap(nums, i, swapIdx);
        }
    }

    private static void swap(int[] nums, int i1, int i2) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}
