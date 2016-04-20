package p202;

import java.util.HashMap;

/**
 * Created by kevint on 4/18/2016.
 */
public class HappyNumber {
    public static void main(String[] args) {
//        System.out.println(isHappy(18));

        System.out.println(isHappy2(18));
        System.out.println(isHappy2(19));
    }

    public static boolean isHappy(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int squaredNum = 0;
        int currNum = n;
        while (squaredNum != 1) {
            squaredNum = 0;
            String numStr = Integer.toString(currNum);

            for (int i = 0; i < numStr.length(); i++) {
                String currString = Character.toString(numStr.charAt(i));
                squaredNum += Math.pow(Integer.parseInt(currString), 2);
            }

            if (squaredNum == 1) {
                return true;
            } else if (map.get(squaredNum) != null) {
                System.out.println(squaredNum);
                return false;
            } else {
                System.out.println(squaredNum);
                map.put(squaredNum, 1);
            }

            currNum = squaredNum;
        }

        // check hash table to see if it exists
        // for each digit in the string convert to int and square and sum
        // convert to string and repeat
        return false;
    }

    // TODO: Need to understand the proof here: https://leetcode.com/discuss/71625/explanation-those-posted-algorithms-mathematically-valid
    public static boolean isHappy2(int n) {
        // This algorithm is based off the Floyd Cycle detection algorithm
        int slow, fast;
        slow = fast = n;

        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while (slow != fast);

        if (slow == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public static int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }
}
