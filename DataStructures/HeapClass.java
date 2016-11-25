import java.util.Arrays;

/**
 * Created by kevinto on 11/24/16.
 */
public class HeapClass {
    public static void main(String[] args) {
        int[] test1 = { 11, 18, 1, 3, 4 };
        Heap heap = new Heap(test1);
        System.out.println(Arrays.toString(heap.arr));
        return;
    }

    static class Heap {
        public static int capacity = 50;
        public static int emptyPos = 1;

        // Array will be populated starting at position 1.
        public static int[] arr = new int[capacity + 1];

        public Heap(int[] originalArray) {
            if (originalArray.length > capacity) {
                return;
            }

            copyToInternalArray(originalArray);

            for(int i = (originalArray.length / 2); i > 0; i--) {
                heapify(i);
            }
        }

        private static void heapify(int i) {
            int min;
            int left = 2 * i;
            int right = 2 * i + 1;

            if (left < emptyPos && arr[left] < arr[i]) {
                min = left;
            } else {
                min = i;
            }

            if (right < emptyPos && arr[right] < arr[min]) {
                min = right;
            }

            if (min != i) {
                swap(i, min);
                heapify(min);
            }
        }

        private static void swap(int leftChildIdx, int i) {
            int temp = arr[leftChildIdx];
            arr[leftChildIdx] = arr[i];
            arr[i] = temp;
        }

        private void copyToInternalArray(int[] originalArray) {
            for (int i = 0; i < originalArray.length; i++) {
                arr[emptyPos] = originalArray[i];
                emptyPos++;
            }
        }
    }
}
