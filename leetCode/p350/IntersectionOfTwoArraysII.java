package p350;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Kevin on 5/20/16.
 * Link: https://leetcode.com/problems/intersection-of-two-arrays-ii/
 *
 */
public class IntersectionOfTwoArraysII {
    public static void main(String[] args) {

    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        if (nums1.length == 0 || nums2.length == 0) {
            return result;
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int resIdx = 0;
        int n2Idx = 0;
        for (int num1 : nums1) {
            if (n2Idx > nums2.length && num1 < nums2[n2Idx]) {
                continue;
            }

            while (n2Idx < nums2.length && num1 > nums2[n2Idx]) {
                n2Idx++;
            }

            if (n2Idx >= nums2.length) {
                break;
            }

            if (num1 == nums2[n2Idx]) {
                result[resIdx++] = num1;
                n2Idx++;
            }
        }

        int[] finalResult = new int[resIdx];
        for (int i = 0; i < resIdx; i++) {
            finalResult[i] = result[i];
        }
        return finalResult;
    }
}
