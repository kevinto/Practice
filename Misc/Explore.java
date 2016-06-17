import java.util.Arrays;
import java.util.Comparator;
import java.util.*;


/**
 * Created by kevint on 4/27/2016.
 */
public class Explore {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matix = new ArrayList<>();
        ArrayList<Integer> zeroRow =  new ArrayList<>();
        zeroRow.add(1);
        zeroRow.add(0);
        zeroRow.add(1);
        ArrayList<Integer> firstRow =  new ArrayList<>();
        firstRow.add(1);
        firstRow.add(1);
        firstRow.add(1);
        ArrayList<Integer> secondRow =  new ArrayList<>();
        secondRow.add(1);
        secondRow.add(0);
        secondRow.add(1);
//        ArrayList<Integer> thirdRow =  new ArrayList<>();
//        thirdRow.add(13);
//        thirdRow.add(14);
//        thirdRow.add(15);
//        thirdRow.add(16);
//
        matix.add(zeroRow);
        matix.add(firstRow);
        matix.add(secondRow);
//        matix.add(thirdRow);
//        rotate(matix);
//        System.out.println(matix);

        setZeroes(matix);
        System.out.println(matix);

        String[] test = new String[4];
        test[0] = "hello";
        test[1] = "good";
        test[2] = "bye";
        test[3] = "ybe";

        System.out.println(test[0] + getValue(test[0]));
        System.out.println(test[1] + getValue(test[1]));
        System.out.println(test[2] + getValue(test[2]));
        System.out.println(test[3] + getValue(test[3]));

        answer(test);
        System.out.println(Arrays.toString(test));

        System.out.println("Printing iterator");
        List<Integer> newIntArr = new ArrayList<>();
        newIntArr.add(1);
        newIntArr.add(2);
        newIntArr.add(3);
        Iterator<Integer> itr = newIntArr.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        Map<Integer, Integer> map1 = new HashMap<>();
    }

    public static void setZeroes(ArrayList<ArrayList<Integer>> a) {
        if (a.size() == 0) {
            return;
        }

        boolean firstRowZ = false;
        boolean firstColZ = false;

        // Check first row
        ArrayList<Integer> firstRow = a.get(0);
        for (int num : firstRow) {
            if (num == 0) {
                firstRowZ = true;
                break;
            }
        }

        // Check first col
        for (int i = 0; i < a.size(); i++) {
            ArrayList<Integer> currRow = a.get(i);
            int currVal = currRow.get(0);
            if (currVal == 0) {
                firstColZ = true;
                break;
            }
        }

        for (int i = 1; i < a.size(); i++) {
            ArrayList<Integer> row = a.get(i);
            for (int j = 1; j < row.size(); j++) {
                int currVal = row.get(j);
                if (currVal == 0) {
                    // set ref row to 0
                    a.get(i).set(0, 0);

                    // set ref col to 0
                    a.get(0).set(j, 0);
                }
            }
        }

        int colNum = firstRow.size();
        int rowNum = a.size();
        for (int row = 1; row < rowNum; row ++) {
            if (a.get(row).get(0) == 0) {
                for (int col = 1; col < colNum; col ++) a.get(row).set(col, 0);
            }
        }
        for (int col = 1; col < firstRow.size(); col ++) {
            if (a.get(0).get(col) == 0) {
                for (int row = 1; row < rowNum; row ++) a.get(row).set(col, 0);
            }
        }

        // change back first row
        if (firstRowZ) {
            for (int i = 0; i < firstRow.size(); i++) {
                firstRow.set(i, 0);
            }
        }

        // change back first col
        if (firstColZ) {
            for (int i = 0; i < a.size(); i++) {
                ArrayList<Integer> currRow = a.get(i);
                currRow.set(0, 0);
            }
        }
    }

    public static void rotate(ArrayList<ArrayList<Integer>> a) {
        int size = a.size();
        // Outer loop signifies the level
        for (int i = 0; i < size / 2; i++) {
            // Inner loop signifies the row/column individual element iterator
            for (int j = i; j < size - 1 - i; j++) {
                int topTemp = a.get(i).get(j);
                int rightTemp = a.get(j).get(size - i - 1);
                int bottomTemp = a.get(size - i - 1).get(size - j - 1);
                int leftTemp = a.get(size - j - 1).get(i);

                // Set new top
                a.get(i).set(j, leftTemp);

                // Set new right
                a.get(j).set(size - i - 1, topTemp);

                // Set new bottom
                a.get(size - i - 1).set(size - j - 1, rightTemp);

                // Set new left
                a.get(size - j - 1).set(i, bottomTemp);
            }

        }
    }

    public static String[] answer(String[] names) {
        Arrays.sort(names, new nameComparator());
        return names;
    }

    static class nameComparator implements Comparator<String>{
        @Override
        public int compare(String s1, String s2) {
            int s1Val = getValue(s1);
            int s2Val = getValue(s2);

            if (s1Val < s2Val) {
                // positive: first val is larger than second. this puts closer to the end.
                return 1;
            }
            else if (s1Val > s2Val) {
                // negative: first val is smaller than second
                return -1;
            }
            else {
                return s2.compareTo(s1);
            }
        }
    }

    public static int getValue(String val) {
        int sum = 0;
        for (int i = 0; i < val.length(); i++) {
            sum += (int)val.charAt(i) - 96;
        }
        return sum;
    }

    public static ArrayList<Integer> getRow(int a) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1); // Adds the header 1

        // Iterates through levels
        for (int k = 0; k < a; k++) {
            int val;
            int last = 0;

            // Iterates through each element within a level
            for (int i = 0; i < k + 1; i++) {
                val = res.get(i);

                // Condition states we are reading from within a valid
                // range.
                if (i - 1 >= 0) {

                    val += last;
                }
                last = res.get(i);
                res.set(i, val);
            }

            res.add(res.get(0)); // Adds the ender 1
        }

        return res;
    }

    public static String reverseString(String s) {
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length / 2; i++) {
            char temp = charArr[i];
            charArr[i] = charArr[charArr.length - i - 1];
            charArr[charArr.length - i - 1] = temp;
        }

        return new String(charArr);
    }
}
