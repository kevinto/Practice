import java.util.*;
import java.util.LinkedList;

/**
 * Created by Kevin on 8/19/16.
 */
public class MinSlidingWindow {
    public static void main(String[] args) {
        int[] arr1 = { 2, 5, 1, 3, 2, 6 };
        System.out.println(minWindows(3, arr1));

        int[] arr2 = { 1, 2, 3, 4, 5, 6 };
        System.out.println(minWindows(3, arr2));
    }

    public static ArrayList<Integer> minWindows(int k, int[] arr) {
        if (k <= 0 || arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }

        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            int start = i - k + 1;
            int end = i;

            while(!dq.isEmpty() && dq.peekFirst() < start) {
                dq.removeFirst();
            }

            while (!dq.isEmpty() && arr[dq.peekLast()] > arr[end]) {
                dq.removeLast();
            }

            dq.addLast(i);
            if (i >= k - 1) {
                res.add(arr[dq.peekFirst()]);
            }
        }

        return res;
    }
}
