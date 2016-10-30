import java.util.Arrays;

/**
 * Created by Kevin on 10/30/16.
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        getLCS("xyz", "byz");
    }

    private static String getLCS(String str1, String str2) {
        System.out.println(getLCSBottomUpDp(str1, str2));
        return "";
    }

    private static String getLCSBottomUpDp(String str1, String str2) {
        int str1Len = str1.length();
        int str2Len = str2.length();
        int[][] dp = new int[str1Len + 1][str2Len + 1];
//        dp[str1Len + 1][str2Len + 1] = 0; // Taken care of in the loop


        for (int row = str1Len; row >= 0; row--) {
            for (int col = str2Len; col >= 0; col--) {
                if (row == str1Len || col == str2Len) {
                    dp[row][col] = 0;
                    continue;
                }

                if (str1.charAt(row) == str2.charAt(col)) {
                    dp[row][col] =  1 + dp[row + 1][col + 1];
                } else {
                    dp[row][col] =  Math.max(dp[row + 1][col], dp[row][col + 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        generatePath(str1, 0, str2, 0, dp, sb);

//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        return sb.toString();
    }

    private static void generatePath(String str1, int idx1, String str2, int idx2, int[][] dp, StringBuilder sb) {
        if (idx1 == dp.length - 1 || idx2 == dp[0].length - 1) {
        } else if (dp[idx1][idx2] == dp[idx1 + 1][idx2] + 1 &&
                dp[idx1][idx2] == dp[idx1][idx2 + 1] + 1) {
//            System.out.println("found change point at: " + idx1 + " " + idx2);
            sb.append(str1.charAt(idx1));
            generatePath(str1, idx1 + 1, str2, idx2 + 1, dp, sb);
        } else if (dp[idx1][idx2] == dp[idx1 + 1][idx2]) {
            generatePath(str1, idx1 + 1, str2, idx2, dp, sb);
        } else {
            generatePath(str1, idx1, str2, idx2 + 1, dp, sb);
        }
    }

    private static int getLCSRecursive(String str1, int idx1, String str2, int idx2) {
        if (idx1 == str1.length() || idx2 == str2.length()) {
            return 0;
        }

        if (str1.charAt(idx1) == str2.charAt(idx2)) {
            return 1 + getLCSRecursive(str1, idx1 + 1, str2, idx2 + 1);
        } else {
            return Math.max(getLCSRecursive(str1, idx1 + 1, str2, idx2),
                    getLCSRecursive(str1, idx1, str2, idx2 + 1));
        }
    }
}
