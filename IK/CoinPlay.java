import java.util.Arrays;

/**
 * Created by Kevin on 11/5/16.
 */
public class CoinPlay {
    public static void main(String[] args) {
        int[] coins = { 5, 3, 7, 10 };
//        int[] coins = {5, 3, 10};
        System.out.println(maxWin(coins));
    }


    static int maxWin(int[] coins) {
//        return maxWinRecursive(coins, 0, coins.length - 1);
//        return maxWinBottomDp(coins, coins.length);
        return maxWinBottomDp2(coins);
    }

    static int maxWinBottomDp2(int[] coins) {
        int[][] dp = new int[coins.length][coins.length];

        // Left indicates available coin on the left side.
        // Right indicates available coin on the right side.
        for (int left = dp.length - 1; left >= 0; left--) {
            for (int right = 0; right < dp.length; right++) {
                if (left == right) {
                    // For odd-numbered boards, set diagonal base case
                    dp[left][right] = coins[left];
                } else if (left + 1 == right) {
                    // For even-numbered boards, set diagonal base case
                    dp[left][right] = Math.max(coins[left], coins[right]);
                } else if (left > right) {
                    // Skip lower half of dp matrix
                    dp[left][right] = 0;
                } else {
                    int chooseLeft = coins[left] + Math.min(dp[left + 2][right], dp[left + 1][right - 1]);
                    int chooseRight = coins[right] + Math.min(dp[left][right - 2], dp[left + 1][right - 1]);

                    dp[left][right] = Math.max(chooseLeft, chooseRight);
                }
            }
        }

        return dp[0][coins.length - 1];
    }

    static int maxWinBottomDp(int[] arr, int n)
    {
        // Create a table to store solutions of subproblems
        int[][] table = new int[n][n];
        int gap, i, j, x, y, z;

        // Fill table using above recursive formula. Note that the table
        // is filled in diagonal fashion (similar to http://goo.gl/PQqoS),
        // from diagonal elements to table[0][n-1] which is the result.
        for (gap = 0; gap < n; ++gap)
        {
            for (i = 0, j = gap; j < n; ++i, ++j)
            {
                // Here x is value of F(i+2, j), y is F(i+1, j-1) and
                // z is F(i, j-2) in above recursive formula
                x = ((i+2) <= j) ? table[i+2][j] : 0;
                y = ((i+1) <= (j-1)) ? table[i+1][j-1] : 0;
                z = (i <= (j-2))? table[i][j-2]: 0;

                table[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));
            }
        }
        printArray(table);
        return table[0][n-1];
    }

    static void printArray(int[][] arr) {
        for (int[] sub : arr) {
            System.out.println(Arrays.toString(sub));
        }
    }

    static int maxWinRecursive(int[] coins, int left, int right) {
        if (left == right) {
            return coins[left];
        } else if (left + 1 == right) {
            return Math.max(coins[left], coins[right]);
        }

        // p1 chooses right or left, why do we choose the minimum of what p2 chooses?
        // because p2 chooses the value that leaves us with the minimum.
        int chooseLeft = coins[left] + Math.min(maxWinRecursive(coins, left + 2, right), maxWinRecursive(coins, left + 1, right - 1));
        int chooseRight = coins[right] + Math.min(maxWinRecursive(coins, left, right - 2), maxWinRecursive(coins, left + 1, right - 1));

        return Math.max(chooseLeft, chooseRight);
    }
}
