package p349Curr1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Kevin on 5/20/16.
 * Link: https://leetcode.com/problems/intersection-of-two-arrays/
 *
 * TODO: Bug when duplicates
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        int[] testArr1 = {1, 2, 2, 1};
        int[] testArr2 = {2, 2, 1, 2};
        int[] retArr = intersection(testArr1, testArr2);
        System.out.print(Arrays.toString(retArr));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);

        HashSet<Integer> resultSet = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (binarySearch(nums2[i], nums1)) {
                resultSet.add(nums2[i]);
            }
        }

        int i = 0;
        int[] retArray = new int[resultSet.size()];
        for (int element : resultSet) {
            retArray[i++] = element;
        }
        return retArray;
    }

    public static boolean binarySearch(int searchTerm, int[] searchArr) {
        int start = 0;
        int end = searchArr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (searchArr[mid] == searchTerm) {
                return true;
            }
            else if (searchArr[mid] < searchTerm) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return false;
    }
}
