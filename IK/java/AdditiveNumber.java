/**
 * Created by kevinto on 1/10/17.
 */
public class AdditiveNumber {
    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("112358") == true);
        System.out.println(isAdditiveNumber("123") == true);
        System.out.println(isAdditiveNumber("1023") == false);
        System.out.println(isAdditiveNumber("101") == true);
        System.out.println(isAdditiveNumber("121224036") == false);
    }

    // ------------------ My iterative implementation with multiple pointers ------------------------
    public static boolean isAdditiveNumber(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        int len = str.length();
        for (int firstEnd = 0; firstEnd < len; firstEnd++) {
            int firstNum = getNum(str, 0, firstEnd);

            for (int secondEnd = firstEnd + 1; secondEnd < len; secondEnd++) {
                int secondNum = getNum(str, firstEnd + 1, secondEnd);

                for (int thirdEnd = secondEnd + 1; thirdEnd < len; thirdEnd++) {
                    int thirdNum = getNum(str, secondEnd + 1, thirdEnd);

                    if (containsLeadingZeros(str, 0, firstEnd)
                            || containsLeadingZeros(str, firstEnd + 1, secondEnd)
                            || containsLeadingZeros(str, secondEnd + 1, thirdEnd)) {
                        continue;
                    }

                    if (firstNum + secondNum == thirdNum
                            && (thirdEnd == len - 1 || restValid(str, firstEnd + 1, secondEnd + 1, thirdEnd))) {

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean restValid(String str, int firstStart, int secondStart, int secondEnd) {
        int firstNum = getNum(str, firstStart, secondStart - 1);
        int secondNum = getNum(str, secondStart, secondEnd);
        int len = str.length();

        for (int thirdEnd = secondEnd + 1; thirdEnd < len; thirdEnd++) {
            int thirdNum = getNum(str, secondEnd + 1, thirdEnd);

            if (containsLeadingZeros(str, secondEnd + 1, thirdEnd)) {
                return false;
            }

            if (firstNum + secondNum == thirdNum) {
                firstNum = secondNum;
                secondNum = thirdNum;
                secondEnd = thirdEnd;

                if (thirdEnd == len - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean containsLeadingZeros(String str, int start, int end) {
        if (start == end) {
            // Only 1 letter, there can't be leading zeros.
            return false;
        } else {
            return str.charAt(start) == '0';
        }
    }

    public static int getNum(String str, int start, int end) {
        int result = str.charAt(start) - '0';
        for (int i = start + 1; i <= end; i++) {
            result *= 10;
            result += str.charAt(i) - '0';
        }
        return result;
    }
    // ------------------ END - My iterative implementation with multiple pointers -------------------

    /* Slightly cleaner coding exercise impl
    public static boolean isAdditiveNumber(String str) {
        // TODO: Input checking

        int len = str.length();
        for (int first = 0; first < len; first++) {
            int firstNum = Integer.parseInt(str.substring(0, first + 1));

            for (int second = first + 1; second < len; second++) {
                int secondNum = Integer.parseInt(str.substring(first + 1, second + 1));

                for (int third = second + 1; third < len; third++) {
                    int thirdNum = Integer.parseInt(str.substring(second + 1, third + 1));

                    boolean isValid = false;
                    if (firstNum + secondNum == thirdNum) {
                        isValid = validRest(str, secondNum, thirdNum, third + 1);
                    }

                    if (isValid) {
                        return isValid;
                    }
                }
            }
        }

        return false;
    }

    public static boolean validRest(String str, int firstVal, int secondVal, int thirdStart) {
        int len = str.length();
        for (int third = thirdStart; third < len; third++) {
            int thirdVal = Integer.parseInt(str.substring(thirdStart, third + 1));
            if (firstVal + secondVal != thirdVal) {
                return false;
            }

            firstVal = secondVal;
            secondVal = thirdVal;
            thirdStart = third + 1;
        }

        return true;
    }
     */
}
