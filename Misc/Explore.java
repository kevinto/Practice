import java.util.Arrays;
import java.util.Comparator;
import java.util.*;
import java.util.regex.Pattern;


/**
 * Created by kevint on 4/27/2016.
 */
public class Explore {
    public static void main(String[] args) {
        String s1 = Pattern.quote("begin M`@```/\\````8`````P```\"@=`0````````````````````````````8```#9M`0``,C$X,S4T````````3D92(]D!%@`!:`'T`?0!``#_G0`!``;_&`86ML`/M!8`*R6`*!W>0\"L$.\"(:5'F$3!8,72:$.!HN94J$8!G:3?N$0!(&.G4$.!HT.MFD()#Y8E^<(6\"8<J#4,4!X^0`L,*$)$H'B,-#)(C.<,)&8JIC:,6`X8RI>,.M!YI3[:,%*HU-\\2,&,8P_!:0-\"J!(2N0)\"(\\T7602\"HM/A@0'!I(^IJ0.\")$WMU@01\"XX&(`$#`A+_`?+_____!?$\"!Q7_________`/(!`!+_`_$&!\"((_]\\`M_Q__!?'_\"?@&`C$&!Q/___\\!`C(#!33_________\"?D'`V$$`!0#!30)\"E<'M_R\\!!%,#!F(*\"VP(_Y\\,\"R+_____`?X'\"I0.%#H*\"#8&!5G___\\.#Q@,\"&0&M!7L)_S\\(!RP&#-$/$A(-_R\\/$1H-\"S$'!KP*#F82$1?___\\(\"U(,#S,)_S__M____%/</#(82$S81#*$'\"M@.%(+_____\"/H-$6$3%5/_$/'_\"/H-$G@3%4+_M$_01#(,/#CL4%E'_%?(1#4,/$F06_U\\7%B03$I4*#G?_________$?(3%BC_J______\\3$E$/%\"07_R____\\3%O(4_R____\\!!$W5XP8`!P`$````````` ===end");
        String s2 = Pattern.quote("begin M`0```%D````M````!````*A;`0````````````````````````````(```#(M````,3$`````````````05!2.0<!`%D``/\\\\M`1H`&`````!`4'0?5%ST#AQBM-!Z*::-0`CGHT$'V,.`!TH.@/M]R$`D[DI`W2ZM02'?@D'::P\"91%S\")4?0B(UM#,8N^0--.!4=$T[5\"KE8E00::7GD;W&2E%#UJ90ISCC4`GY`I$QJ8]0E_G$4.MU;25!7S&\"0Y_V*4&8>R5!X+LZ04W[C49\\\\AQF!JTFIA8/+O88PCCJ!S@\\\\M@?VG2DH7MDRJ\")E0EA246*83D6;&'G!R2A7FAF88P::;V&,JFAA@`````` ===end");
        String common = s1.replaceAll("[" + s2 + "]", "");
//        common += s2.replaceAll("[" + s1 + "]", "");
        System.out.println(common);
//        String result = s1.replaceAll("[" + common + "]","");
//        System.out.println(result);
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
