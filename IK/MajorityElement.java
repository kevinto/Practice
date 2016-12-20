/**
 * Created by kevinto on 12/20/16.
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 3, 3, 4, 2, 2};
        findMajorityElement(nums);
    }

    public static void findMajorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("Invalid input.");
        }

        int count = 0;
        int currM = -1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                currM = nums[i];
            } else {
                if (nums[i] == currM) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        System.out.println("curr majority: " + currM);
    }
}
