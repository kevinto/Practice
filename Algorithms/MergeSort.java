import java.util.*;

/*
 * Runtime complexity: O(n log(n))
 * More info: https://www.khanacademy.org/computing/computer-science/algorithms/merge-sort/a/analysis-of-merge-sort
 * - If we draw out the recursion tree we can see that each level
 *   takes n amount of work to merge. There is (log n) amount of
 *   levels, we get (n log n).
 *
 * Space complexity: O(n)
 * - On each
 *
 * Basic Structure:
 * 1. Find the middle point to divide the array into two halves.
 * 2. Recursively call merge sort on the first half.
 * 3. Recursively call merge sort on the second half.
 * 4. Merge the two halves from step 2 and 3.
 *
 *
 */

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = { 2, 6, 3, 5, 1 };

        mergeSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int arr[], int low, int high) {
        if ( low < high ) {
            // (high - low) can potentially cause overflow especially for
            // very large inputs.
            int mid = low + ((high - low) / 2);
        }
    }
}