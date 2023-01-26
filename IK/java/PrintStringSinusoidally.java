/**
 * Created by kevinto on 12/12/16.
 */
public class PrintStringSinusoidally {
    public static void main(String[] args) {
        String str = "Google Worked";
        printSnakeString(str);
    }

    static void printSnakeString(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        int len = str.length();

        System.out.print("  ");
        for (int i = 2; i < len; i = i + 4) {
            char curr = str.charAt(i) == ' ' ? '~' : str.charAt(i);
            System.out.print(curr + "   ");
        }
        System.out.println();

        System.out.print(" ");
        for (int i = 1; i < len; i = i + 2) {
            char curr = str.charAt(i) == ' ' ? '~' : str.charAt(i);
            System.out.print(curr + " ");
        }
        System.out.println();

        for (int i = 0; i < len; i = i + 4) {
            char curr = str.charAt(i) == ' ' ? '~' : str.charAt(i);
            System.out.print(curr + "   ");
        }
        System.out.println();
    }
}
