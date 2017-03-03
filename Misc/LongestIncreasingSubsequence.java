/**
 * Created by Kevin on 8/2/16.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int arr[] = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
//        int arr[] = {23,24,10,25};
        int result1 = lis.findLongestRecursivePrevNext(arr);
        System.out.println("brute force: " + result1);
        int result2 = lis.findLongestDPBottomUpTushar(arr);
        System.out.println("optimal dp from Roy Tushar: " + result2);
        int result3 = lis.lisProperDp(arr);
        System.out.println("optimal dp based off the geeksforgeeks recursion: " + result3);
        lis.lisPath(arr);

        lisProperRecursion(arr);
        geeks4GeeksLisBottomUp(arr);

        // Tests that the lis ends in the middle of the input.
//        int[] arr2 = {1, 2, -1};
//        System.out.println("arr2 max: " + geeks4GeeksLisBottomUp(arr2));
    }

    // This is my own implementation based on the geeks for
    // geeks recursive implementation.
    public int lisProperDp(int[] arr) {
        int finalMax = 0;
        int[] dp = new int[arr.length + 1];

        // len represents the len from the beginning.
        for (int len = 1; len <= arr.length; len++) {
            dp[len] = 1; // Every element has a start length of at least 1

            // we don't actually do this loop until len is 2.
            // Check every element before the end of the len.
            // If the condition is satisfied, then reuse the value we have
            // in our dp table. we are using dp[i+1] because our dp table is
            // represented as distance from the beginning, so the saved value
            // for a specific element is dp[i+1].
            for (int i = 0; i < len - 1; i++) {
                if (arr[i] < arr[len - 1]) {
                    dp[len] = Math.max(dp[len], dp[i + 1] + 1);
                }
            }
            finalMax = Math.max(finalMax, dp[len]);
        }

        return finalMax;
    }

    // My own implementation of dp path finding.
    public int[] lisPath(int[] arr) {
        int[] path = new int[arr.length + 1];
        int[] dp = new int[arr.length + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            path[i] = i;
        }

        for (int len = 0; len < dp.length; len++) {
            for (int subLen = 0; subLen < len - 1; subLen++) {
                int subMax = dp[subLen + 1];
                if (arr[subLen] < arr[len - 1] && subMax + 1 > dp[len]) {
                    dp[len] = subMax + 1;
                    path[len - 1] = subLen;
                }
            }
        }

        int dpMaxIdx = 0;
        int dpMax = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dpMax < dp[i]) {
                dpMax = dp[i];
                dpMaxIdx = i;
            }
        }

        System.out.print("Printing the max path from lisPath: ");
        int pathIdx = dpMaxIdx - 1;
        while (pathIdx != path[pathIdx]) {
            System.out.print(arr[pathIdx] + " ");
            pathIdx = path[pathIdx];
        }
        System.out.print(arr[pathIdx] + " ");
        System.out.println();
        return path;
    }

    // This implementation came from the Tushar video
    public int findLongestDPBottomUpTushar(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        // Init table
        int[] table = new int[arr.length];
        int[] path = new int[arr.length]; // Tracks the path to the current element
        for (int i = 0; i < table.length; i++) {
            table[i] = 1;
            path[i] = i;
        }

        // Populate table
        // Outer loop goes through smaller lengths to bigger lengths. We have to
        // start at length 1.
        for (int subLen = 1; subLen < arr.length; subLen++) {
            // Inner loop goes through the smaller length within the
            // given max length of the outer loop
            for (int prevSubLen = 0; prevSubLen < subLen; prevSubLen++) {
                if (arr[prevSubLen] < arr[subLen] && table[prevSubLen] + 1 > table[subLen]) {
                    table[subLen] = table[prevSubLen] + 1;
                    path[subLen] = prevSubLen;
                }

                if (arr[prevSubLen] < arr[subLen]) {
                    table[subLen] = Math.max(table[subLen], table[prevSubLen] + 1);
                }
            }
        }

        // Find the max len of the subseq
        int maxIndex = 0;
        for (int i = 1; i < table.length; i++) {
            if (table[i] > table[maxIndex]) {
                maxIndex = i;
            }
        }

        // Print actual solution
        int curr;
        int next = maxIndex;
        do {
            // We have curr here to prevent the infinite loop.
            // curr can only equal next 1 time before this do while breaks.
            curr = next;
            System.out.print(arr[curr] + " ");
            next = path[next];
        } while (curr != next);
        System.out.println();

        return table[maxIndex];
    }

    private static int maxLen;
    private static void lisProperRecursion(int[] nums) {
        maxLen = 0;
        lisProperRecursion(nums, nums.length);
        System.out.println("geeksforgeeks recursive: " + maxLen);
    }

    // Go through each element and say that element is the end of
    // the lis. Make a recursive call the portion between array
    // start and that element. If the current element value is
    // less than the "last" value, then we check if the lis
    // ending at the current element + 1 has a greater length
    // the previously found element.
    // - PROS: This way is better than the other recursive method
    //         because the params are simpler. This way is also
    //         easier to convert to a DP solution because don't have
    //         to deal with extra parameters.
    private static int lisProperRecursion(int[] nums, int length) { // length here is the 0-based length.
        if (length == 1) {
            return 1;
        }

        // Notice we pass in the length and not the index of the last element we want to look at.
        // That is why in order to get the last element you have to do num[length - 1].
        // If the currlength is truely the length, then it has to include the last element.
        // this loop does 2 things:
            // Gets the best sublength starting at the smallest length to the current length
            // Adds the sub length to the current length only if that interim value is less than the last value.
        // Note: This len means len from the end...
        int currentLisLength = 1;
        for (int i = 0; i < length - 1; i++) {
            // Get the len of the lis ending at i
            int subLis = lisProperRecursion(nums, i + 1);

            // The '1 +' is saying that the current element is added
            // to the current lis.
            if (nums[i] < nums[length - 1]) {
                currentLisLength = Math.max(currentLisLength, 1 + subLis);
            }
        }
        maxLen = Math.max(maxLen, currentLisLength);

        return currentLisLength;
    }

    private static int geeks4GeeksLisBottomUp(int[] arr) {
        int dp[] = new int[arr.length];

        // Each element has a initial lis of 1
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = 0; i < dp.length; i++) {
//            System.out.println("i = " + i);
            for (int j = 0; j <= i; j++) {
//                System.out.println("j = " + j);
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int val : dp) {
            max = Math.max(max, val);
        }

        System.out.println("geeksforgeeks dp: " + max);
        return max;
    }

    // Pending questions:
    //    1. Does this go through all permutations of the substrings?
    //          This implementation works by keeping track of the max
    //          subarray length. If we find an increasing number based
    //          on the previous array number, then we add 1 to the length.
    //          If we dont find an increasing number, than we keep track
    //          of our last valid increasing number and we recurse to the
    //          next value.
    //    2. What is the optimal substructure?
    //          Optimal substructure means that we can solve the problem
    //          by its subproblems.
    //          -- In order to find the max length, we need the max length
    //          the smaller subarrays.
    //
    //          Recurrence relation:
    //          L(i) = {
    //                   L(i) = 1, if there is no such j
    //                   1 + Max ( L(j) ), where j < i and arr[j] < arr[i]
    //                 }
    //    3. What is the repeated work?
    //          When we reach a certain array element the second time during
    //          recursive stack, we already calculated the max subarray of that
    //          array position.
    public int findLongestRecursivePrevNext(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, findLongestRecursivePrevNext(arr, i, i + 1));
        }

        return max;
    }

    private int findLongestRecursivePrevNext(int[] arr, int prev, int next) {
        if (next >= arr.length) return 1;

        int res1 = 0;
        if (arr[next] > arr[prev]) {
            // We make prev -> next because we know the curr satisfies our condition
            res1 = 1 + findLongestRecursivePrevNext(arr, next, next + 1);
        }

        int res2 = findLongestRecursivePrevNext(arr, prev, next + 1);

        return Math.max(res1, res2);
    }

    // This implementation fail for the following test case:
    // int[] arr = {23,24,10};
    // - This only sets max length when we include the last element in the subsequence.
    //   This is why a maxLen class variable is needed
    public static int findLongestRecursiveWrong(int[] arr) {
        return findLongestRecursiveWrong(arr, arr.length);
    }

    public static int findLongestRecursiveWrong(int[] arr, int len) {
        if (len == 1) {
            return 1;
        }

        int maxLen = 0;
        //int currMax = 0;

        for (int i = 0; i < len - 1; i++) {
            int subMax = findLongestRecursiveWrong(arr, i);
            if (arr[i] < arr[len - 1]) {
                //currMax = Math.max(currMax, 1 + subMax);
                maxLen = Math.max(maxLen, 1 + subMax);
            }
            //maxLen = Math.max(currMax, maxLen);
        }

        return maxLen;
    }
}
