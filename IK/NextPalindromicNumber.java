/**
 * Created by kevinto on 12/10/16.
 */
public class NextPalindromicNumber {
    public static void main(String[] args) {
        System.out.println(nextPalindrome(19));
    }

    public static int nextPalindrome(int num) {
        int[] numArr = convertToArr(num);

        if (allAre9s(numArr)) {
            int result = 1;
            for (int i = 0; i < numArr.length; i++) {
                result *= 10;
            }
            return result + 1;
        }

        return nextPalindromeHelper(numArr);
    }

    private static int nextPalindromeHelper(int[] numArr) {
        int mid = numArr.length / 2;
        boolean leftSmaller = false;
        int i = mid - 1;
        int j = (numArr.length % 2) == 1 ? mid + 1 : mid;

        // Expand until we reach a point where it isnt a pal anymore.
        while (i >= 0 && numArr[i] == numArr[j]) {
            i--;
            j++;
        }

        // Check if we need to increment the mid and expand later
        if (i < 0 || numArr[i] < numArr[j]) {
            leftSmaller = true;
        }

        // Mirror the left side to the right side
        while (i >= 0) {
            numArr[j] = numArr[i];
            i--;
            j++;
        }

        // increment mid and expand.
        if (leftSmaller) {
            int carry = 1;
            i = mid - 1;

            if (numArr.length % 2 == 1) {
                // Take care of incrementing mid if odd
                numArr[mid] += carry;
                carry = numArr[mid] / 10; // We take care of reaching 10 here.
                numArr[mid] %= 10;
                j = mid + 1;
            } else {
                j = mid;
            }

            while (i >= 0) {
                numArr[i] += carry;
                carry = numArr[i] / 10;
                numArr[i] %= 10;
                numArr[j++] = numArr[i--];
            }
        }

        // Convert out result to an integer
        int result = numArr[0];
        for (int curr = 0; curr < numArr.length - 1; curr++) {
            result *= 10;
            result += numArr[curr + 1];
        }
        return result;
    }

    private static int[] convertToArr(int num) {
        String numStr = Integer.toString(num);
        int[] result = new int[numStr.length()];

        for (int i = 0; i < numStr.length(); i++) {
            result[i] = numStr.charAt(i) - '0';
        }
        return result;
    }

    private static boolean allAre9s(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 9) {
                return false;
            }
        }

        return true;
    }
}
