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
            fs.push(0, 2);
            fs.push(0, 3);

            fs.push(1, 4);
            fs.push(1, 5);
            fs.push(1, 6);

            fs.push(2, 7);
            fs.push(2, 8);
            fs.push(2, 9);

            System.out.println(fs.pop(0) == 3);
            System.out.println(fs.pop(0) == 2);
            System.out.println(fs.pop(0) == 1);

            System.out.println(fs.pop(1) == 6);
            System.out.println(fs.pop(1) == 5);
            System.out.println(fs.pop(1) == 4);

            System.out.println(fs.pop(2) == 9);
            System.out.println(fs.pop(2) == 8);
            System.out.println(fs.pop(2) == 7);
        }
        catch(Exception ex) {
        }

    }
}

class FixedMultiStack {
    int numStacks = 3;
    int stackCapacity;
    int[] values;
    int[] sizes;

    public FixedMultiStack(int stackSize) {
        stackCapacity = stackSize;
        values = new int[stackCapacity * numStacks];
        sizes = new int[numStacks];
    }

    public void push(int stackNum, int value) throws FullStackException {
        if (isFull(stackNum)) {
            throw new FullStackException();
        }

        int addIdx = getTop(stackNum) + 1;
        values[addIdx] = value;
        sizes[stackNum]++;
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }

        int retValue = values[getTop(stackNum)];
        sizes[stackNum]--;
        return retValue;
    }

    public boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity;
    }

    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    public int getTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        return offset + sizes[stackNum] - 1;
    }
}

class FullStackException extends Exception {
}
