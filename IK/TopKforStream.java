import java.util.PriorityQueue;

/**
 * Created by kevinto on 11/19/16.
 */
public class TopKforStream {
    static int[] topK(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return nums;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        loadInitialQueue(pq, nums, k);

        for (int i = k; i < nums.length; i++) {
            if (pq.peek() < nums[i]) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        return pqToArray(pq);
    }

    static void loadInitialQueue(PriorityQueue<Integer> pq, int[] nums, int k) {
        for (int i = 0; i < k && i < nums.length; i++) {
            pq.offer(nums[i]);
        }
    }

    static int[] pqToArray(PriorityQueue<Integer> pq) {
        int[] result = new int[pq.size()];
        int pos = 0;

        while (!pq.isEmpty() && pos < result.length) {
            result[pos] = pq.poll();
            pos++;
        }

        return result;
    }
}
