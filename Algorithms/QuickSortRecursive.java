import java.util.Arrays;

/**
 * Created by kevint on 4/27/2016.
 * Useful References:
 * http://stackoverflow.com/questions/15034897/in-place-quick-sort-has-on-or-ologn-space-complexity
 * http://geeksquiz.com/quick-sort/
 *
 * Alternatives to picking the pivot:
 *  1. Pick the first element of the array.
 *  2. Pick the last element of the array.
 *  3. Pick a random element as pivot.
 *  4. Pick median as pivot.
 *
 * Runtime analysis: O(n log(n))
 * Memory analysis: If it was just the space taken up in the original array
 *                  then the space complexity would be O(1)??. Since we are
 *                  using recursion and we recurse (log n) times, then the
 *                  memory on the stack would grow on the order of O(log(n))
 *                  in the average case. This assumes that we have a reasonable
 *                  pivot in the average case. In the worse case, the space
 *                  complexity is O(n). This is because we will do n recursive
 *                  calls? todo: why?
 *                  WHEN TALKING ABOUT THIS BE SURE TO MENTION EXTRA SPACE.
 *                  WE DON'T CARE ABOUT THE SPACE THE ARRAY TAKES UP.
 */
public class QuickSortRecursive {
    public static void main(String[] args) {
        int[] test1 = {3, 7, 8, 9, 1, 5};

        System.out.println("Before: " + Arrays.toString(test1));
        QSortRecursive qSort = new QSortRecursive();
        qSort.sort(test1, 0, test1.length - 1);
        System.out.println("After: " + Arrays.toString(test1));

        System.out.println("");

        int[] test2 = {10, 7, 8, 99, 1, 5, 100, 200, 40, 89, 60};
        System.out.println("Before: " + Arrays.toString(test2));
        qSort.sort(test2, 0, test2.length - 1);
        System.out.println("After: " + Arrays.toString(test2));
    }
}

class QSortRecursive {
    void sort(int arr[], int low, int high) {
        if (low < high) {
            // Find where we can split the two arrays
            int partitioningIdx = partition(arr, low, high);

            // Process the left side of the array
            sort(arr, low, partitioningIdx - 1);

            // Process the right side of the array
            sort(arr, partitioningIdx + 1, high);
        }
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high]; // Pivot to the rightmost element

        // i represents an element Idx that can be swapped
        // with a value that is greater than the pivot
        int i = low;

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        // i tracks the last element that was smaller than
        // the pivot. Here, we swap the pivot with the number
        // directly after tThe last number that was smaller
        // than the pivot. If the pivot is already in the
        // correct place, then i is already equal to high.
        swap(arr, i, high);

        return i;
    }

    void swap(int[] arr, int swapIdx1, int swapIdx2) {
        if (swapIdx1 == swapIdx2) {
            return;
        }

        int temp = arr[swapIdx1];
        arr[swapIdx1] = arr[swapIdx2];
        arr[swapIdx2] = temp;
    }
}