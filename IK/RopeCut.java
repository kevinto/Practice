/**
 * Created by Kevin on 11/6/16.
 */
public class RopeCut {
    public static void main(String[] args) {
        System.out.println(maxProductFromCutPieces(58));
    }

    static int maxProductFromCutPieces(int len) {
        if (len < 2) {
            return 0;
        }

        // Cut at least once first
        int max = 1;
        for (int i = 1; i <= (len / 2); i++) {
            max = Math.max(max, i * maxProdHelper(len - i));
        }

        return max;
    }

    static int maxProdHelper(int len) {
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            for (int cut = 1; cut <= (i / 2); cut++) {
                dp[i] = Math.max(i, cut * dp[i - cut]);
            }
        }

        return dp[len];
    }
}
