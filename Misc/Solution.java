public class Solution {
    public static void main(String[] args) {
        boolean a = false;
        a |= true;

        System.out.println(new Solution().atoi(" 7 U 0 T7165  0128862 089 39 5"));
        System.out.println(new Solution().atoi(" 7 U 0 T7165  0128862 089 39 5"));
        System.out.println(new Solution().atoi(" 7 U 0 T7165  0128862 089 39 5"));
    }

    public int atoi(final String a) {
        if (a.length() == 0) return 0;

        String orig = a.trim();
        char first = orig.charAt(0);
        if (first - '0' <= 0 || first - '0' > 9) {
            return 0;
        }

        double result = 0;
        int i = 0;
        while (i < orig.length() && orig.charAt(i) - '0' >= 0 && orig.charAt(i) -'0' <= 9) {
            result = (result * 10) + (orig.charAt(i) - '0');
            i++;
        }

        if (result > Integer.MAX_VALUE) {
            return 0;
        } else if (result < Integer.MIN_VALUE) {
            return 0;
        }

        return (int)result;
    }

}
