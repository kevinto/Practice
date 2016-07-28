import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kevint on 7/27/2016.
 */
public class RobHouses {
    public static void main(String[] args) {
        int[] arr1 = {100, 100, 1, 2, 100};
        memo = new int[arr1.length];
        System.out.println(findMax(arr1));
        System.out.println("path: " + findPath(arr1, findMax(arr1)));

        int[] arr2 = {1, 2, 3};
        memo = new int[arr2.length];
        System.out.println(findMax(arr2));
        System.out.println("path: " + findPath(arr2, findMax(arr2)));

        int[] arr3 = {100, 2, 100};
        memo = new int[arr3.length];
        System.out.println(findMax(arr3));
        System.out.println("path: " + findPath(arr3, findMax(arr3)));

        int[] arr4 = {1, 100, 3};
        memo = new int[arr4.length];
        System.out.println(findMax(arr4));
        System.out.println("path: " + findPath(arr4, findMax(arr4)));
    }

    private static int[] memo;

    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];

        int max = -1;

        memo[0] = arr[0];
        memo[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++) {
            int chooseCurr = arr[i] + memo[i - 2];
            if (chooseCurr > memo[i - 1]) {
                memo[i] = chooseCurr;
            } else {
                memo[i] = memo[i - 1];
            }
            max = Math.max(memo[i], max);
        }

        return max;
    }

    public static ArrayList<Integer> findPath(int[] arr, int max) {
        ArrayList<Integer> result = new ArrayList<>();
        int currVal = max;

        int idx = memo.length - 1;
        while (currVal > 0) {
            idx = findIdx(currVal, idx);
            if (idx != -1) {
                result.add(idx);
                currVal -= arr[idx];
            } else {
                break;
            }
        }
        return result;
    }

    public static int findIdx(int val, int start) {
        for (int currIdx = start; currIdx >= 0; currIdx--) {
            if (memo[currIdx] == val) {
                if (currIdx == 0) {
                    return currIdx;
                } else if (currIdx - 1 >= 0 && memo[currIdx - 1] != val) {
                    return currIdx;
                }
            }
        }
        return -1;
    }
}
