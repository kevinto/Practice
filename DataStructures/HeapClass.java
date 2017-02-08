import java.util.Arrays;

/**
 * Created by kevinto on 11/24/16.
 */
public class HeapClass {
    public static void main(String[] args) {
        int[] test1 = { 11, 18, 1, 3, 4 };
        Heap heap = new Heap(test1);
        heap.insert(-1);
        System.out.println(Arrays.toString(heap.arr));

        int[] test2 = { 100, 99, 98, 97 };
        Heap heap2 = new Heap(test2);
        System.out.println(Arrays.toString(heap2.arr));
    }

    static class Heap {
        public static int capacity = 50;
        public static int emptyPos;

        // Array will be populated starting at position 1.
        public static int[] arr;

        public Heap(int[] originalArray) {
            if (originalArray.length > capacity) {
                return;
            }

            emptyPos = 1;
            arr = new int[capacity + 1];
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

        public static void insert(int val) {
            if (emptyPos > 50) {
                return;
            }

            arr[emptyPos] = val;
            emptyPos++;

            bottomUpHeapify(emptyPos - 1);
        }

        private static void bottomUpHeapify(int pos) {
            if (pos == 1) {
                return;
            }

            int parentIdx = pos / 2;
            if (arr[pos] < arr[parentIdx]) {
                swap(parentIdx, pos);
            }
            bottomUpHeapify(parentIdx);
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

    /*
    // This method is supposed to let me heapify a node within a
    // heap that i need to find and update
    public static void updateHeap(int[] heap, HashMap<Integer, Integer> map,
                                  int oldKey, int newKey) {
        if (!map.containsKey(oldKey)) {
            return;
        }

        int oldKeyIndex = map.get(oldKey);
        map.remove(oldKey);
        map.put(newKey, oldKeyIndex);
        heap[oldKeyIndex] = newKey;

        int parent = (oldKeyIndex - 1) / 2;
        if (parent >= 0 && heap[parent] > heap[oldKeyIndex]) {
            bottomUpHeapify(heap, oldKeyIndex);
        } else {
            topDownHeapify(heap, oldKeyIndex);
        }
    }

    private static void topDownHeapify(int[] heap, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        while (left < heap.length || right < heap.length) {
            if (left < heap.length && heap[left] <= heap[right]
                    && heap[left] < heap[index]) {

                swap(heap, left, index);
                index = left;
            } else if (right < heap.length && heap[right] < heap[left]
                       && heap[right] < heap[index]){

                swap(heap, right, index);
                index = right;
            } else {
                break;
            }

            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }

    private static void bottomUpHeapify(int[] heap, int index) {
        int parent = (index - 1) / 2;
        while (parent >= 0 && parent != index) {
            if (heap[parent] > heap[index]) {
                swap(heap, parent, index);
                index = parent;
                parent = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] nums, int i1, int i2) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
     */
}
