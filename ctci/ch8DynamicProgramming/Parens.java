package ch8DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Kevin on 6/19/16.
 */
public class Parens {
    public static void main(String args[]) {
        int n = 3;
        System.out.println(getParens(3));
    }

    public static ArrayList<String> getParens(int n) {
        if (n == 0) {
            ArrayList<String> blank = new ArrayList<>();
            blank.add("");
            return blank;
        }

        ArrayList<String> partials = getParens(n - 1);
        HashSet<String> set = new HashSet<>();

        for (String partial : partials) {
            set.add("()" + partial);
            set.add(partial + "()");
            set.add("(" + partial + ")");
        }

        ArrayList<String> result = new ArrayList<>();
        Iterator<String> itr = set.iterator();
        while (itr.hasNext()) {
            result.add(itr.next());
        }

        return result;
    }
}
