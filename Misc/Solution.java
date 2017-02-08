import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        System.out.println("test".substring(0,0));
        int[] denoms = {2,3,5,6};
        int money = 10;

        int recurMin = getMinRecursive(denoms, money);
        System.out.println("Recursive min: " + recurMin);

        int dpMin = getMinDp(denoms, money);
        System.out.println("Dp min: " + dpMin);

        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();

        System.out.println(getPath(denoms, money));
    }

    public static List<List<Integer>> getPath(int[] denoms, int money) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        getPath(path, res, money, denoms);
        return res;
    }

    public static void getPath(List<Integer> path, List<List<Integer>> res, int money, int[] denoms) {
        if (money == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        int temp = money;
        for (int i = 0; i < denoms.length; i++) {
            if ((temp - denoms[i] >= 0) && dp[temp - denoms[i]] + 1 == dp[temp]) {
                path.add(denoms[i]);
                getPath(path, res, i, denoms);
                path.remove(path.size() - 1);
            }
        }
    }

    private static int[] dp;
    public static int getMinDp(int[] denoms, int money) {
        dp = initDp(money);

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < denoms.length; j++) {
                if (i - denoms[j] >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - denoms[j]]);
                }
            }
        }

        return dp[money];
    }

    private static int[] initDp(int money) {
        int[] dp = new int[money + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = money;
        }
        return dp;
    }

    public static int getMinRecursive(int[] denoms, int money) {
        if (money == 0) {
            return 0;
        }

        int min = money;
        for (int i = 0; i < denoms.length; i++) {
            if (money - denoms[i] >= 0) {
                min = Math.min(min, 1 + getMinRecursive(denoms, money - denoms[i]));
            }
        }

        return min;
    }


}

