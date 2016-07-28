import java.util.Arrays;

/**
 * Created by kevint on 7/19/2016.
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr1 = { 4, 2, 1, 5 };
//        new InsertionSort().insSort(arr1);
        new InsertionSort().insSortShort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = { 5, 4, 2, 1 };
//        new InsertionSort().insSort(arr2);
        new InsertionSort().insSortShort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = { 1, 2, 3, 4 };
//        new InsertionSort().insSort(arr3);
        new InsertionSort().insSortShort(arr3);
        System.out.println(Arrays.toString(arr3));
    }

    public void insSortShort(int[] arr) {
        int len = arr.length;
        for (int j = 1; j < len; j++) {
            int i = j - 1;
            int key = arr[j];

            while (i > -1 && arr[i] > key) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }

    public void insSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int sortedIdx = 0;
        for (; sortedIdx < arr.length; sortedIdx++) {
            int nextIdx = sortedIdx + 1;
            if (nextIdx >= arr.length) break;

            int tempIdx = sortedIdx;
            while (tempIdx >= 0 && arr[tempIdx] > arr[nextIdx]) {
                swap(arr, tempIdx, nextIdx);
                nextIdx = tempIdx;
                tempIdx--;
            }
        }
    }

    private void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
}
