import sun.rmi.server.Activation$ActivationSystemImpl_Stub;

import java.util.Arrays;

/**
 * Created by kevinto on 11/19/16.
 */
public class mergeSortForInts {
    public static void main(String[] args) {
        int[] test1 = { 4, 2, 3, 1 };
        System.out.println(Arrays.toString(MergeSort(test1)));

        int[] test2 = { 4, 2, 3 };
        System.out.println(Arrays.toString(MergeSort(test2)));
    }

    static int[] MergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        MergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    static void MergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = ((end - start) / 2) + start;
            MergeSort(arr, start, mid);
            MergeSort(arr, mid + 1, end);
            Merge(arr, start, end);
        }
    }

    static void Merge(int[] arr, int start, int end) {
        int[] helper = new int[end - start + 1];
        int mid = ((end - start) / 2) + start;
        int first = start;
        int second = mid + 1;
        int helperIdx = 0;

        while (first <= mid && second <= end) {
            if (arr[first] < arr[second]) {
                helper[helperIdx++] = arr[first++];
            } else {
                helper[helperIdx++] = arr[second++];
            }
        }

        while (first <= mid) {
            helper[helperIdx++] = arr[first++];
        }

        while (second <= end) {
            helper[helperIdx++] = arr[second++];
        }

        helperIdx = 0;
        for (int i = start; i <= end; i++) {
            arr[i] = helper[helperIdx++];
        }
    }
}
