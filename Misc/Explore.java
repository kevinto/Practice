import java.util.Stack;

/**
 * Created by kevint on 4/27/2016.
 */
public class Explore {
    public static void main(String[] args) {
//        Stack<Integer> s = new Stack<>();
//        int val = s.pop();
//        System.out.println(val);
        int[] arr = {1, 2, 4, 13, 43};
        System.out.println(minPatches(arr, 100));
    }

    public static int minPatches(int[] nums, int n) {
        long miss = 1, added = 0;
        int i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            }
            else {
                miss += miss;
                added++;
            }
        }
        return (int)added;
    }

    public static String reverseString(String s) {
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length / 2; i++) {
            char temp = charArr[i];
            charArr[i] = charArr[charArr.length - i - 1];
            charArr[charArr.length - i - 1] = temp;
        }

        return new String(charArr);
    }
}
