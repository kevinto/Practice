import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Kevin on 7/10/16.
 */
public class StackFromQueues {
    public static void main(String[] args) {
        SFromQ stack = new SFromQ();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
    }


}

class SFromQ {
    int size;
    Queue<Integer> currQ;
    Queue<Integer> tempQ;

    SFromQ() {
        currQ = new LinkedBlockingQueue<>();
        tempQ = new LinkedBlockingQueue<>();
    }

    public int pop() throws IndexOutOfBoundsException {
        if (currQ.isEmpty())
        {
            throw new IndexOutOfBoundsException();
        }

        return currQ.poll();
    }

    public void push(int val) {
        tempQ.add(val);

        while (!currQ.isEmpty()) {
            tempQ.add(currQ.poll());
        }

        Queue<Integer> swapTemp = currQ;
        currQ = tempQ;
        tempQ = swapTemp;
    }
}
