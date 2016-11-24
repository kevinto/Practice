import java.util.ArrayList;

/**
 * Created by kevinto on 11/22/16.
 */
public class SumOfSubArrayPartitioning {
    public static void main(String[] args) {
        int[] test1 = {6, 5, 4, 5};
        int target1 = 3;
        boolean result = subsetSum(test1, target1);
        System.out.println(result);

        int[] test2 = {6, 5, 4, 5};
        int target2 = 10;
        boolean result2 = subsetSum(test2, target2);
        System.out.println(result2);

        int[] test3 = {6, 5, 4, 5};
        int target3 = 11;
        boolean result3 = subsetSum(test3, target3);
        System.out.println(result3);
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

    public static boolean subsetSumDp(int[] arr, int target) {
        boolean[][] dp = new boolean[target + 1][arr.length + 1];

        // Initialize the first row as true, according to our base case.
        for (int col = 0; col < dp[0].length; col++) {
            dp[0][col] = true;
        }

        for (int row = 1; row < dp.length; row++) {
            for (int col = dp[0].length - 2; col >= 0; col--) {
                dp[row][col] = dp[row][col + 1];
                if (row - arr[col] >= 0) {
                    dp[row][col] |= dp[row - arr[col]][col + 1];
                }
            }
        }

        return dp[target][0];
    }
}
