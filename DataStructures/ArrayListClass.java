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

        for (int i = 0; i < 34; i++) {
            arrL.add(i);
        }
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
            createDoubledArr();
            arr[emptyIdx] = val;
        }
        else {
            arr[emptyIdx] = val;
        }

        emptyIdx++;
    }

    void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }
    }

    private void createDoubledArr() {
        int[] tempArr = new int[size * 2];
        copy(arr, tempArr);
        size *= 2;
        arr = tempArr;
    }

    private void copy(int[] src, int[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }
}
