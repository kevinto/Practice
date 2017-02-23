/**
 * Created by kevinto on 12/19/16.
 */
public class PascalTriangle {
    public static void main(String args[] ) throws Exception {
        System.out.println("printPasUsingRecursion(6):");
        printPasUsingRecursion(6);
        System.out.println("printPasUsingIterationWithCurrPrev(6):");
        printPasUsingIterationWithCurrPrev(6);
        System.out.println("printPasCleanIteration(6):");
        printPasCleanIteration(6);
    }

    public static int[] printPasUsingRecursion(int n) {
        if (n <= 0) {
            return null;
        } else if (n == 1) {
            System.out.println("1");
            int[] res = {1};
            return res;
        }
        int[] res = printPasUsingRecursion(n - 1);
        int[] res2 = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == n - 1) {
                res2[i] = 1;
            } else {
                res2[i] = res[i] + res[i - 1];
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(res2[i] + " ");
        }

        System.out.println();
        return res2;
    }

    public static void printPasUsingIterationWithCurrPrev(int n) {
        if (n <= 0) {
            return;
        }

        int[] prev = new int[n];
        int[] curr = {1};
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                // Prints the first line
                System.out.print(curr[0]);
            } else {
                // Prints the current line based on the previous line
                curr = new int[n];
                for (int j = 0; j < i; j++) {
                    if (j == 0 || j == i - 1) {
                        curr[j] = 1;
                    } else {
                        curr[j] = prev[j - 1] + prev[j];
                    }

                    System.out.print(curr[j] + " ");
                }
            }

            prev = curr;
            System.out.println();
        }
    }

    public static void printPasCleanIteration(int n) {
        int[] prev = new int[n];
        for (int level = 1; level <= n; level++) {
            int[] curr = new int[n];

            for (int i = 0; i < level; i++) {
                if (i == 0 || i == level - 1) {
                    curr[i] = 1;
                    System.out.print(curr[i] + " ");
                } else {
                    curr[i] = prev[i - 1] + prev[i];
                    System.out.print(curr[i] + " ");
                }
            }

            System.out.println();
            prev = curr;
        }
    }
}
