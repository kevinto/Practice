/**
 * Created by kevinto on 1/25/17.
 */
public class LongestPalSubsequence {
    public static void main(String[] args) {
        String test = "abzmymaa";
        int res = getLongDp(test);
        System.out.println(res);
    }

    public static int getLongDp(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int len = str.length();
        int[][] dp = new int[len][len];
        initDp(dp);

        for (int row = len - 2; row >= 0; row--) {
            for (int col = row + 1; col < dp.length; col++) {
                if (str.charAt(row) == str.charAt(col)) {
                    dp[row][col] = 2 + dp[row + 1][col - 1];
                } else {
                    dp[row][col] = Math.max(dp[row][col - 1], dp[row + 1][col]);
                }
            }
        }

        return dp[0][dp.length - 1];
    }

    public static void initDp(int[][] dp) {
        int row = 0;
        int col = 0;

        while (row < dp.length && col < dp[0].length) {
            dp[row][col] = 1;
            row++;
            col++;
        }
    }

    public static int getLongRecur(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        return getLongRecur(str, 0, str.length() - 1);
    }

    private static int getLongRecur(String str, int start, int end) {
        if (start >= end) {
            return 1;
        } else if (str.charAt(start) == str.charAt(end)) {
            return 2 + getLongRecur(str, start + 1, end - 1);
        }

        return Math.max(getLongRecur(str, start, end - 1),
                getLongRecur(str, start + 1, end));
    }
}
