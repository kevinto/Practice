package ch3StacksAndQueues;

import java.util.EmptyStackException;

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
 * 5. Does out solution have to take care of a stack running out of space?
 * 6. Can we assume that stack 1 is identified as 0, stack 2 as 1, stack 3 as 2?
 */
public class ThreeInOne {
    public static void main(String args[]) {
        try {
            FixedMultiStack fs = new FixedMultiStack(3);
            fs.push(0, 1);
        }
        catch(Exception ex) {
        }

    }
}

class FixedMultiStack {
    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public FixedMultiStack(int stackSize) {
        stackCapacity = stackSize;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    public void push(int stackNum, int value) throws FullStackException {
        if (isFull(stackNum)) {
            throw new FullStackException();
        }

        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }

        sizes[stackNum]--;
        return values[indexOfTop(stackNum)];
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        return values[indexOfTop(stackNum)];
    }

    private boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity;
    }

    private int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset + size - 1;
    }

    private boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }
}

class FullStackException extends Exception {
}
