package p347CURRENT;

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
}
