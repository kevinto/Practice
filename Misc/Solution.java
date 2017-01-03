public class Solution {
    public static void main(String[] args) {
//        int[] nums = {-2, -3, 4, -1, -2, 1, 5, -3};
//        System.out.println(findMax(nums)); // ans is 7.

//        int[] nums1 = {-1, 1, 2};
        int[] nums1 = {100, -1, 99};
        System.out.println(maxSumSubArrayRecursive(nums1)); // ans is 3.
//        System.out.println(findMax(nums1)); // ans is 3.

//        int[] nums2 = {1, 1, 2};
//        System.out.println(findMax(nums2)); // ans is 4.
    }

    private static int maxSoFar;
    public static int findMax(int[] nums) {
        maxSoFar = Integer.MIN_VALUE;
        findMax(nums, 0, 0);
        return maxSoFar;
    }

    public static void findMax(int[] nums, int pos, int sum) {
        if (pos >= nums.length) {
            return;
        }

        sum += nums[pos];
        findMax(nums, pos + 1, sum); // Choose to add
        findMax(nums, pos + 1, 0); // Dont use to add

        maxSoFar = Math.max(maxSoFar, sum);
    }

    private static int finalMax;
    public static int maxSumSubArrayRecursive(int[] nums) {
        finalMax = Integer.MIN_VALUE;
        maxSumSubArrayRecursiveRightToLeftTraversal(nums, nums.length - 1);
        return finalMax;
    }

    // Can you implement the same requirements starting from the left to right?
    //  Yes, you can the implementation is belove.
    private static int maxSumSubArrayRecursiveRightToLeftTraversal(int[] nums, int start) {
        if (start < 0) {
            return 0;
        }

        // The max is either the current number or the current number + the next segment.
        // How does this problem deal with when the max doesnt involve the previously viewed number?
        // The currMax that is returned ALWAYS involves the current number. Notice nums[start] is always added.

        // This line gives us the max if:
        // - We chose the current number only.
        // - We chose the current number as an ending to a sub array
        // The beginning of our maxest subarray is returned because the current element + recur call is less
        // then the curr element by itself.
        // How does a break occur between the end of a potential max subarray and the beginning of another potential
        // max subarray?
        // -> break occurs when the curr element plus the recur value is less than the just returning the current
        //    element itself.
        int currMax = Math.max(maxSumSubArrayRecursiveRightToLeftTraversal(nums, start - 1) + nums[start], nums[start]);

        // We need this global max check because we cant wait till the end to return the global max found so far.
        // Since any number can be an end and, as an end, it can include all numbers before it.
        if (currMax > finalMax) {
            finalMax = currMax;
        }

        // The return value either includes the sum of the previous segments + this segment OR
        // the value of this segment only. if it is the value of this segment only then it is
        // returning the start of a potential max subarray.
        return currMax;
    }

    // Left to right traversal with no forloop.
    private static int maxSoFar2;
    public static int recursiveLeftToRightTraversal(int[] nums) {
        maxSoFar2 = Integer.MIN_VALUE;
        recursiveLeftToRightTraversal(nums, 0);
        return maxSoFar2;
    }

    public static int recursiveLeftToRightTraversal(int[] nums, int end) {
        if (end >= nums.length) {
            return 0;
        }

        int currMax = Math.max(recursiveLeftToRightTraversal(nums, end + 1) + nums[end], nums[end]);
        maxSoFar2 = Math.max(maxSoFar2, currMax);
        return currMax;
    }

    public static int leftToRightDpConversion(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length + 1];
        int finalMax = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1] + nums[i], nums[i]);
            finalMax = Math.max(dp[i], finalMax);
        }

        return finalMax;
    }
}

