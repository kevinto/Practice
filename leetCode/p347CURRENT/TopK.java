package p347CURRENT;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin on 5/6/16.
 * Problem Link: https://leetcode.com/problems/top-k-frequent-elements/
 *
 * 1. Use a hashmap with key equal to the number and value equal
 *    to the number of occurences. Finding the num occurences
 *    would be O(n). Then you have to sort the num occurences
 *    to find the k most frequent elements.
 * 2. Create a temp array at least the size of the input array.
 *    Initialize to zero and increment the num occurances.
 *    Sort the temp array using quick sort.
 *    Time complexity: O(n) + (n log n)
 *
 * Q1: Will I ever get an empty array? Based on the assmption, no.
 *
 */
public class TopK {
    public static void main(String[] args) {
        int[] arr1 = {1, 1, 1, 2, 2, 3};
        System.out.println(topKFrequent(arr1, 2).toString());
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        int[] occurs = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            occurs[nums[i]]++;
        }

        Arrays.sort(occurs);

        List<Integer> returnList = new ArrayList<>();
        for(int i = nums.length - 1; i > nums.length - k - 1; i--) {
            //TODO bug here we need to return the numbers
            returnList.add(occurs[i]);
        }

        return returnList;
    }
}
