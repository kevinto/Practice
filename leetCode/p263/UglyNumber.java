package p263;

/**
 * Created by kevin on 4/17/2016.
 */
public class UglyNumber {
    public static void main(String[] args) {
//        System.out.println(isUgly(14));
//        System.out.println(isUgly(214797179));
        System.out.println(isUgly(937351770));
//        System.out.println(numIsPrime(6));
//        System.out.println(getNextPrime(11));
    }

    public static boolean isUgly(int num) {
//        if (num == 1) return true;
//        if (num == 0) return false;
//        else if (num % 2 == 0) isUgly(num / 2);
        if (num == 1) {
            return true;
        }

        boolean ugly = false;
        if ((num % 2 == 0) || (num % 3 == 0) || (num % 5 == 0)) {
            ugly = true;
        }

        // iterate through all the other prime numbers and check if it divides into
        int nextPrime = 7;
        while (nextPrime < num) {
            if (num % nextPrime == 0) {
                ugly = false;
                break;
            }
            nextPrime = getNextPrime(nextPrime);
        }

        return ugly;
    }

    public static int getNextPrime(int val) {
        // Assumes that what is passed in is prime already
        boolean isPrime = false;

        int i = val + 1;
        for (; i < 2 * val; i++) {
            if (numIsPrime(i)) {
                break;
            }
        }
        return i;
    }

    public static boolean numIsPrime(int val) {
        for (int i = val - 1; i > 1; i--) {
            if (val % i == 0) {
                return false;
            }
        }

        return true;
    }
}
