/**
 * Created by kevinto on 1/8/17.
 */
public class StringsInterleave {
    public static void main(String[] args) {
        System.out.println("1: " + getInter("acbd", "ab", "cd"));
        System.out.println("2: " + getInter("1234", "123", "123"));
        System.out.println("3: " + getInter("112233", "123", "123"));
        System.out.println("4: " + getInter("123456", "123456", ""));
        System.out.println("5: " + getInter("123456", "", "123456"));
        System.out.println("6: " + getInter("12345678", "1234", "5678"));
        System.out.println("7: " + getInter("12345678", "1233", "5678"));
    }

    public static boolean getInter(String inter, String s1, String s2) {
        // This is the recursive way
        //return getInter(inter, s1, s2, 0, 0) == inter.length();

        // This way uses dp[]
        return getInterDp(inter, s1, s2);
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

    public static int getInter(String inter, String s1, String s2, int s1Pos, int s2Pos) {
        if (s1Pos == s1.length() && s2Pos == s2.length()) {
            return 0;
        }

        int res = 0;
        if (s1Pos < s1.length() && s1.charAt(s1Pos) == inter.charAt(s1Pos + s2Pos)) {
            res = 1 + getInter(inter, s1, s2, s1Pos + 1, s2Pos);
        }

        if (s2Pos < s2.length() && s2.charAt(s2Pos) == inter.charAt(s1Pos + s2Pos)) {
            res = Math.max(res, 1 + getInter(inter, s1, s2, s1Pos, s2Pos + 1));
        }

        return res;
    }
}
