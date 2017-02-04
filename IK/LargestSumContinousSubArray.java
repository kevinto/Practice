/**
 * Created by kevinto on 12/11/16.
 */
public class LargestSumContinousSubArray {
    public static void main(String[] args) {

        System.out.println("Positive max test: ");
        int[] test1 = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("recursive solution: " + maxSumSubArrayRecursive(test1));
        System.out.println("dp solution: " + maxSumSubArrayDPNonOptimalSpace(test1));
        System.out.println("dp solution space optimized: " + maxSumSubArrayDPOptimalSpace(test1));
        System.out.println("Using Kadanes: " + kadansPositiveOnly(test1));
        System.out.println("Using Kadanes modified: " + kadansModToWorkWithNegs(test1));
        System.out.println();

        System.out.println("Negative max test: ");
        int[] test2 = {-2, -3, -1, -2};
        System.out.println("recursive solution: " + maxSumSubArrayRecursive(test2));
        System.out.println("dp solution: " + maxSumSubArrayDPNonOptimalSpace(test2));
        System.out.println("dp solution space optimized: " + maxSumSubArrayDPOptimalSpace(test2));
        System.out.println("Using Kadanes[shows this is wrong]: " + kadansPositiveOnly(test2));
        System.out.println("Using Kadanes modified: " + kadansModToWorkWithNegs(test2));
        System.out.println();

        int[] test3 = {1, 5, -3};
        System.out.println("recursive solution: " + maxSumSubArrayRecursive(test3));
        System.out.println("Using Kadanes[shows this is wrong]: " + kadansPositiveOnly(test3));
        System.out.println("Using Kadanes modified: " + kadansModToWorkWithNegs(test3));
    }

    private static int finalMax;
    public static int maxSumSubArrayRecursive(int[] nums) {
        finalMax = Integer.MIN_VALUE;
//        return maxSumSubArrayRecursive1(nums, 0);
        recursiveRightToLeftTraversal(nums, nums.length - 1);
        return finalMax;
    }

    // This is the recursive definition:
    // MS(i) = Max[MS(i-1) + A[i] , A[i]]
    // Notes: Realize that the current max is the dp table element.
    //        Since we need the previous solution, the first element of
    //        the dp array has to be 0 to take care of the case where we
    //        are at the first element. This means that we need to create
    //        a dp array that is 1 element bigger than the size of the
    //        input array. This is because the last element of our input
    //        takes up that last dp array pos. The way I recursively solved
    //        it below is not optimally structured to convert to a dp solution.
    private static int maxSumSubArrayDPNonOptimalSpace(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        int finalMax = Integer.MIN_VALUE;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            finalMax = Math.max(dp[i], finalMax);
        }

        return finalMax;
    }

    private static int maxSumSubArrayDPOptimalSpace(int[] nums) {
        int finalMax = Integer.MIN_VALUE;
        int prevMax = 0;
        int currMax;

        for (int i = 1; i < nums.length + 1; i++) {
            currMax = Math.max(prevMax + nums[i - 1], nums[i - 1]);
            prevMax = currMax;
            finalMax = Math.max(currMax, finalMax);
        }

        return finalMax;
    }

    // Uses kadans. Only works if we have a single positive number.
    private static int kadansPositiveOnly(int[] nums) {
       int maxSoFar = Integer.MIN_VALUE;
       int maxEndingHere = 0;

       for (int i = 0; i < nums.length; i++) {
           maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
           if (maxEndingHere < 0) {
               maxEndingHere = 0;
           }

           maxSoFar = Math.max(maxSoFar, maxEndingHere);
       }

       return maxSoFar;
    }

    // Uses kadans and modifies the start values to take care of negative values
    private static int kadansModToWorkWithNegs(int[] nums) {
       int maxSoFar = nums[0];
       int maxEndingHere = nums[0];

       for (int i = 1; i < nums.length; i++) {
           maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
           maxSoFar = Math.max(maxSoFar, maxEndingHere);
           if (maxEndingHere < 0) {
               maxEndingHere = 0;
           }
       }

       return maxSoFar;
    }

    // Alternative recursive method that is less complicated, and involves no forloop.
    // This is the easiest recursive definition to convert to a DP solution.
    // This method involves going all the way to from the end of the array to the beginning.
    // Then on the way back up the recursive stack, we say:
    //  1. Do we want to add current with the best we found before?
    //  OR
    //  2. Do we want to start new and just return current?
    private static int recursiveRightToLeftTraversal(int[] nums, int start) {
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
        int currMax = Math.max(recursiveRightToLeftTraversal(nums, start - 1) + nums[start], nums[start]);

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

    // This way recursively traverses left to right.
    private static int maxSumSubArrayRecursive1(int[] nums, int start) {
        if (start >= nums.length) {
            return Integer.MIN_VALUE;
        }

        int currMax = 0;
        for (int end = start; end < nums.length; end++) {
            currMax += nums[end];
            finalMax = Math.max(finalMax, Math.max(currMax, maxSumSubArrayRecursive1(nums, end + 1)));
        }

        return finalMax;
    }

    public static int maxSumSubArrayRecursiveWithNoClassVar(int[] nums, int start) {
        if (start >= nums.length) {
            return Integer.MIN_VALUE;
        }

        int currMax = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = start; i < nums.length; i++) {
            currSum += nums[i];
            currMax = Math.max(currMax, currSum);
            int otherMax = maxSumSubArrayRecursiveWithNoClassVar(nums, i + 1);
            currMax = Math.max(currMax, otherMax);
        }

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

    // Clean working one during coding exercise:

    /*
    private static int finalMax;
    public static int maxSumDp(int[] nums) {
        finalMax = Integer.MIN_VALUE;
        maxSumHelper(nums, nums.length - 1);
        return finalMax;
    }

    private static int maxSumHelper(int[] nums, int end) {
        if (end < 0) {
            return 0;
        }

        int currMax = Math.max(nums[end], nums[end] + maxSumHelper(nums, end - 1));
        finalMax = Math.max(finalMax, currMax);

        return currMax;
    }

    public static int maxSumKadane(int[] nums) {
        // TODO: Input Checking

        int sum = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // We can choose to extend the current substring
            // OR start over.
            sum = Math.max(nums[i], sum + nums[i]);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
     */
}






















