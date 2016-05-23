import java.util.Stack;

/**
 * Created by kevint on 4/27/2016.
 */
public class Explore {
    public static void main(String[] args) {
//        Stack<Integer> s = new Stack<>();
//        int val = s.pop();
//        System.out.println(val);

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
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
