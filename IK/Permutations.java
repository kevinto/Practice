/**
 * Created by kevinto on 12/27/16.
 */
public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        printPermutations(nums);
    }

    public static void printPermutations(int[] nums) {
        printPermutations(nums, 0);
    }

    private static void printPermutations(int[] nums, int writeIndex) {
        if (writeIndex == nums.length) {
            for (int num : nums) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int j = writeIndex; j < nums.length; j++) {
            swap(nums, writeIndex, j);
            printPermutations(nums, writeIndex + 1);
            swap(nums, writeIndex, j);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
