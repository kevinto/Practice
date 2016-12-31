/**
 * Created by kevinto on 12/30/16.
 */
public class ImplementPow {
    public static void main(String[] args) {
        System.out.println(dpow(10, 4));
        System.out.println(dpow(2, 4));
        System.out.println(dpow(2, 3));
        System.out.println(dpow(2, -3));

        System.out.println();

        System.out.println(dpowI(10, 4));
        System.out.println(dpowI(2, 4));
        System.out.println(dpowI(2, 3));
        System.out.println(dpowI(2, -3));
    }

    public static double dpowI(double base, int power) {
        int pow = Math.abs(power);
        double result = 1;

        while (pow != 0) {
            if (pow % 2 == 1) {
                result *= base;
            }
            pow >>= 1;
            base *= base;
        }

        if (power < 0) {
            return (1 / result);
        } else {
            return result;
        }
    }

    public static double dpow(double base, int power) {
        if (power == 0) {
            return 1;
        } else if (power == 1) {
            return base;
        }

        double retVal = 0;
        if (power % 2 == 0) {
            retVal = dpow(base, power / 2);
            retVal *= retVal;
        } else {
            retVal = dpow(base, Math.abs(power) - 1);
            retVal *= base;
        }

        if (power < 0) {
            return (1 / retVal);
        } else {
            return retVal;
        }
    }
}
