import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kevint on 7/18/2016.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(-1 % 20);
        System.out.println(new Solution().pow(2, 3, 3));
    }

    public int pow(int x, int n, int d) {
        long a = x;
        long result = 1L;

        while (n > 0) {
            if (n % 2 == 1) {
                // n is odd.
                result *= a;
                result %= d;
            }
            a *= a;
            a %= d;
            n = n >> 1;
        }

        result = (result + d) % d;
        return (int) result;

    }
}
