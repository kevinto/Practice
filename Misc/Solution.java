import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {2, 3, -4, -9, -1, -7, 1, -5, -6};
        System.out.println(Arrays.toString(negPos(nums)));
    }

    public static int[] negPos(int[] nums) {
        int[] res = new int[nums.length];
        int posIdx = getNextPos(nums, -1);
        int negIdx = getNextNeg(nums, -1);

        for (int i = 0; i < res.length; i++) {
            if (i % 2 == 0) {
                res[i] = posIdx >= 0 ? nums[posIdx] : 0;
                posIdx = getNextPos(nums, posIdx);
            } else {
                res[i] = negIdx >= 0 ? nums[negIdx] : 0;
                negIdx = getNextNeg(nums, negIdx);
            }
        }

        return res;
    }

    public static int getNextPos(int[] nums, int index) {
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public static int getNextNeg(int[] nums, int index) {
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                return i;
            }
        }
        return -1;
    }

    static class Node {
        Node next;
        Node arbit;
        int val;

        Node(int x) {
            this.val = x;
        }
    }
}

