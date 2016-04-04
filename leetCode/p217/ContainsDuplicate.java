/*
Link: https://leetcode.com/problems/contains-duplicate/

Problem: Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
*/

// option 1: create a huge array for all possible ints and increment element as position is found.
        
// option 2: put the int as a key in a hash map and increment the value. get hash value before inserting into hash to figure out if its a duplicate.

package p217;
import java.util.HashMap;
public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] testNumbers1 = {100, 200, 400};
        int[] testNumbers2 = {100, 200, 100};
        
        System.out.println("testNumbers1 contains dups: " + solve(testNumbers1));
        System.out.println("testNumbers2 contains dups: " + solve(testNumbers2));
    }
    
    public static boolean solve(int[] nums) {
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i]) && (hash.get(nums[i]) > 0)) {
                return true;
            }
            
            hash.put(nums[i], 1);
        }
        
        return false;
    }
}