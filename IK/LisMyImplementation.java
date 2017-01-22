/**
 * Created by kevinto on 1/22/17.
 */
public class LisMyImplementation {
    public static void main(String[] args) {
//        int arr[] = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
//        int arr[] = {5, 1, 2};
        int arr[] = {5, 1, -1, 2, 2, 3, 4, 0, 1};
        lisProperRecursion(arr);
        System.out.println(myDp(arr));
    }

    private static int maxLen;
    private static void lisProperRecursion(int[] nums) {
        maxLen = 0;
        myRecursion(nums, 0);
        System.out.println("my recursive impl: " + maxLen);
    }

    // Tries the next element in the for loop to see if it
    //  1. has a max inc length we can add on
    //  OR
    //  2. its max len is greater than what we have now excluding the current element.
    private static int myRecursion(int[] nums, int pos) {
        if (pos >= nums.length) {
            return 0;
        }

        int currLen = 1;
        for (int i = pos + 1; i < nums.length; i++) {
            int subLen = myRecursion(nums, i);

            if (nums[pos] < nums[i]) {
                currLen = Math.max(currLen, subLen + 1);
            }

            maxLen = Math.max(Math.max(currLen, subLen), maxLen);
        }

        return currLen;
    }

    private static int myDp(int[] nums) {
        int[] dp = new int[nums.length + 1];
        int maxLength = 0;

        for (int dpIdx = nums.length - 1; dpIdx >= 0; dpIdx--) {
            int currLen = 1;
            for (int nextIdx = dpIdx + 1; nextIdx < nums.length; nextIdx++) {
                int subLen = dp[nextIdx];

                if (nums[dpIdx] < nums[nextIdx]) {
                    currLen = Math.max(currLen, subLen + 1);
                }

                maxLength = Math.max(Math.max(currLen, subLen), maxLength);
            }

            dp[dpIdx] = currLen;
        }

        return maxLength;
    }
}
