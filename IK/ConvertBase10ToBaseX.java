/**
 * Created by kevinto on 2/25/17.
 */
public class ConvertBase10ToBaseX {
    public static void main(String[] args) {
        int[] map = {0, 1}; // Currently set to binary.
        int val = 9;

        // This way prints it out in reverse of what the number actually is.
        while (val > 0) {
            System.out.print((map[val % 2]) + " ");
            val /= 2;
        }
    }
}
