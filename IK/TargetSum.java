/**
 * Created by kevinto on 2/5/17.
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return helper(nums, target, 0);
    }

    private int helper(int[] nums, int target, int pos) {
        if (pos >= nums.length) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        int sum = helper(nums, target + nums[pos], pos + 1);
        sum += helper(nums, target - nums[pos], pos + 1);
        return sum;
    }
}
