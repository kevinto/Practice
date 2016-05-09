package p347;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kevin on 5/6/16.
 * Problem Link: https://leetcode.com/problems/top-k-frequent-elements/
 *
 * 1. Use a hashmap with key equal to the number and value equal
 *    to the number of occurrences. Finding the num occurrences
 *    would be O(n). Then you have to sort the num occurrences
 *    to find the k most frequent elements.
 * 2. Create a temp array at least the size of the input array.
 *    Initialize to zero and increment the num occurrences.
 *    Sort the temp array using quick sort.
 *    Time complexity: O(n) + (n log n)
 * 3. Use bucket sort.
 *    Iterate through the given array and map the number of
 *    occurrences. Iterate through the map and
 *    Time complexity: O(n)
 *
 * Q1: Will I ever get an empty array? Based on the assumption, no.
 *
 */
public class TopK {
    public static void main(String[] args) {
        int[] arr1 = {1, 1, 1, 2, 2, 2, 2, 3};
        System.out.println(topKFrequent(arr1, 2).toString());

        int[] arr2 = {1};
        System.out.println(topKFrequent(arr2, 1).toString());
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        // Bucket is initialized to (nums.length + 1) because
        // there needs arrays are zero indexed. Buckets indexes
        // are supposed to represent the frequency numbers. If
        // we have an array with only when element, then the
        // frequency is 1, and bucket needs to have 2 open elements.
        List<Integer>[] bucket = new List[nums.length + 1];
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        // Create a map with keys as the number and values as the number of
        // occurrences
        for (int n : nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        // Add keys from the hashmap to an array using the number of occurrences
        // as the index
        for (int key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }

            // bucket index represents the number frequency
            // the bucket element value represents the key
            // we want to return.
            bucket[freq].add(key);
        }


        // Start from the right side of the bucket array and add the key
        // values to the result array
        List<Integer> res = new ArrayList<>();
        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }

        return res;
    }
}

