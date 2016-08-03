/**
 * Created by Kevin on 8/2/16.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int arr[] = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
//        int result = lis.longestSubsequenceWithActualSolution(arr);
        int result1 = lis.findLongestRecursive(arr);
//        System.out.println(result);
        System.out.println(result1);
    }

    // Pending questions:
    //    1. Does this go through all permutations of the substrings?
    //    2. What is the optimal substructure?
    //    3. What is the repeated work?
    public int findLongestRecursive(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, findLongestRecursive(arr, i + 1, i));
        }

        return max + 1; // Assuming I didnt count i.
    }

    public int findLongestRecursive(int[] arr, int next, int prev) {
        if (next >= arr.length) return 0;

        int res1 = 0;
        int res2 = 0;
        if (arr[next] > arr[prev]) {
            res1 = 1 + findLongestRecursive(arr, next + 1, next);
        }

        res2 = findLongestRecursive(arr, next + 1, prev);

        return Math.max(res1, res2);
    }
}
