import java.util.Arrays;

/**
 * Created by kevint on 7/19/2016.
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr1 = { 4, 2, 1, 5 };
        new InsertionSort().insSort(arr1);

        System.out.println(Arrays.toString(arr1));
    }

    public void insSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        // Iterate through entire array and move the
        // unsorted element to its sorted position.
        for (int i = 0; i < arr.length; i++) {
            // Set the swap point. swap only if current element is bigger then the previous
            int k = i;
//            while (j > 0; )

        }
    }
}
