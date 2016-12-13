/**
 * Created by kevinto on 12/12/16.
 */
public class Neuronyms {
    public static void main(String[] args) {
        String str = "nailed";
        printShortened(str);
    }

    public static void printShortened(String str) {
        int shortener = str.length() - 2;
        if (shortener < 2) {
            System.out.println(str);
        }

        for (int i = shortener; i >= 2; i--) {
            printShortened(str, i);
            System.out.println();
        }
    }

    private static void printShortened(String str, int shortLen) {
        int strLen = str.length();
        int start = 1;
        int end = start + shortLen - 1;

        // The end of our replacement range determines the correctness
        // of our replacement
        while (end < strLen - 1) {
            System.out.print(str.substring(0, start) + shortLen + str.substring(end + 1) + " ");
            start++;
            end++;
        }
    }
}
