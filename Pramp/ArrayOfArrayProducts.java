import java.util.Arrays;

/**
 * Created by Kevin on 5/22/16.
 */
public class ArrayOfArrayProducts {
    public static void main(String[] args) {
        int[] arr1 = {2, 7, 3, 4};
        int[] result = solve(arr1);
        System.out.println(Arrays.toString(result));
    }

    public static int[] solve(int[] arr) {
        // Initialize the result array to all 1s
        int[] result = new int[arr.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }

        //  multiplies the lefthand-side trailing product
        int product = 1;
        for (int i = 0; i < arr.length; i++) {
            result[i] *= product;
            product *= arr[i];
        }


        // multiplies the righthand-size trailing product
        product = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            result[i] *= product;
            product *= arr[i];
        }

        return result;
    }
}
