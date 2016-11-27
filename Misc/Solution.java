import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String args[] ) throws Exception {
        char[] arr = { '1', '9', '9', '1' };
        incMid(arr);
        System.out.println(Arrays.toString(arr));

        char[] arr1 = { '1', '9', '9' };
        incMid(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    public static void incMid(char[] nums) {
        boolean carry;
        int mid = (nums.length) / 2;
        if (nums.length % 2 == 0) {
            mid--;
        }

        do {
            carry = nums[mid] == '9';
            if (carry) {
                nums[mid] = '0';
                mid--;
            } else {
                nums[mid] = (char)(nums[mid] + 1);
            }
        } while (mid >= 0 && carry);
    }
}

