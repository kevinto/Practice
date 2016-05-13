package p326;

/**
 * Created by kevint on 5/12/2016.
 * Link: https://leetcode.com/problems/power-of-three/
 */
public class PowerOfThree {
    public static void main(String[] args) {
        System.out.println("0: " + isPowerOfThree(0));
        System.out.println("1: " + isPowerOfThree(1));
        System.out.println("2: " + isPowerOfThree(2));
        System.out.println("3: " + isPowerOfThree(3));
        System.out.println("4: " + isPowerOfThree(4));
        System.out.println("5: " + isPowerOfThree(5));
        System.out.println("6: " + isPowerOfThree(6));
        System.out.println("9: " + isPowerOfThree(9));
        System.out.println("27: " + isPowerOfThree(27));
    }

    public static boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }

        int remainder = -1;
        while (n != 0 && n != 1) {
            remainder = n % 3;
            n /= 3;
        }

        return remainder == 0;
    }
}
