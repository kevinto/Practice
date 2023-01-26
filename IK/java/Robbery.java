/**
 * Created by kevinto on 12/16/16.
 */
public class Robbery {
    public static void main(String[] args) {
        int[] arr1 = {6, 1, 2, 7};
        System.out.println("good recur: " + maxStolenValueGoodRecur(arr1, 0));
        System.out.println("bad recur: " + maxStolenValueBadRecur(arr1, 0, 0));
        System.out.println("dp: " + maxStolenValueDp(arr1));

        int[] arr2 = {100, 1, 100, 1000, 1, 100};
        System.out.println("good recur: " + maxStolenValueGoodRecur(arr2, 0));
        System.out.println("bad recur: " + maxStolenValueBadRecur(arr2, 0, 0));
        System.out.println("dp: " + maxStolenValueDp(arr2));
    }

    public static int maxStolenValueDp(int[] arr) {
        // Added 2 extra spaces to account for the + 2
        // in the recursive solution
        int[] dp = new int[arr.length + 2];
        for (int i = arr.length - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 2] + arr[i], dp[i + 1]);
        }

        return dp[0];
    }

    private static int maxStolenValueGoodRecur(int[] arr, int pos) {
        if (pos >= arr.length) {
            return 0;
        }

        return Math.max(maxStolenValueGoodRecur(arr, pos + 2) + arr[pos],
                maxStolenValueGoodRecur(arr, pos + 1));
    }

    // This solution is bad because it relies on 2 changing variables.
    // this is unncessary. the implementation above calculates the stolen
    // amount on the way back up the recursive stack.
    private static int maxStolenValueBadRecur(int[] arr, int pos, int stolen) {
        if (pos >= arr.length) {
            return stolen;
        }

        return Math.max(maxStolenValueBadRecur(arr, pos + 2, stolen + arr[pos]),
                maxStolenValueBadRecur(arr, pos + 1, stolen));
    }
}
