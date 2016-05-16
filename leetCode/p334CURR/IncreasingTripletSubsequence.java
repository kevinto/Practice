package p334CURR;

/**
 * Created by Kevin on 5/14/16.
 * Link: https://leetcode.com/problems/increasing-triplet-subsequence/
 *
 * Problem: Given an unsorted array return whether an increasing
 *          subsequence of length 3 exists or not in the array.
 *
 *          Return true if there exists i, j, k such that
 *          arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 *
 *          Examples:
 *          1. [1, 2, 3, 4, 5] = true
 *          2. [5, 2, 3, 4, 1] = true
 *          3. [1, 1, 1, 5] = false
 *          4. [5, 4, 3, 2, 1] = false
 *          5. [7,1,2,3,6,5] = true
 *
 *  1. Start from first element and check the next two elements. O(n) time, O(1) space.O(n) time
 */
public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4 ,5};
        System.out.println(increasingTriplet(arr1));

        int[] arr2 = {5, 2, 3, 4, 1};
        System.out.println(increasingTriplet(arr2));

        int[] arr3 = {1, 1, 1, 5};
        System.out.println(increasingTriplet(arr3));

        int[] arr4 = {5, 4, 3, 2, 1};
        System.out.println(increasingTriplet(arr4));

        int[] arr5 = {7, 1, 2, 3, 6, 5};
        System.out.println(increasingTriplet(arr5));

        int[] arr6 = {2, 1 ,5 ,0, 4, 6};
        System.out.println(increasingTriplet(arr6));
    }

    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        int low = Integer.MAX_VALUE;
        int high = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= low) {
                low = nums[i];
            }
            else if (nums[i] <= high) {
                high = nums[i];
            }
            else {
                return true;
            }
        }

        return false;
    }
}
