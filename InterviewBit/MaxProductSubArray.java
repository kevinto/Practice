/**
 * Created by Kevin on 7/9/16.
 */
public class MaxProductSubArray {
    public static void main(String[] args) {
        int[] testarr1 = {2, 3, -2, 4};
        System.out.println(maxProduct(testarr1));
    }

    private static int maxProduct(int[] nums) {
        // Why do we need min and max arrays?
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        max[0] = min[0] = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
            } else {
                max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
            }
            result = Math.max(result, max[i]);
        }

        return result;
    }
}
