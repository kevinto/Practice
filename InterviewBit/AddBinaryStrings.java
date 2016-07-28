/**
 * Created by Kevin on 7/23/16.
 */
public class AddBinaryStrings {
    public static void main(String[] args) {
        System.out.println(addB("100", "11"));
        System.out.println(addBinary("100", "11"));
    }

    // Explanation: We iterator through both strings at the same time and compare starting from the
    //              rightmost point. We keep track of the sum of the current points.
    //              sum = 0 means we add a 0 to the result.
    //              sum = 1 means we add a 1 to the result.
    //              sum = 2 means we add a 0 to the result and add to the carry.
    //              sum = 3 means we add a 1 to the result and add to the carry.
    //              the carry will keep track of the digit that moves to the next pos
    //              We use a bitwise & 1 to check whether we want to add a 1 to the
    //              result or not. a sum value of 3(11) and 1(1) means we want to add
    //              a 1 to the result. We will reverse the result be cause we are appending
    //              the rightmost results each time.
    public static String addBinary(String a, String b) {
        if (a == null || b == null) return "";
        int carry = 0;
        int first = a.length() - 1;
        int second = b.length() - 1;
        StringBuilder sb = new StringBuilder();

        while (first >= 0 || second >= 0) {
            int sum = carry;

            if (first >= 0) {
                sum += a.charAt(first);
                first--;
            }

            if (second >= 0) {
                sum += b.charAt(second);
                second--;
            }

            carry = sum >> 1;
            sum &= 1;
            sb.append(sum == 0 ? '0' : '1');
        }

        sb.reverse();
        return String.valueOf(sb);
    }

    public static String addB(String aStr, String bStr) {
        if (aStr == null || bStr == null) return "";
        int firstPtr = aStr.length() - 1;
        int secondPtr = bStr.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;

        // Iterate through both binary strings
        while (firstPtr >= 0 || secondPtr >= 0) {
            // Sum will track whether we have 0, 1 or 2
            int sum = carry;

            if (firstPtr >= 0) {
                sum += aStr.charAt(firstPtr) - '0';
                firstPtr--;
            }

            if (secondPtr >= 0) {
                sum += bStr.charAt(secondPtr) - '0';
                secondPtr--;
            }

            // There is only a carry when the sum is 11B.
            carry = sum >> 1;

            // sum will only be 1 if (0+1) or (1+0)
            sum &= 1;
            sb.append(sum == 0 ? '0' : '1');
        }

        if (carry > 0) {
            sb.append('1');
        }

        sb.reverse();
        return String.valueOf(sb);
    }
}
