import java.util.*;

/*
 * Runtime complexity: O(n log(n))
 * More info: https://www.khanacademy.org/computing/computer-science/algorithms/merge-sort/a/analysis-of-merge-sort
 * - If we draw out the recursion tree we can see that each level
 *   takes n amount of work to merge. There is (log n) amount of
 *   levels, we get (n log n).
 *
 * Space complexity: O(n)
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
        Integer[] arr = { 2, 6, 3, 5, 1 };

        mergeSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    @SuppressWarnings("rawtypes")
    private static Comparable[] mergeSort(Comparable[] list) {
        if (list.length <= 1) {
            return list;
        }

        Comparable[] first = new Comparable[list.length / 2];
        Comparable[] second = new Comparable[list.length - first.length];
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);

        mergeSort(first);
        mergeSort(second);

        merge(first, second, list);
        return list;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void merge(Comparable[] first, Comparable[] second, Comparable[] result) {
        int iFirst = 0;
        int iSecond = 0;
        int iMerged = 0;

        // we have temp to hold the array content in order to
        // safely copy over elements to the original array.
        while(iFirst < first.length && iSecond < second.length) {
            if (first[iFirst].compareTo(second[iSecond]) < 0) {
                result[iMerged] = first[iFirst];
                iFirst++;
            }
            else {
                result[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }

        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }
}