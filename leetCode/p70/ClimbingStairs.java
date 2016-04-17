package p70;

/**
 * Created by kevin on 4/16/2016.
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }

    public static int climbStairs(int n) {
        int nMinus2 = 0;
        int nMinus1 = 1;

        if (n == 0) { return 0; }
        if (n == 1) { return 1; }

        for (int i = 0; i < n; i++) {
            int temp = nMinus2;
            nMinus2 = nMinus1;
            nMinus1 += temp;
        }

        return nMinus1;
    }
}
