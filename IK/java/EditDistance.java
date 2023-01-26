/**
 * Created by kevinto on 2/12/17.
 */
public class EditDistance {
    static int levenshteinDistance(String w1, String w2) {
        // TODO: Input checking

        return helperDpMemOptimized(w1, w2);

        // !---- These 2 implementations are just for reference ---- !
        // return helperRecursive(w1, 0, w2 ,0);
        // return helperDp(w1, w2);
    }

    static int helperDpMemOptimized(String w1, String w2) {
        int w1Len = w1.length();
        int w2Len = w2.length();
        int[] curr = null;
        int[] prev = new int[w2Len + 1];

        // Base cases for the last row
        int sum = 1;
        for (int i = prev.length - 2; i >= 0; i--) {
            prev[i] = sum;
            sum++;
        }

        // Dp Table: Row represents w1 indicies, Col represents w2 indicies
        sum = 1;
        for (int i = w1Len - 1; i >= 0; i--) {
            curr = new int[w2Len + 1];

            for (int j = w2Len; j >= 0; j--) {
                if (j == w2Len) {
                    curr[j] = sum;
                    sum++;
                    continue;
                }

                curr[j] = Integer.MAX_VALUE;
                if (w1.charAt(i) == w2.charAt(j)) {
                    curr[j] = Math.min(curr[j], prev[j + 1]);
                } else {
                    // Not equal, so lets try replace
                    curr[j] = Math.min(curr[j], prev[j + 1] + 1);
                }

                // Try deleting
                curr[j] = Math.min(curr[j], prev[j] + 1);

                // Try inserting
                curr[j] = Math.min(curr[j], curr[j + 1] + 1);
            }
            prev = curr;
        }

        return prev[0];
    }

    static int helperDp(String w1, String w2) {
        int w1Len = w1.length();
        int w2Len = w2.length();
        int[][] dp = initDp(w1Len, w2Len);

        // Dp Table: Row represents w1 indicies, Col represents w2 indicies
        for (int i = w1Len - 1; i >= 0; i--) {
            for (int j = w2Len - 1; j >= 0; j--) {
                dp[i][j] = Integer.MAX_VALUE;

                if (w1.charAt(i) == w2.charAt(j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j + 1]);
                } else {
                    // Not equal, so lets try replace
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j + 1] + 1);
                }

                // Try deleting
                dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);

                // Try inserting
                dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
            }
        }

        return dp[0][0];
    }

    static int[][] initDp(int rows, int cols) {
        int[][] dp = new int[rows + 1][cols + 1];
        //dp[rows][cols] = 0;

        // Base cases for the last column
        int sum = 1;
        for (int i = dp.length - 2; i >= 0; i--) {
            dp[i][dp[0].length - 1] = sum;
            sum++;
        }

        // Base cases for the last row
        sum = 1;
        for (int j = dp[0].length - 2; j >= 0; j--) {
            dp[dp.length - 1][j] = sum;
            sum++;
        }

        return dp;
    }

    // Recursive Implementation is here just as a reference.
    static int helperRecursive(String w1, int w1Pos, String w2, int w2Pos) {
        if (w1Pos >= w1.length() && w2Pos >= w2.length()) {
            return 0;
        } else if (w1Pos >= w1.length()) {
            // w2 is shorter, we have to delete the rest of the chars in w1
            return helperRecursive(w1, w1Pos, w2, w2Pos + 1) + 1;
        } else if (w2Pos >= w2.length()) {
            // w1 is shorter, we have to insert the rest of the chars in w1
            return helperRecursive(w1, w1Pos + 1, w2, w2Pos) + 1;
        }

        int min = Integer.MAX_VALUE;
        if (w1.charAt(w1Pos) == w2.charAt(w2Pos)) {
            min = Math.min(min, helperRecursive(w1, w1Pos + 1, w2, w2Pos + 1));
        } else {
            // Not equal, so lets try replace
            min = Math.min(min, helperRecursive(w1, w1Pos + 1, w2, w2Pos + 1) + 1);
        }

        // Try deleting
        min = Math.min(min, helperRecursive(w1, w1Pos + 1, w2, w2Pos) + 1);

        // Try inserting
        min = Math.min(min, helperRecursive(w1, w1Pos, w2, w2Pos + 1) + 1);

        return min;
    }
}
