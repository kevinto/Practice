public class Solution {
    public static void main(String[] args) {
        System.out.println(getMoneyAmountRecursive(3));
    }

    public static int getMoneyAmountRecursive(int n){
        return getMoney(1,n);
    }

    private static int getMoney(int start, int end){
        // If start is greater than or equal to the end, then
        // return 0 because we can only choose 1 number.
        if(start >= end){
            return 0;
        }

        // Set max to integer max, so we can reset it later
        int max=Integer.MAX_VALUE;

        // Recursive call - find the cost of the left and the cost of the right.
        // Get the max of either sides and add it to the current picked number.
        // Then pick the minimum of picking all those i's.
        for(int i = start; i <= end; i++){
            int left = getMoney(start,i - 1);
            int right = getMoney(i + 1, end);
            int subMax = Math.max(left, right) + i;
            max=Math.min(max, subMax);
        }
        return max;
    }

    // -- NOT OPTIMAL Recursion --
    // This is the not optimal recursive solution. hard to convert to DP.
    // Warning sign that there is too many base cases.
    public static int getMoneyAmount(int n) {
        minMoneyAmt = Integer.MAX_VALUE;

        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }

        return getMoneyAmount(nums, 0, nums.length - 1);
    }

    // Drawbacks of this recursive method is that it requires an
    // array to hold the numbers.
    private static int minMoneyAmt;
    public static int getMoneyAmount(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        } else if (end - start == 1) {
            return nums[start];
        } else if (end - start == 2) {
            int mid = ((end - start) / 2) + start;
            return nums[mid];
        }

        int currMax = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int subMax = nums[i]
                    + getMoneyAmount(nums, start, i - 1)
                    + getMoneyAmount(nums, i + 1, end);
            currMax = Math.min(currMax, subMax);
        }

        minMoneyAmt = Math.min(minMoneyAmt, currMax);
        return currMax;
    }
}

