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

        int[] test2 = {1, 2, 4, 3};
        int target2 = 5;
        boolean result2 = subsetSum(test2, target2);
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
}
