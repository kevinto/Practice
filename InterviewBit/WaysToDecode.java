/**
 * Created by Kevin on 7/31/16.
 */
public class WaysToDecode {
    public static void main(String[] args) {
        String test1 = "11111";
        System.out.println(new WaysToDecode().numDecodings(test1));
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
}
