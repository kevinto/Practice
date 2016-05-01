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
        for ( int i = 0; i < 20; i++ ) {
            System.out.println(Math.random());
        }
        Pair<String, Integer> p1 = new OrderedPair<>("Even", 8);
        Pair<String, String> p2 = new OrderedPair<>("hello", "world");
        Pair<String, OrderedPair<String, String>> p3 = new OrderedPair<>("good", new OrderedPair<>("bye", "to you"));

        System.out.println("pair 1: " + p1.getKey() + "," + p1.getValue());
        System.out.println("pair 2: " + p2.getKey() + "," + p2.getValue());
        System.out.println("pair 3: " + p3.getKey() + "," + p3.getValue().getKey() + "," + p3.getValue().getValue());
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

