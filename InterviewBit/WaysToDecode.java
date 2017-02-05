/**
 * Created by Kevin on 7/31/16.
 */
public class WaysToDecode {
    public static void main(String[] args) {
        String test1 = "11111";
        System.out.println(new WaysToDecode().numDecodings(test1));
        System.out.println(numDecodingsDp("1231124"));
    }

    public static int numDecodingsDp(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int[] dp = new int[str.length() + 1];
        dp[str.length()] = 1;

        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }

            if (str.charAt(i) - '0' > 0) {
                dp[i] = dp[i + 1];
            }

            if (i + 1 < str.length()
                    && ((str.charAt(i) - '0') * 10) + (str.charAt(i + 1) - '0') <= 26) {

                dp[i] += dp[i + 2];
            }
        }

        return dp[0];
    }

    public int numDecodings(String a) {
        if (a.length() == 0) {
            return 0;
        }

        char[] digits = a.toCharArray();

        return countDecodings(digits, digits.length);
    }

    private int countDecodings(char[] digits, int n) {
        int[] memo = new int[digits.length + 1];
        memo[0] = 1;
        memo[1] = 1;

        // This is like the fib seq because we are summing the count of ways
        // we can cut single characters out and ways we can cut 2 characters out.
        for (int i = 2; i <= n; i++) {
            memo[i] = 0;

            if (digits[i - 1] > '0') {
                memo[i] = memo[i - 1];
            }

            if (digits[i - 2] < '2' || (digits[i - 2] == '2' && digits[i - 1] < '7')) {
                memo[i] += memo[i - 2];
            }
        }

        return memo[n];
    }


    public int numDecodingsSlightlyDifferent(String str, int pos) {
        if (pos >= str.length()) {
            return 1;
        }

        int sum = 0;
        if (str.charAt(pos) - '0' > 0) {
            sum = numDecodingsSlightlyDifferent(str, pos + 1);
        }

        if (pos + 1 < str.length()
                && ((str.charAt(pos) - '0') * 10) + (str.charAt(pos + 1) - '0') <= 26) {

            sum += numDecodingsSlightlyDifferent(str, pos + 2);
        }

        return sum;
    }
}
