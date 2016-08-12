import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] test1 = { 1, 3 };
        System.out.println("test1, expect 2: " + nextServerNumber(test1));

        int[] test2 = { 1 };
        System.out.println("test2, expect 2: " + nextServerNumber(test2));

        int[] test3 = { 1, 2, 3 };
        System.out.println("test3, expect 4: " + nextServerNumber(test3));

        int[] test4 = { 3, 2, 1 };
        System.out.println("test4, expect 4: " + nextServerNumber(test4));

        int[] test5 = { };
        System.out.println("test5, expect 1: " + nextServerNumber(test5));

        int[] test6 = { 5, 4, 4, 1, 2, 3};
        System.out.println("test6, expect 6: " + nextServerNumber(test6));

        int[] test7 = { 5, 4, 4, 1, 1, 3};
        System.out.println("test7, expect 2: " + nextServerNumber(test7));

        int[] test8 = { 2 };
        System.out.println("test8, expect 1: " + nextServerNumber(test8));

        int[] test9 = { -1, 2 };
        System.out.println("test8, expect 1: " + nextServerNumber(test8));
    }
    public static int nextServerNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }


        Arrays.sort(nums);

        int serverNum = 1;
        int idx = 0;
        while (idx < nums.length) {
            // Takes care of negative numbers
            // Also takes care of getting the index back up to the server num if there are duplicates.
            if (nums[idx] < serverNum) {
                idx++;
                continue;
            }

            if (nums[idx] != serverNum) {
                return serverNum;
            }
            idx++;
            serverNum++;
        }

        return nums[nums.length - 1] + 1;
    }
}
