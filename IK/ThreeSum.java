import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kevinto on 11/25/16.
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] test1 = {-1, 1, 0, -2, 1};
        String[] results = printTriplets(test1);
        return;
    }

    public static String[] printTriplets(int[] nums) {
        List<String> results = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return (String[]) results.toArray();
        }
        Arrays.sort(nums);

        for (int first = 0; first < nums.length - 2; first++) {
            if (first == 0 || nums[first] > nums[first - 1]) {
                int second = first + 1;
                int third = nums.length - 1;

                while (second < third) {
                    if (nums[first] + nums[second] + nums[third] == 0) {
                        String temp = Integer.toString(nums[first]) + "," + Integer.toString(nums[second]) + "," + Integer.toString(nums[third]);
                        results.add(temp);

                        second++;
                        third--;
                        while (second < third && nums[second] == nums[second - 1]) {
                            second++;
                        }
                        while (second < third && nums[third] == nums[third + 1]) {
                            third--;
                        }
                    } else if (nums[first] + nums[second] + nums[third] < 0) {
                        second++;
                    } else {
                        third--;
                    }
                }
            }
        }
        String[] finalResult = results.toArray(new String[0]);
        return finalResult;
    }
}
