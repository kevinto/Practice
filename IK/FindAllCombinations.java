import java.util.Arrays;

/**
 * Created by kevinto on 1/10/17.
 */
public class FindAllCombinations {
    public static void main(String[] args) {
        printCombos(4, 2);
    }

    public static void printCombos(int n, int k) {
        int[] path = new int[k];
        printCombos(n, k, path, 1, 0);
    }

    public static void printCombos(int n, int k, int[] path, int read, int write) {
        if (write >= path.length) {
            System.out.println(Arrays.toString(path));
            return;
        } else if (read > n) {
            return;
        }

        for (int i = read; i <= n; i++) {
            path[write] = i;
            printCombos(n, k, path, i + 1, write + 1);
        }
    }
}
