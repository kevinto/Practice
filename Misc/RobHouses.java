/**
 * Created by kevint on 7/27/2016.
 */
public class RobHouses {
    public static void main(String[] args) {
        int[] arr1 = {100, 100, 1, 2, 100};
        System.out.println(findMax(arr1));

        int[] arr2 = {1, 2, 3};
        System.out.println(findMax(arr2));

        int[] arr3 = {100, 2, 100};
        System.out.println(findMax(arr3));

        int[] arr4 = {1, 100, 3};
        System.out.println(findMax(arr4));
    }

    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];

        int max = -1;
        int[] memo = new int[arr.length];

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
}
