import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kevin on 11/9/16.
 */
public class KnightsTour {
    public static void main(String[] args) {
        System.out.println("recursive: " + numPhoneNumbers(1, 3));
        System.out.println("bottom up dp: " + countNumbersDp(1, 3));
        System.out.println("bottom up dp low mem: " + countNumbersDpLowMem(1, 3));
    }

    static ArrayList<ArrayList<Integer>> traversalMap;

    static int numPhoneNumbers(int startdigit, int phonenumberlength) {
        initTraversalMap();
        return countNumbersRecursive(startdigit, phonenumberlength);
    }

    static int countNumbersDp(int currentStart, int phoneNumLen) {
        int[][] dp = new int[10][phoneNumLen + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][1] = 1;
        }
        for (int col = 2; col < dp[0].length; col++) {
            for (int row = 0; row < dp.length; row++) {
                for (int next : traversalMap.get(row)) {
                    dp[row][col] += dp[next][col - 1];
                }
            }
        }

        printMatrix(dp);
        return dp[currentStart][phoneNumLen];
    }

    static int countNumbersDpLowMem(int currentStart, int phoneNumLen) {
        int[] prev = new int[10];
        int[] curr = new int[10];
        for (int i = 0; i < 10; i++) {
            prev[i] = 1;
        }

        for (int col = 2; col <= phoneNumLen; col++) {
            for (int row = 0; row < 10; row++) {
                for (int next : traversalMap.get(row)) {
                    curr[row] += prev[next];
                }
            }
            prev = curr;
            curr = new int[10];
        }

        return prev[currentStart];
    }

    static int countNumbersRecursive(int currentStart, int phoneNumLen) {
        if (phoneNumLen == 1) {
            return 1;
        } else if (phoneNumLen == 0) {
            return 0;
        }

        int result = 0;
        for (int next : traversalMap.get(currentStart)) {
            result += countNumbersRecursive(next, phoneNumLen - 1);
        }

        return result;
    }

    static void printMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    static void initTraversalMap() {
        traversalMap = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            traversalMap.add(new ArrayList<Integer>());
        }

        traversalMap.get(0).add(4);
        traversalMap.get(0).add(6);
        traversalMap.get(1).add(6);
        traversalMap.get(1).add(8);
        traversalMap.get(2).add(9);
        traversalMap.get(2).add(7);
        traversalMap.get(3).add(8);
        traversalMap.get(3).add(4);
        traversalMap.get(4).add(3);
        traversalMap.get(4).add(9);
        traversalMap.get(4).add(0);
        traversalMap.get(6).add(1);
        traversalMap.get(6).add(7);
        traversalMap.get(6).add(0);
        traversalMap.get(7).add(2);
        traversalMap.get(7).add(6);
        traversalMap.get(8).add(1);
        traversalMap.get(8).add(3);
        traversalMap.get(9).add(2);
        traversalMap.get(9).add(4);
    }
}
