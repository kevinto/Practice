import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevinto on 1/14/17.
 */
public class SumZero {
    public static void main(String[] args) {
        int[] nums = {
                0,
                1,
                2,
                3,
                4,
                -10
        };

        String[] res = sumZero(nums);

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

        sumZeroBrute(nums);
    }

    static void sumZeroBrute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];

                if (sum == 0) {
                    System.out.println("Found sum 0 at start: " + start + ", end: " + end);
                }
            }
        }
    }

    static String[] sumZero(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return strListToStrArr(res);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum)) {
                res.add(getSubArray(nums, map.get(sum) + 1, i));
            } else if (sum == 0 && i > 0) {
                if (nums[0] == 0) {
                    res.add(getSubArray(nums, 1, i));
                }
                res.add(getSubArray(nums, 0, i));
            } else if (nums[i] == 0) {
                res.add(getSubArray(nums, i, i));
            } else {
                map.put(sum, i);
            }
        }

        return strListToStrArr(res);
    }

    static String getSubArray(int[] nums, int start, int end) {
        if (start > end) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(nums[start]);
        for (int i = start + 1; i <= end; i++) {
            sb.append("," + Integer.toString(nums[i]));
        }

        return sb.toString();
    }

    static String[] strListToStrArr(List<String> res) {
        String[] resArr = new String[res.size()];

        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }

        return resArr;
    }

}
