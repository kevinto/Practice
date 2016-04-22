/**
 * Created by Kevin on 4/21/16.
 *
 * More info on difference between vector and arraylist
 * 1. http://geekexplains.blogspot.com/2008/05/difference-between-vector-and-arraylist.html
 * 2. http://www.erpgreat.com/java/difference-between-arraylist-and-vector.htm
 */
public class ArrayListClass {
    public static void main(String[] args) {
        ArrayL arrL = new ArrayL();

        arrL.add(1);
        arrL.print();
    }
}

class ArrayL {
    private int size;
    private int[] arr;
    private int emptyIdx;

    ArrayL() {
        size = 1;
        arr = new int[1];
        emptyIdx = 0;
    }

    void add(int val) {
        if (emptyIdx + 1 > size) {
            // We need to double
        }
        else {
            arr[emptyIdx] = val;
        }
    }

    void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }
    }
}
