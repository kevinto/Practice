import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin on 7/18/16.
 */
public class fourSum {
    public static void main(String[] args) {
        int[] nums1 = {5,3,2,6,7,9,2,1,1,1};
        int target1 = 10;
        System.out.println(findFourSum(nums1, target1));
        return;
    }

    public static List<List<Integer>> findFourSum(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;

        Arrays.sort(nums);

        int max = nums[nums.length - 1];
        int min = nums[0];
        if ((4 * max) < target || (4 * min > target)) return res;

        int firstNum;
        for (int i = 0; i < nums.length; i++) {
            firstNum = nums[i];

            if (i > 0 && nums[i - 1] == firstNum) {
                // avoid duplicate
                continue;
            } else if ((firstNum + (3 * max)) < target) {
                // z too small
                continue;
            } else if ((firstNum * 4) > target) {
                // z too big
                break;
            } else if (i + 3 < nums.length && nums[i + 3] == firstNum) {
                // z is boundary
                if (4 * firstNum == target) {
                    res.add(Arrays.asList(firstNum, firstNum, firstNum, firstNum));
                }
                break;
            }

            threeSumForFourSum(nums, target - firstNum, i + 1, nums.length - 1, res, firstNum);
        }

        return res;
    }

    public static void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
                                   int firstNum) {
        if (low + 1 >= high) {
            return;
        }

        int max = nums[high];
        int min = nums[low];
        if (min * 3 > target || max * 3 < target) return;

        int secondNum;
        for (int i = low; i < high - 1; i++) {
            secondNum = nums[i];
            if (i > low && nums[i - 1] == secondNum) {
                // Avoid duplicates
                continue;
            } else if (secondNum + 2 * max < target) {
                // Too small
                continue;
            } else if (secondNum * 3 > target) {
                // Too Big
                break;
            } else if (3 * secondNum == target) {
                // Boundary
                if (i + 2 < nums.length && nums[i + 2] == secondNum){
                    fourSumList.add(Arrays.asList(firstNum, secondNum, secondNum, secondNum));
                }
            }

            twoSumForFourSum();
        }
    }
}
