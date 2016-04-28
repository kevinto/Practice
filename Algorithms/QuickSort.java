import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by kevint on 4/27/2016.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] test1 = {10, 7, 8, 9, 1, 5};

        QSort qSort = new QSort();
        qSort.sort(test1, 0, test1.length - 1);
        System.out.println(Arrays.toString(test1));
    }
}

class QSort {
    void sort(int arr[], int low, int high) {
        if (low < high) {
            int partititioningIdx = partition(arr, low, high);

            sort(arr, low, partititioningIdx - 1);
            sort(arr, partititioningIdx + 1, high);
        }
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // Idx of smaller element
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}