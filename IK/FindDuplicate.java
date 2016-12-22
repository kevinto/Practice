/**
 * Created by kevinto on 12/22/16.
 */
public class FindDuplicate {
    public static void main(String[] args) {
        int[] nums = {2, 1, 3, 6, 1, 2};
        int dup = findDup(nums);
        System.out.println("dup = " + dup);
    }

    public static int findDup(int[] nums) {
        int temp = -1;
        int i = 0;
        while (i < nums.length) {
            if (temp == -1) {
                if (nums[i] == i + 1) {
                    i++;
                } else {
                    int temp2 = nums[i];
                    nums[i] = temp;
                    temp = temp2;
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
}
