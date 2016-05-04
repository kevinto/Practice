import java.util.Arrays;

/**
 * Created by kevint on 4/27/2016.
 */
public class QuickSortRecursive {
    public static void main(String[] args) {
        int[] test1 = {10, 7, 8, 9, 1, 5};

        System.out.println("Before: " + Arrays.toString(test1));
        QSort qSort = new QSort();
        qSort.sort(test1, 0, test1.length - 1);
        System.out.println("After: " + Arrays.toString(test1));
    }
}

class QSort {
    void sort(int arr[], int low, int high) {
        if (low < high) {
            int partitioningIdx = partition(arr, low, high);

            sort(arr, low, partitioningIdx - 1);
            sort(arr, partitioningIdx + 1, high);
        }
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // Tracks all the elements that
                           // are less than the pivot.

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;

                swap(arr, i, j);
            }
        }

        // i tracks the last element that was smaller than
        // the pivot. Here, we swap the pivot with the number
        // directly after the last number that was smaller
        // than the pivot.
        swap(arr, i + 1, high);

        return i + 1;
    }

    void swap(int[] arr, int swapIdx1, int swapIdx2) {
        int temp = arr[swapIdx1];
        arr[swapIdx1] = arr[swapIdx2];
        arr[swapIdx2] = temp;
    }
}