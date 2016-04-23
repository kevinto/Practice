package p343;

/**
 * Created by kevin on 4/22/2016.
 *
 * Problem here: https://leetcode.com/problems/integer-break/
 * - The best way to solve this problem is by writing out examples.
 *   It is best to start with n=2 all the way to n=10 and figure
 *   out if there are any patterns.
 *
 *   n=2, 1 + 1 = best product = 1
 *   n=3, 2 + 1 = best product = 2, 3%3 = 0
 *   n=4, 2 + 2 = best product = 4, 3%4 = 1
 *   n=5, 3 + 2 = best product = 6, 3%5 = 2
 *   n=6, 3 + 3 = best product = 9, 3%6 = 0
 *   n=7, 3 + 2 + 2 = best product = 12, 3%7 = 1
 *   n=8, 3 + 3 + 2 = best product = 18, 3%8 = 2
 *   n=9, 3 + 3 + 3 = best product = 27, 3%9 = 0
 *   n=10, 3 + 3 + 2 + 2 = best product = 27, 3%10 = 1
 *
 *   This leads us to 5 cases:
 *   1. if n=2, return 1
 *   2. if n=3, return 2
 *   3. if n % 3 == 0, return 3^(n/3)
 *   4. if n % 3 == 1, return 3^(n/3 - 1) * 2 * 2
 *   5. if n % 3 == 2, return 3^(n/3) * 2 // For this case, remember n/3 returns only the integer.
 */
public class intBreak {
    public static void main(String args[]) {
        System.out.println(integerBreak(3));
        System.out.println(integerBreak(4));
        System.out.println(integerBreak(5));
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        else if (n == 3) {
            return 2;
        }
        else if (n % 3 == 0) {
            return (int)Math.pow(3, n / 3);
        }
        else if (n % 3 == 1) {
            return (int)Math.pow(3, (n / 3) - 1) * 4;
        }
        else {
            return (int)Math.pow(3, n / 3) * 2;
        }
    }
}
