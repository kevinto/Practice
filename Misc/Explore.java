import java.util.*;

/**
 * Created by kevint on 4/27/2016.
 */
public class Explore {
    public static void main(String[] args) {
    }
    public int[] intersect(int[] nums1, int[] nums2) {
        // Determine return array size
        List<Integer> arrList = new ArrayList<>();

        HashMap<Integer,Integer> hm1 = new HashMap<>();
        for (int num : nums1) {
            if (hm1.containsKey(num)) {
                int currVal1 = hm1.get(num);
                hm1.put(num, currVal1++);
            }
            else {
                hm1.put(num, 1);
            }
        }

        HashMap<Integer,Integer> hm2 = new HashMap<>();
        for (int num : nums2) {
            if (hm2.containsKey(num)) {
                int currVal2 = hm2.get(num);
                hm2.put(num, currVal2++);
            }
            else {
                hm2.put(num, 1);
            }
        }

        for (int key : hm1.keySet()) {
            if (hm2.containsKey(key)) {
                int v1 = hm1.get(key);
                int v2 = hm2.get(key);
                int max = Math.max(v1, v2);

                for (int i = 0; i < max; i++) {
                    arrList.add(key);
                }
            }
        }

        int[] returnArr = new int[arrList.size()];
        for (int i = 0; i < arrList.size(); i++) {
            returnArr[i] = arrList.get(i);
        }
        return returnArr;
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
