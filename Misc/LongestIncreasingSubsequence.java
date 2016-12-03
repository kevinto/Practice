import java.util.HashSet;

/**
 * Created by Kevin on 8/2/16.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int arr[] = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
        int result1 = lis.findLongestRecursive(arr);
        System.out.println("brute force: " + result1);
        int result2 = lis.findLongestDPBottomUp(arr);
        System.out.println("optimal: " + result2);
        int result3 = lis.longestSubsequenceWithActualSolution(arr);
        System.out.println("optimal roy: " + result3);

        geeks4GeeksLis(arr);
    }

    public int findLongestDPBottomUp(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        // Init table
        int[] table = new int[arr.length];
        int[] path = new int[arr.length]; // Tracks the path to the current element
        for (int i = 0; i < table.length; i++) {
            table[i] = 1;
            path[i] = i;
        }

        // Populate table
        for (int i = 1; i < arr.length; i++) {
            for (int k = 0; k < i; k++) {
                if (arr[i] > arr[k] && table[k] + 1 > table[i]) {
                    table[i] = table[k] + 1;
                    path[i] = k;
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

    public int longestSubsequenceWithActualSolution(int arr[]){
        int T[] = new int[arr.length];
        int actualSolution[] = new int[arr.length];
        for(int i=0; i < arr.length; i++){
            T[i] = 1;
            actualSolution[i] = i;
        }

        for(int i=1; i < arr.length; i++){
            for(int j=0; j < i; j++){
                if(arr[i] > arr[j]){
                    if(T[j] + 1 > T[i]){
                        T[i] = T[j] + 1;
                        //set the actualSolution to point to guy before me
                        actualSolution[i] = j;
                    }
                }
            }
        }

        //find the index of max number in T
        int maxIndex = 0;
        for(int i=0; i < T.length; i++){
            if(T[i] > T[maxIndex]){
                maxIndex = i;
            }
        }

        //lets print the actual solution
        int t = maxIndex;
        int newT = maxIndex;
        do{
            t = newT;
            System.out.print(arr[t] + " ");
            newT = actualSolution[t];
        }while(t != newT);
        System.out.println();

        return T[maxIndex];
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
    public int findLongestRecursive(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, findLongestRecursive(arr, i + 1, i));
        }

        return max;
    }

    private int findLongestRecursive(int[] arr, int next, int prev) {
        if (next >= arr.length) return 1;

        int res1 = 0;
        if (arr[next] > arr[prev]) {
            // We make prev -> next because we know the curr satisfies our condition
            res1 = 1 + findLongestRecursive(arr, next + 1, next);
        }

        int res2 = findLongestRecursive(arr, next + 1, prev);

        return Math.max(res1, res2);
    }

    private static int maxLen;
    private static void geeks4GeeksLis(int[] nums) {
        maxLen = 0;
        lisHelper(nums, nums.length);
        System.out.println("geeksforgeeks recursive: " + maxLen);
    }

    // Go through each element and say that element is the end of
    // the lis. Make a recursive call the portion between array
    // start and that element. If the current element value is
    // less than the "last" value, then we check if the lis
    // ending at the current element + 1 has a greater length
    // the previously found element.
    // - PROS: This way is better than the other recursive method
    //         because the params are simpler.
    private static int lisHelper(int[] nums, int length) {
        if (length == 1) {
            return 1;
        }

        int currentLisLength = 1;
        for (int i = 0; i < length - 1; i++) {
            // Get the len of the lis ending at i
            int subLis = lisHelper(nums, i);

            // The '1 +' is saying that the current element is added
            // to the current lis.
            if (nums[i] < nums[length - 1] && currentLisLength < (1 + subLis)) {
                currentLisLength = 1 + subLis;
            }
        }

        if (maxLen < currentLisLength) {
            maxLen = currentLisLength;
        }

        return currentLisLength;
    }
}
