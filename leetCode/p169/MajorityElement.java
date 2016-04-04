package p169;
/*
Link: https://leetcode.com/problems/majority-element/

Problem:
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

- op1: use hashmap to increment. we can just check the number 
- op2: use array to increment
- op3: use arrayList to hold objects
- common: calc floor(n/2);

Corner cases:
1. Odd/even element arrays
2. single element arrays
*/

import java.util.HashMap;
public class MajorityElement {
    public static void main(String[] args) {
        int[] testNum1 = { 1, 1, 0 };
        System.out.println(solve(testNum1) == 1);
        
        int[] testNum2 = { 8, 8, 7, 7, 7 };
        System.out.println(solve(testNum2) == 7);
        
        int[] testNum3 = { 1 };
        System.out.println(solve(testNum3) == 1);
    }
    
    public static int solve(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        
        // Bug need to think about even and odds
        int majorNum = (int)Math.ceil(nums.length / 2.0);
        
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                int currValue = hash.get(nums[i]);
                
                // Didnt inc yet to need to check for majority number - 1
                if (currValue >= majorNum - 1) {
                    return nums[i];
                }
                
                hash.put(nums[i], ++currValue);
            }
            else {
                hash.put(nums[i], 1);
            }
        }
        return -1;
    }
}