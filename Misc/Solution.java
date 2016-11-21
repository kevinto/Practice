import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String args[] ) throws Exception {
        int[] nums1 = { 1, 2 };
        int[] nums2 = { 3, 4, 0, 0 };
        int[] res = mergeFirstIntoAnother(nums1, nums2);
        return;
    }

    static int[] mergeFirstIntoAnother(int[] intArrShort, int[] intArrLong) {
        shiftToLastHalf(intArrLong);

        int len = intArrShort.length;
        int first = 0;
        int second = len;
        int finalIdx = 0;
        while (first < len && second < intArrLong.length) {
            if (intArrShort[first] < intArrLong[second]) {
                intArrLong[finalIdx++] = intArrShort[first++];
            } else {
                intArrLong[finalIdx++] = intArrLong[second++];
            }
        }

        while (first < len) {
            intArrLong[finalIdx++] = intArrShort[first++];
        }

        while (second < intArrLong.length) {
            intArrLong[finalIdx++] = intArrLong[second++];
        }

        return intArrLong;
    }

    static void shiftToLastHalf(int[] arr) {
        int start = 0;
        int copyIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                copyIdx = i;
                break;
            }
        }

        while (copyIdx < arr.length) {
            arr[copyIdx++] = arr[start++];
        }
    }
}

