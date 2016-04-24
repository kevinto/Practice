import java.math.BigInteger;

/**
 * Created by Kevin on 4/24/16.
 */
public class NumRoutes {
    public static void main(String[] args) {
        System.out.println(numberOfRoutes(3, 2));
        System.out.println(numberOfRoutes(6, 6));
    }

    public static BigInteger numberOfRoutes(long n, long m) {
        BigInteger upper = BigInteger.ONE;
        long highest = (m > n) ? m : n;
        long lowest = (m < n) ? m : n;

        for (long i = highest + 1; i <= highest + lowest; i++) {
            upper = upper.multiply(BigInteger.valueOf(i));
        }

        return upper.divide(factorial(BigInteger.valueOf(lowest)));
    }

    public static BigInteger factorial(BigInteger number) {

        if (number.compareTo(BigInteger.ONE) == -1) {
            // number passed in is less than 1
            return BigInteger.ONE;
        }
        else {
            return number.multiply(factorial(number.subtract(BigInteger.ONE)));
        }
    }
}
