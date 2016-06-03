import java.util.Arrays;
import java.util.Comparator;
import java.util.*;


/**
 * Created by kevint on 4/27/2016.
 */
public class Explore {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i += 2) {
            System.out.println(i);
        }
//        ArrayList<Integer> test = getRow(2);
//        System.out.println((test));
    }

    public static String[] answer(String[] names) {
        // Awesome, i can use arrays.sort. need to write comparator now.
        // 1. do mergesort. compare values on merge step.
        // figuring out the values on every merge space is troublesome

        // 2. create hash map with key as string and value as value.
        // do merge sort, but use map as guide for value compare.


        // Your code goes here.
        Arrays.sort(names);
        return names;
    }

    class nameComparator implements Comparator<String>{
        @Override
        public int compare(String s1, String s2) {
            return -1;
        }
    }

    public static ArrayList<Integer> getRow(int a) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1); // Adds the header 1

        // Iterates through levels
        for (int k = 0; k < a; k++) {
            int val;
            int last = 0;

            // Iterates through each element within a level
            for (int i = 0; i < k + 1; i++) {
                val = res.get(i);

                // Condition states we are reading from within a valid
                // range.
                if (i - 1 >= 0) {

                    val += last;
                }
                last = res.get(i);
                res.set(i, val);
            }

            res.add(res.get(0)); // Adds the ender 1
        }

        return res;
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
