/**
 * Created by kevinto on 1/8/17.
 */
public class StringsInterleave {
    public static void main(String[] args) {
        System.out.println("1: " + getInter("acbd", "ab", "cd"));
        System.out.println("2: " + getInter("1234", "123", "123")); // should be false
        System.out.println("3: " + getInter("112233", "123", "123"));
        System.out.println("4: " + getInter("123456", "123456", ""));
        System.out.println("5: " + getInter("123456", "", "123456"));
        System.out.println("6: " + getInter("12345678", "1234", "5678"));
        System.out.println("7: " + getInter("12345678", "1233", "5678")); // should be false
    }

    public static boolean getInter(String inter, String s1, String s2) {
        // This is the recursive way
        // return getInterRecursive(inter, s1, s2, 0, 0) == inter.length();

        // This way uses dp[] space unoptimized
        // return getInterDp(inter, s1, s2);

        // This way uses dp space optimized
        // return getInterDpSpaceOptimized(inter, s1, s2);

        // This way uses recursion that returns boolean
        // return getInterRecursive2(inter, s1, s2, 0, 0);

        // This way uses dp space unoptimized, but the dp table contains boolean values
        return getInterDp2(inter, s1, s2);
    }

    public static boolean getInterDpSpaceOptimized(String inter, String s1, String s2) {
        if (inter == null || s1 == null || s2 == null || s1.length() + s2.length() != inter.length()) {
            return false;
        }

        int s1Len = s1.length();
        int s2Len = s2.length();
        int[] curr;
        int[] prev = new int[s2Len + 1];

        for (int s1Pos = s1Len; s1Pos >= 0; s1Pos--) {
            curr = new int[s2Len + 1];
            for (int s2Pos = s2Len; s2Pos >= 0; s2Pos--) {
                if (s1Pos == s1Len && s2Pos == s2Len) {
                    continue;
                }

                int res = 0;
                if (s1Pos < s1Len && s1.charAt(s1Pos) == inter.charAt(s1Pos + s2Pos)) {
                    res = 1 + prev[s2Pos];
                }

                if (s2Pos < s2Len && s2.charAt(s2Pos) == inter.charAt(s1Pos + s2Pos)) {
                    res = Math.max(res, 1 + curr[s2Pos + 1]);
                }

                curr[s2Pos] = res;
            }
            prev = curr;
        }

        return prev[0] == inter.length();
    }

    public static boolean getInterDp(String inter, String s1, String s2) {
        if (inter == null || s1 == null || s2 == null || s1.length() + s2.length() != inter.length()) {
            return false;
        }

        int s1Len = s1.length();
        int s2Len = s2.length();
        int[][] dp = new int[s1Len + 1][s2Len + 1];

        for (int s1Pos = s1Len; s1Pos >= 0; s1Pos--) {
            for (int s2Pos = s2Len; s2Pos >= 0; s2Pos--) {
                if (s1Pos == s1Len && s2Pos == s2Len) {
                    continue;
                }

                int res = 0;
                if (s1Pos < s1Len && s1.charAt(s1Pos) == inter.charAt(s1Pos + s2Pos)) {
                    res = 1 + dp[s1Pos + 1][s2Pos];
                }

                if (s2Pos < s2Len && s2.charAt(s2Pos) == inter.charAt(s1Pos + s2Pos)) {
                    res = Math.max(res, 1 + dp[s1Pos][s2Pos + 1]);
                }

                dp[s1Pos][s2Pos] = res;
            }
        }

        return dp[0][0] == inter.length();
    }

    public static int getInterRecursive(String inter, String s1, String s2, int s1Pos, int s2Pos) {
        if (s1Pos == s1.length() && s2Pos == s2.length()) {
            return 0;
        }

        int res = 0;
        if (s1Pos < s1.length() && s1.charAt(s1Pos) == inter.charAt(s1Pos + s2Pos)) {
            res = 1 + getInterRecursive(inter, s1, s2, s1Pos + 1, s2Pos);
        }

        if (s2Pos < s2.length() && s2.charAt(s2Pos) == inter.charAt(s1Pos + s2Pos)) {
            res = Math.max(res, 1 + getInterRecursive(inter, s1, s2, s1Pos, s2Pos + 1));
        }

        return res;
    }

    // We dont need to return the length, we can just use true or false.
    public static boolean getInterRecursive2(String inter, String s1, String s2, int s1Pos, int s2Pos) {
        if (s1Pos == s1.length() && s2Pos == s2.length()) {
            return true;
        }

        boolean res = false;
        if (s1Pos < s1.length() && s1.charAt(s1Pos) == inter.charAt(s1Pos + s2Pos)) {
            res = getInterRecursive2(inter, s1, s2, s1Pos + 1, s2Pos);
        }

        if (s2Pos < s2.length() && s2.charAt(s2Pos) == inter.charAt(s1Pos + s2Pos)) {
            res |= getInterRecursive2(inter, s1, s2, s1Pos, s2Pos + 1);
        }

        return res;
    }

    // This version doesn't use the length, just returns a t/f value
    public static boolean getInterDp2(String inter, String s1, String s2) {
        if (inter == null || s1 == null || s2 == null || s1.length() + s2.length() != inter.length()) {
            return false;
        }

        int s1Len = s1.length();
        int s2Len = s2.length();
        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
        dp[s1Len][s2Len] = true;

        for (int s1Pos = s1Len; s1Pos >= 0; s1Pos--) {
            for (int s2Pos = s2Len; s2Pos >= 0; s2Pos--) {
                if (s1Pos == s1Len && s2Pos == s2Len) {
                    continue;
                }

                boolean res = false;
                if (s1Pos < s1Len && s1.charAt(s1Pos) == inter.charAt(s1Pos + s2Pos)) {
                    res = dp[s1Pos + 1][s2Pos];
                }

                if (s2Pos < s2Len && s2.charAt(s2Pos) == inter.charAt(s1Pos + s2Pos)) {
                    res |= dp[s1Pos][s2Pos + 1];
                }

                dp[s1Pos][s2Pos] = res;
            }
        }

        return dp[0][0];
    }
}
