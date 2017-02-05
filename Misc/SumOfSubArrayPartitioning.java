import java.util.Arrays;

/**
 * Created by kevinto on 11/22/16.
 * My solution is based on starting at the beginning of the array,
 * and finding all the subsets.
 *
 * The solution here:
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
 * Is based off of a solution that starts from the end of the array.
 */

public class SumOfSubArrayPartitioning {
    public static void main(String[] args) {
//        int[] test1 = {6, 5, 4, 5};
//        int target1 = 3;
//        boolean result = subsetSum(test1, target1);
//        System.out.println(result);

        int[] test2 = {2, 2, 8};
//        int[] test2 = {1, 2, 4, 3};
        int target2 = 6;
//        int target2 = 5;
        //boolean result2 = subsetSum(test2, target2);
        //System.out.println(result2);
        boolean result2 = subsetExistsDp1(test2);
        System.out.println(result2);

//        int[] test3 = {6, 5, 4, 5};
//        int target3 = 11;
//        boolean result3 = subsetSum(test3, target3);
//        System.out.println(result3);
    }

    public static boolean subsetSum(int[] arr, int target) {
        return subsetSumDp(arr, target);
//        return subsetSumRecursive(arr, 0, target);
    }

    public static boolean subsetSumRecursive(int[] arr, int pos, int target) {
        if (pos >= arr.length) {
            return false;
        } else if (target == 0) {
            return true;
        } else if (target < 0) {
            return false;
        }

        boolean result = subsetSumRecursive(arr, pos + 1, target - arr[pos]);
        result |= subsetSumRecursive(arr, pos + 1, target);

        return result;
    }

    // Can build up to the solution by checking previous solutions for smaller
    // targets. The key point is whether we can construct the target from
    // another subset element OR (using this subset element and a smaller target solution)
    public static boolean subsetSumDp(int[] arr, int target) {
        boolean[][] dp = new boolean[target + 1][arr.length + 1];

        // Initialize the first row as true, according to our base case.
        // First row represents the sum being 0.
        for (int col = 0; col < dp[0].length; col++) {
            dp[0][col] = true;
        }

        // We are going to build downwards in our DP array. Start at the top
        // right corner and build down to the bottom left corner.
        for (int row = 1; row < dp.length; row++) {
            for (int col = dp[0].length - 2; col >= 0; col--) {
                // The current element is true if:
                    // The array element before this pos could already
                    // evaluate to this sum.
                    // - OR -
                    // The (target - the current array element) = old target.
                    // The old target was reachable by the next number. This means
                    // The current target is reachable because we can use our
                    // current number.
                dp[row][col] = dp[row][col + 1];
                if (row - arr[col] >= 0) {
                    dp[row][col] |= dp[row - arr[col]][col + 1];
                }
            }
        }

        // Why are we building downwards?
            // When we did our recursive call, we started at our target number.
            // Then traversed top down all the way to a sum of 0. The current
            // solution builds bottom up towards our target.

        printMatrix(dp);
        return dp[target][0];
    }

    public static void printMatrix(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // This implementation of DP models the dp table as
    // x-axis is the target values, y-axis as the pos values.
    public static boolean subsetExistsDp1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int val : nums) {
            sum += val;
        }

        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];

        // Init first col to be true
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i + 1][j];

                if (j - nums[i] >= 0) {
                    dp[i][j] |= dp[i + 1][j - nums[i]];
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[0][target];
    }

    // Memory efficient solution to what we have above:
    public static boolean subsetSumDpMemoryOptimized(int[] nums, int target) {
        boolean[] prev = new boolean[target + 1];
        boolean[] curr = null;
        System.out.println("finding target: " + target);

        for (int i = nums.length - 1; i >= 0; i--) {
            curr = new boolean[target + 1];
            for (int j = 0; j < curr.length; j++) {
                if (j == 0) {
                    curr[j] = true;
                    continue;
                }

                curr[j] = prev[j];
                if (j - nums[i] >= 0) {
                    curr[j] |= prev[j - nums[i]];
                }
            }

            prev = curr;
        }

        return prev[target];
    }

    // Given the dp1 table above this is how you find the path. Or in other words, this is how
    // you find the exact subset.
    /*
    // Gets the actual subset
    private static boolean[][] dp1;
    public static List<Integer> dfsDp1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int sum = 0;
        for (int val : nums) {
            sum += val;
        }

        dfsDp1Helper(nums, res, 0, sum / 2);
        return res;
    }

    private static boolean dfsDp1Helper(int[] nums, List<Integer> path, int row, int col) {
        if (row < 0 || col < 0) {
            return false;
        } else if (col == 0) {
            return true;
        }

        path.add(nums[row]);
        col = col - nums[row];
        row = row + 1;

        while (row < dp1.length && col >= 0 && dp1[row][col] == true) {
            row++;
        }

        if (dfsDp1Helper(nums, path, row - 1, col)) {
            return true;
        } else {
            return false;
        }
    }
     */
}
