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

    // This solution cuts down the problem space sufficiently
    public static boolean isUgly(int num) {
        if (num == 1) {
            return true;
        }

        boolean result = false;
        if (num == 0) {
            return false;
        }
        else if (num % 2 == 0) {
            result = isUgly(num / 2);
        }
        else if (num % 3 == 0) {
            result = isUgly(num / 3);
        }
        else if (num % 5 == 0) {
            result = isUgly(num / 5);
        }

        return result;
    }

}
