/**
 * Created by kevinto on 2/18/17.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        int area = 0;

        while (left < right) {
            if (nums[left] < nums[right]) {
                area = Math.max(nums[left] * (right - left), area);
                left++;
            } else {
                area = Math.max(nums[right] * (right - left), area);
                right--;
            }
        }

        return area;
    }
}
