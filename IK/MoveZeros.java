import java.util.Arrays;

/**
 * Created by kevinto on 2/4/17.
 */
public class MoveZeros {
    public static void main(String[] args) {
        int[] test1 = {0, 1, 0, 3, 12};
        moveZeroOptimal(test1);
        System.out.println(Arrays.toString(test1));
    }

    private static void moveZeroOptimal(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }

        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}
