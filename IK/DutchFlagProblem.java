/**
 * Created by Kevin on 11/20/16.
 */
public class DutchFlagProblem {
    public static void main(String[] args) {
        String test1 = "GBGGRBRG";
        System.out.println((solve(test1)));

        String test2 = "BBBGRRRR";
        System.out.println((solve(test2)));
    }

    public static String solve(String str) {
        char[] arr = str.toCharArray();
        int low = 0;
        int mid = 0;
        int high =  arr.length - 1;

        while (mid <= high) {
            if (arr[mid] == 'R') {
                swap(arr, mid, low);
                mid++; // does not need to be re-evaluated because we started evaluating from the beginning
                low++;
            } else if (arr[mid] == 'G') {
                mid++;
            } else {
                swap(arr, mid, high);
//                mid++; // needs to be re-evaluated after swap
                high--;
            }
        }
        return new String(arr);
    }

    public static void swap(char[] arr, int i1, int i2) {
        char temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    // My own recursive way of doing it.
    public static void dutch(int[] nums, int startIdx, int numToSort) {
        if (startIdx >= nums.length) {
            return;
        } else if (numToSort > 2) {
            return;
        }

        int sortedIdx = startIdx;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == numToSort) {
                swap(nums, sortedIdx, j);
                sortedIdx++;
            }
        }

        dutch(nums, sortedIdx, numToSort + 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
