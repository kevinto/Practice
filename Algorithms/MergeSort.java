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
 */

public class MergeSort {
    public static void main(String[] args) {
        int[] arr1 = { 2, 6, 3, 5, 1 };
        mergeSort(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = { 9, 2, 6, 3, 5, 1 };
        mergeSort(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));
    }

    public static void mergeSort(int arr[], int low, int high) {
        if ( low < high ) {
            // (high - low) can potentially cause overflow especially for
            // very large inputs.
            int mid = low + ((high - low) / 2);

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
    }

    // The first subarray is low...mid.
    // Theh second subarray is mid+1...high.
    public static void merge(int arr[], int low, int mid, int high) {
        int leftArrSize = mid - low + 1;
        int rightArrSize = high - mid;

        // Create temp arrays to hold the values to be
        // merged in the original array.
        int leftArr[] = new int[leftArrSize];
        int rightArr[] = new int[rightArrSize];

        // Copy both halfs to be merged from the original
        // array to the temp arrays
        for (int i = 0; i < leftArrSize; i++) {
            leftArr[i] = arr[low + i];
        }
        for (int i = 0; i < rightArrSize; i++) {
            rightArr[i] = arr[i + mid + 1];
        }

        // Merge section:
        int leftIndex = 0, rightIndex = 0;
        int originalIndex = low;
        while (leftIndex < leftArrSize && rightIndex < rightArrSize) {
            if (leftArr[leftIndex] <= rightArr[rightIndex]) {
                arr[originalIndex] = leftArr[leftIndex];
                leftIndex++;
            }
            else {
                arr[originalIndex] = rightArr[rightIndex];
                rightIndex++;
            }
            originalIndex++;
        }

        while(leftIndex < leftArrSize) {
            arr[originalIndex] = leftArr[leftIndex];
            originalIndex++;
            leftIndex++;
        }

        while(rightIndex < rightArrSize) {
            arr[originalIndex] = rightArr[rightIndex];
            originalIndex++;
            rightIndex++;
        }
    }
}