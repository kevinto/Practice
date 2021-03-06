package p338;

import java.util.Arrays;

/**
 * Created by kevint on 4/26/2016.
 */
public class CountBitsFile {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(5)));
    }

    public static int[] countBits(int num) {
        int[] ret = new int[num+1];
        ret[0] = 0;
        int pow = 1;
        for(int i = 1, t = 0; i <= num; i++, t++) {
            if(i == pow) {
                pow *= 2;
                t = 0;
            }
            ret[i] = ret[t] + 1;
        }
        return ret;
    }

    public static int[] countBits3(int num) {
        int[] answer = new int[num+1];
        int offset = 1;

        // Element at pos 0 is always 0, So start at pos 1.
        for(int i = 1; i < answer.length; i++){
            if(offset * 2 == i) offset *= 2;
            answer[i] = 1 + answer[i - offset];
        }
        return answer;
    }

    public static int[] countBits2(int num) {
        int[] retArr = new int[num + 1];
        for (int i = 0; i < num + 1; i++) {
            if (i == 0) {
                retArr[i] = 0;
            }
            else {
                String binNum = Integer.toBinaryString(i);
                int oneCount = findNumOnes(binNum);
                retArr[i] = oneCount;
            }
        }

        return retArr;
    }

    public static int findNumOnes(String binString) {
        int count = 0;
        for (int i = 0; i < binString.length(); i++) {
            char currChar = binString.charAt(i);
            if (currChar == '1') {
                count++;
            }
        }
        return count;
    }
}
