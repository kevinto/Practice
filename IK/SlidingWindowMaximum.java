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
}
