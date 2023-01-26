import java.util.Arrays;

/**
 * Created by kevinto on 12/10/16.
 */
public class ArrayProduct {
    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(getArrProduct(test1)));
    }

    public static int[] getArrProduct(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }

        // Left to right
        int prev = arr[0];
        for (int i = 1; i < result.length; i++) {
            result[i] *= prev;
            prev *= arr[i];
        }

        // Right to left
        prev = arr[arr.length - 1];
        for (int i = result.length - 2; i >= 0; i--) {
            result[i] *= prev;
            prev *= arr[i];
        }

        return result;
    }
}
