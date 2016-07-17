import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Kevin on 7/16/16.
 */
public class KmessedArrSort {
    public static void main(String[] args) {
        int[] arr1 = { 7, 8, 5, 6, 100, 9 };
        int k1 = 2;
        new KmessedArrSort().heapSrt(arr1, k1);
        System.out.println(Arrays.toString(arr1));
    }

    public void heapSrt(int[] arr, int k) {
        if (arr.length <= 1) return;
        if (k > arr.length) return;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i <= k; i++) pq.add(arr[i]);

        int insertIdx = 0;
        int end = k;
        int len = arr.length;
        while (insertIdx < len) {
            arr[insertIdx] = pq.poll();
            insertIdx++;

            if (end < len - 1) {
                end++;
                pq.add(arr[end]);
            }
        }
    }
}
