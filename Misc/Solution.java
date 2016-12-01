import java.util.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String args[] ) throws Exception {

    }

    public static List<Integer> validMove(int place) {
        /*
        1 2 3
        4 5 6
        7 8 9
          0
        */

        List<Integer> results = new ArrayList<>();
        if (place == 1) {
            results.add(8);
            results.add(6);
        }

        return results;
    }
}

