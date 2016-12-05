import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

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
        Deque<Integer> dq = new LinkedList();

        // Add initial window
        for (int i = 0; i < windowSize; i++){
            while (!dq.isEmpty() && nums[i] > nums[dq.getLast()]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }

        for (int i = windowSize; i < nums.length; i++) {
            // Process the previous window
            maxes[i - windowSize] = nums[dq.getFirst()];

            // Clean back of queue
            while (!dq.isEmpty() && nums[i] > nums[dq.getLast()]) {
                dq.removeLast();
            }

            // Clean front of queue
            while (!dq.isEmpty() && dq.getFirst() <= i - windowSize) {
                dq.removeFirst();
            }

            // Add the current window
            dq.addLast(i);
        }

        // Add the last window.
        maxes[nums.length - windowSize] = dq.getFirst();
        return maxes;
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
