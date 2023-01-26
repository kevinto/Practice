import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by kevinto on 12/3/16.
 * input: long[] nums, int windowSize
 * output: long[] maximums
 *
 * 1. BF: For every new window, find the max of the window and put into result.
 *    t = o(n * w), s = o(1)
 * 2. Max heap. add window to max heap. take the max. How do we remove the element that is out of scope?
 * 3. Use 1 queue. Load window and save the max. when moving to next element. check if removed element
 *    is at the end of the queue, if yes, remove it.
 * 4. Use 1 stack. Load the window. if new element is greater than top of stack then push it. How do you
 *    remove depreciated elements from the middle of the stack?
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        long[] nums1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int windowSize = 3;
        System.out.println(Arrays.toString(windowMaxes(nums1, windowSize)));
    }

    public static long[] windowMaxes(long[] nums, int windowSize) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        long[] maxes = new long[nums.length - windowSize + 1];
        Deque<Integer> deque = new LinkedList();

        // Add initial window
        for (int i = 0; i < windowSize; i++){
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }

        for (int i = windowSize; i < nums.length; i++) {
            // Process the previous window
            maxes[i - windowSize] = nums[deque.getFirst()];

            // Clean back of queue
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }

            // Clean front of queue
            while (!deque.isEmpty() && deque.getFirst() <= i - windowSize) {
                deque.removeFirst();
            }

            // Add the current window
            deque.addLast(i);
        }

        // Add the last window.
        maxes[nums.length - windowSize] = deque.getFirst();
        return maxes;
    }

    // This is a solution for a different problem. This solution finds all the windows,
    // even if the window slides out of bounds.
    public static int[] windowMaxesWithDeque(int[] nums, int winSize) {
        // TODO: Input checking

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length && i < winSize; i++) {
            while (!queue.isEmpty() && nums[queue.getLast()] < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }

        int[] res = new int[nums.length];
        for (int i = winSize; i < nums.length; i++) {
            res[i - winSize] = nums[queue.getFirst()];

            // Clean up maxes that are out of bounds
            while (!queue.isEmpty() && queue.getFirst() < i - winSize + 1) {
                queue.removeFirst();
            }

            // Remove old vals that are less then current
            while (!queue.isEmpty() && nums[queue.getLast()] < nums[i]) {
                queue.removeLast();
            }

            queue.addLast(i);
        }

        for (int i = nums.length - winSize; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.getFirst() < i) {
                System.out.println(queue.getFirst());
                queue.removeFirst();
            }

            res[i] = nums[queue.getFirst()];
        }

        return res;
    }

    // This way makes the forloop a little cleaner by focusing on only 1 condition. This
    // forloop tracks only the right border and inits at the new window next element.
    // We first add the currmax (for previous window), then clean the window using the right
    // border, then add the element at the right border. However this way requires that you
    // add the remainder of the window at the end after the original forloop exits.
    // This is a solution for a different problem. This solution finds all the windows,
    // even if the window slides out of bounds.
    public static int[] windowMaxesWithHeapClean(int[] nums, int winSize) {
        int[] res = new int[nums.length];
        PriorityQueue<HeapObj> heap = new PriorityQueue<>((a, b) -> {
            return b.val - a.val;
        });

        // Load first window
        for (int i = 0; i < nums.length && i < winSize; i++) {
            heap.offer(new HeapObj(i, nums[i]));
        }

        for (int i = winSize; i < nums.length; i++) {
            res[i - winSize] = heap.peek().val;

            while (!heap.isEmpty() && heap.peek().index < i - winSize - 1) {
                heap.poll();
            }

            heap.offer(new HeapObj(i, nums[i]));
        }

        int right = nums.length - winSize;
        while (right < nums.length) {
            res[right] = heap.peek().val;

            right++;
            while (!heap.isEmpty() && heap.peek().index < right) {
                heap.poll();
            }
        }

        return res;
    }

    // This way loads the window first. The next for loop inits at the first window,
    // checks if top contains an out of range element (pops if it does), adds a new
    // object only if we are not in the initial window, and adds the max of the
    // window into the result array.
    // Our structure is basically start at the first window, check if valid, add it,
    // then go to the next window.
    // This is a solution for a different problem. This solution finds all the windows,
    // even if the window slides out of bounds.
    public static int[] windowMaxesWithHeap(int[] nums, int winSize) {
        // TODO: Input checking

        int[] res = new int[nums.length];
        PriorityQueue<HeapObj> heap = new PriorityQueue<>((a, b) -> {
            return b.val - a.val;
        });

        // Load first window
        for (int i = 0; i < nums.length && i < winSize; i++) {
            heap.offer(new HeapObj(i, nums[i]));
        }

        for (int left = 0, right = winSize - 1; left < nums.length; left++, right++) {
            while(!heap.isEmpty() && heap.peek().index < left) {
                heap.poll();
            }

            // Need to not add the initial window again
            if (left != 0 && right < nums.length) {
                heap.offer(new HeapObj(right, nums[right]));
            }

            res[left] = heap.peek().val;
        }

        return res;
    }

    private static class HeapObj {
        int index;
        int val;

        HeapObj(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    // Do this later as a coding exercise -> runtime = o(nlogn). this is because
    // we will never pop from the heap if the array is in sorted order.
//    typedef pair<int, int> Pair;
//    void maxSlidingWindow(int originalInts[], int n, int w, int resultArray[]) {
//        priority_queue<Pair> Q;
//
//        // Pushes the initial window
//        for (int i = 0; i < w; i++)
//            Q.push(Pair(originalInts[i], i));
//
//        // processes the current window first, cleans it up for the next window,
//        // adds the element for the next window ==> then next
//        // Goes through the rest of the windows
//        for (int i = w; i < n; i++) {
//            // Put curr max into the results
//            Pair currMax = Q.top();
//            resultArray[i-w] = currMax.first;
//
//            // Pop any maxes that are out of range. why do it after inputing the results?
//            // because we start by adding the first window, then we move on to the next window.
//
//            // Keep removing top of the heap if it contains
//            // any node less than or equal to the end of current window
//            while (currMax.second <= i-w) {
//                Q.pop();
//                currMax = Q.top();
//            }
//
//            // adds the element for the next window
//            Q.push(Pair(originalInts[i], i));
//        }
//        resultArray[n-w] = Q.top().first;
//    }
}
