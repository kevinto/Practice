import java.util.HashMap;

/**
 * Created by kevinto on 2/9/17.
 */
public class MaximumSizeSubarraySumEqualsk {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLen = 0;
        int[] sums = new int[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();

        // Gen the sum array
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 0; i < sums.length; i++) {
            int comp = sums[i] - k;
            if (map.containsKey(comp)) {
                maxLen = Math.max(maxLen, i - map.get(comp));
            }

            if (sums[i] == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // Bug where you could have duplicate sums. It doesnt matter.
            // you always want the older index because it is farther away!
            if (!map.containsKey(sums[i])) {
                map.put(sums[i], i);
            }
        }

        return maxLen;
    }
}
