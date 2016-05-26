package p303;

import java.util.NavigableMap;

/**
 * Created by kevin on 5/25/2016.
 */
public class RangeSumQueryImmuteable {
    public static void main(String[] args) {
        int[] arr = { 1,  2, 3, 4 };
        NumArray(arr);
        System.out.println(sumRange(1, 2));
    }

    private static int[] numArr;
    public static void NumArray(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        numArr = nums;
    }

    public static int sumRange(int i, int j) {
        if (i == 0) {
            return numArr[j];
        }

        return numArr[j] - numArr[i - 1];
    }
}
