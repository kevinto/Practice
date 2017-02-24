/**
 * Created by kevinto on 12/22/16.
 */
public class FindDuplicate {
    public static void main(String[] args) {
        int[] nums = {2, 1, 3, 6, 1, 2};
        int dup = findDup(nums);
        System.out.println("dup = " + dup);

        int[] nums2 = {2, 1, 3, 6, 1, 2};
        int dup2 = findDupAlt(nums2);
        System.out.println("dup2 = " + dup2);
    }

    public static int findDup(int[] nums) {
        int temp = -1;
        int i = 0;
        while (i < nums.length) {
            if (temp == -1) {
                if (nums[i] == i + 1) {
                    i++;
                } else {
                    temp = nums[i];
                    nums[i] = -1;
                }
            } else {
                if (nums[temp - 1] == temp) {
                    // Found the duplicate
                    return temp;
                } else {
                    int temp2 = nums[temp - 1];
                    nums[temp - 1] = temp;
                    temp = temp2;
                }
            }

            while (nums[i] == i + 1) {
                i++;
            }
        }

        return -1;
    }

    public static int findDupAlt(int[] nums) {
        int curr;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i + 1) {
                continue;
            }

            curr = nums[i];
            nums[i] = -1;

            while (curr != -1) {
                if (curr == nums[curr - 1]) {
                    return curr;
                }

                int temp = nums[curr - 1];
                nums[curr - 1] = curr;
                curr = temp;
            }
        }

        return -1;
    }
}
