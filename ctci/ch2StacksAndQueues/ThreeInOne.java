package ch2StacksAndQueues;

/**
 * Created by Kevin on 4/28/16.
 *
 * Problem Statement: Describe how you could use a single
 *                    array to implement three stacks.
 *
 * 1. Do I get to encapsulate this in a class?
 *      - A var that tracks the insert position (will know
 *        the end based on size of each stack)
 *      - A var that tracks the last element of the stack
 *      - A var that tracks the size allocated for each stack.
 *      - Methods:
 *          - constructor(int perStackSize)
 *          - push(int value, int stackNumber)
 *          - pop(int stack)
 *          - size()
 *
 * 2. What data type is going to be used for the stack?
 *
 * 3. Does the user control which stack to push and pop from?
 * 4. Will there be a max stack size?
 */
public class ThreeInOne {
    public static void main(String args[]) {

    }
}

class ThreeStack {
    private int allocatedPerStackSize = 4;
    private int stack1InsertIdx;
    private int stack2InsertIdx;
    private int stack3InsertIdx;
    private int stack1EndIdx;
    private int stack2EndIdx;
    private int stack3EndIdx;
    private int stack1Size;
    private int stack2Size;
    private int stack3Size;
}
