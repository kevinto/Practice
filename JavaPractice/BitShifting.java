/**
 * Created by Kevin on 4/11/16.
 */

import java.util.ArrayList;
import java.util.List;

public class BitShifting {
    public static void main(String[] args) {
//        int bitMask = 0x000F;
//        int val = 0x2222; //what does this mean? This is in hex
//        System.out.println(val & bitMask);

//        Person p = new Person("Joe", "Author", 42, 173, 82, "Brown", "MALE");
//        p.setName("kevin");
//        System.out.println(p.getName());

//        Logger logger = Logger.getLogger(p.getName());
//        for ( int i = 0; i < 20; i++ ) {
//            System.out.println(Math.random());
//        }
//        Pair<String, Integer> p1 = new OrderedPair<>("Even", 8);
//        Pair<String, String> p2 = new OrderedPair<>("hello", "world");
//        Pair<String, OrderedPair<String, String>> p3 = new OrderedPair<>("good", new OrderedPair<>("bye", "to you"));
//
//        System.out.println("pair 1: " + p1.getKey() + "," + p1.getValue());
//        System.out.println("pair 2: " + p2.getKey() + "," + p2.getValue());
//        System.out.println("pair 3: " + p3.getKey() + "," + p3.getValue().getKey() + "," + p3.getValue().getValue());

//        int[] arr = {8, 9, 10, 11, 12};
//        System.out.println("7: " + findNum(7, arr, 0, arr.length - 1));
//        System.out.println("8: " + findNum(8, arr, 0, arr.length - 1));
//        System.out.println("9: " + findNum(9, arr, 0, arr.length - 1));
//        System.out.println("10: " + findNum(10, arr, 0, arr.length - 1));
//        System.out.println("11: " + findNum(11, arr, 0, arr.length - 1));
//        System.out.println("12: " + findNum(12, arr, 0, arr.length - 1));
//        System.out.println("13: " + findNum(13, arr, 0, arr.length - 1));

        int[] arr1 = {9, 12, 17, 2, 4, 5};
        System.out.println("1: " + shiftFind(1, arr1));
        System.out.println("9: " + shiftFind(9, arr1));
        System.out.println("12: " + shiftFind(12, arr1));
        System.out.println("17: " + shiftFind(17, arr1));
        System.out.println("2: " + shiftFind(2, arr1));
        System.out.println("4: " + shiftFind(4, arr1));
        System.out.println("5: " + shiftFind(5, arr1));
        System.out.println("20: " + shiftFind(20, arr1));
    }

    public static int shiftFind(int n, int[] arr) {
        int firstStart = 0;
        int firstEnd = 0;
        int secondStart = 0;
        int secondEnd = arr.length - 1;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                firstEnd = i;
                secondStart = i + 1;
            }
        }

        int firstResult = findNum(n, arr, firstStart, firstEnd);
        int secondResult = findNum(n, arr, secondStart, secondEnd);

        return firstResult == -1 ? secondResult : firstResult;
    }

    public static int findNum(int n, int[] arr, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2 ;
            if (arr[mid] == n) {
                return mid;
            } else if (n < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}

interface Pair<K, V> {
    public K getKey();
    public V getValue();
}

class OrderedPair<K, V> implements Pair<K, V> {
    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
}

