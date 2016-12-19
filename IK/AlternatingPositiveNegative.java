import java.util.Arrays;

/**
 * Created by kevinto on 12/18/16.
 */
public class AlternatingPositiveNegative {
    public static void main(String[] args) {
        int[] nums1 = {2, 3, -4, -9, -1, -7, 1, -5, -6};
        int[] nums2 = {2, 3, -4, -9, -1, -7, 1, -5, -6};
        System.out.println(Arrays.toString(negPos(nums1)));
        negPosChangeInput(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    // This implementation changes the input. rightShift moves the
    // rightmost element to the beginning of the range and shifts
    // everything else over. This maintains the stability.
    public static void negPosChangeInput(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int index;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0 && nums[i] < 0) {
                index = getNextVal(nums, i, false);
                if (index == -1) {
                    continue;
                }
                rightShift(nums, i, index);
            } else if (i % 2 == 1 && nums[i] > 0) {
                index = getNextVal(nums, i, true);
                if (index == -1) {
                    continue;
                }
                rightShift(nums, i, index);
            }
        }
    }

    private static void rightShift(int[] input, int start, int end) {
       int elementToShiftToFront = input[end];
       for (int i = end; i > start; i--) {
           input[i] = input[i - 1];
       }
       input[start] = elementToShiftToFront;
    }

    // This returns a new result array without modifying the input.
    // It uses something like the merge sort pattern to find the answer
    public static int[] negPos(int[] nums) {
        int[] res = new int[nums.length];
        int posIdx = getNextVal(nums, -1, false);
        int negIdx = getNextVal(nums, -1, true);

        int i = 0;
        for (; i < res.length; i++) {
            if (i % 2 == 0) {
                if (posIdx == -1) {
                    break;
                }
                res[i] = nums[posIdx];
                posIdx = getNextVal(nums, posIdx, false);
            } else {
                if (negIdx == -1) {
                    break;
                }
                res[i] = nums[negIdx];
                negIdx = getNextVal(nums, negIdx, true);
            }
        }

        while (i < res.length && posIdx != -1) {
            res[i++] = nums[posIdx];
            posIdx = getNextVal(nums, posIdx,false);
        }

        while (i < res.length && negIdx != -1) {
            res[i++] = nums[negIdx];
            negIdx = getNextVal(nums, negIdx, true);
        }

        return res;
    }

    private static int getNextVal(int[] nums, int index, boolean isNeg) {
        for (int i = index + 1; i < nums.length; i++) {
            if ((!isNeg && nums[i] > 0) || (isNeg && nums[i] < 0)) {
                return i;
            }
        }
        return -1;
    }
}
