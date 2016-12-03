/**
 * Created by kevinto on 11/18/16.
 */
public class NutsAndBolts {
    public static void main(String args[] ) throws Exception {
        String[] nuts = { "N3", "N2", "N1", "N4" };
        String[] bolts = { "B4", "B2", "B3", "B1" };
        String[] matched = match(nuts, bolts);
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
}
