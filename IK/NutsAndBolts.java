import java.util.Arrays;

/**
 * Created by kevinto on 11/18/16.
 */
public class NutsAndBolts {
    public static void main(String args[] ) throws Exception {
        // TODO: There is a bug here for the case where < < = < > >. You swap the left with the less than, but the pivot index is miss-set.
        String[] nuts = { "N1", "N3", "N2", "B5", "N4" };
        String[] bolts = { "B4", "B1", "B2", "B5", "B3" };
        String[] matched = match(nuts, bolts);
        System.out.println("nuts: " + Arrays.toString(nuts));
        System.out.println("bolts: " + Arrays.toString(bolts));
        return;
    }

    public static String[] match(String[] nuts, String[] bolts) {
        String[] results = new String[nuts.length];
        if (nuts == null || bolts == null || nuts.length != bolts.length) {
            return results;
        }

        sortBoltsNuts(nuts, bolts, 0, nuts.length - 1);
        combine(nuts, bolts, results);

        return results;
    }

    private static void sortBoltsNuts(String[] nuts, String[] bolts, int start, int end) {
        if (start >= end) {
            return;
        }

        // Partition nuts based on the last value of bolts
        int nutPivotIndex = partition(nuts, bolts[end], start, end);

        // Partition bolts based on the pivot index of nuts
        partition(bolts, nuts[nutPivotIndex], start, end);

        // Once we get here the values at the pivot's position are in the correct position.
        // Run the same thing against both sides of the pivot.
        sortBoltsNuts(nuts, bolts, start, nutPivotIndex - 1);
        sortBoltsNuts(nuts, bolts, nutPivotIndex + 1, end);
    }

    private static int partition(String[] arr, String pivot, int start, int end) {
        int left = start;
        int right = end;
        int pivotIndex = 0;

        while (left <= right) {
            while (bnComparer(arr[left], pivot) < 0) {
                left++;
            }
            while (bnComparer(arr[right], pivot) > 0) {
                right--;
            }

            if (bnComparer(arr[left], pivot) == 0) {
                pivotIndex = left;
            }

            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        return pivotIndex;
    }

    private static int bnComparer(String value1, String value2) {
        String numStr1 = value1.substring(1);
        String numStr2 = value2.substring(1);
        int num1 = Integer.parseInt(numStr1);
        int num2 = Integer.parseInt(numStr2);

        if (num1 < num2) {
            return -1;
        } else if (num1 > num2) {
            return 1;
        } else {
            return 0;
        }
    }

    private static void swap(String[] arr, int i1, int i2) {
        String temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    private static void combine(String[] arr1, String[] arr2, String[] result) {
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i] + arr2[i];
        }
    }

    // Similar to standard partition method. Here we pass the pivot element
    // too instead of choosing it inside the method.
    // Alt impl for quicksort partition. Here i is where all the lower numbers are.
    private static int partition(char[] arr, int low, int high, char pivot)
    {
        int i = low;
        char temp1, temp2;
        for (int j = low; j < high; j++)
        {
            if (arr[j] < pivot){
                temp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp1;
                i++;
            } else if(arr[j] == pivot){
                // put pivot into the high spot, unreachable.
                // then go back a step, so we can process the new number.
                temp1 = arr[j];
                arr[j] = arr[high];
                arr[high] = temp1;
                j--;
            }
        }

        // we can put the element into the spot at i because we always
        // inc i after putting a lower number there. even when
        // all the numbers are lower, i will be inc to the high pos.
        temp2 = arr[i];
        arr[i] = arr[high];
        arr[high] = temp2;

        // Return the partition index of an array based on the pivot
        // element of other array.
        return i;
    }
}
