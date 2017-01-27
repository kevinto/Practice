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

    // ---------------------- My Implementation ------------------------
    public static int getMaj(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int winner = getMajCandidate(nums);
        int counter = getCount(nums, winner);

        int len = nums.length;
        if ((len % 2 == 0 && counter > len / 2)
                || (len % 2 == 1 && counter > (len / 2) + 1)) {

            System.out.println("Found the majority.");
            return winner;
        } else {
            System.out.println("There was no majority.");
            return -1;
        }
    }

    private static int getCount(int[] nums, int winner) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == winner) {
                counter++;
            }
        }
        return counter;
    }

    private static int getMajCandidate(int[] nums) {
        int count = 1;
        int winner = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count >= 1) {
                if (nums[i] == winner) {
                    count++;
                } else {
                    count--;
                }
            } else {
                count = 1;
                winner = nums[i];
            }
        }

        return winner;
    }
}
