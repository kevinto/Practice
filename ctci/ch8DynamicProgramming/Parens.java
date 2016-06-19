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
        System.out.println("getparens: " + getParens(n));
        System.out.println("GenParensNoDup: " + GenParensNoDup(n));

        n = 2;
        System.out.println("getparens" + getParens(n));
        System.out.println("GenParensNoDup: " + GenParensNoDup(n));

        n = 1;
        System.out.println("getparens" + getParens(n));
        System.out.println("GenParensNoDup: " + GenParensNoDup(n));
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

    public static ArrayList<String> GenParensNoDup(int count) {
        char[] str = new char[count * 2];
        ArrayList<String> list = new ArrayList<>();
        addParenNoDup(list, count, count, str, 0);
        return list;
    }

    public static void addParenNoDup(ArrayList<String> list, int leftRem, int rightRem,
                              char[] str, int count) {
        if (leftRem < 0 || rightRem < leftRem) return;

        if (leftRem == 0 && rightRem == 0) {
            String s = String.copyValueOf(str);
            list.add(s);
            return;
        } else {
            if (leftRem > 0) {
                str[count] = '(';
                addParenNoDup(list, leftRem - 1, rightRem, str, count + 1);
            }

            if (rightRem > 0) {
                str[count] = ')';
                addParenNoDup(list, leftRem, rightRem - 1, str, count + 1);
            }
        }
    }
}
