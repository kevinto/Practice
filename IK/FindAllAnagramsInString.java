import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinto on 1/17/17.
 */
public class FindAllAnagramsInString {
    public static void main(String[] args) {
        List<Integer> res = getStarts2("cbaebabacd", "abc");
        System.out.println(res);
    }

    public static List<Integer> getStarts2(String search, String pattern) {
        List<Integer> res = new ArrayList<>();
        if (search == null || pattern == null
                || search.length() < pattern.length()) {
            return res;
        }

        int[] map = new int[256];
        int patLen = pattern.length();
        for (int i = 0; i < patLen; i++) {
            map[pattern.charAt(i)]++;
        }


        int count = 0;
        int left = 0;
        int right = 0;
        int searchLen = search.length();
        while (right < searchLen) {
            if (map[search.charAt(right)] > 0) {
                count++;
            }
            map[search.charAt(right)]--;
            right++;

            if (count == patLen) {
                res.add(left);
            }

            if (right - left == patLen) {
                if (map[search.charAt(left)] >= 0) {
                    count--;
                    map[search.charAt(left)]++; // Why is putting it in here WRONG?
                }
//                map[search.charAt(left)]++;
                left++;
            }
        }

        return res;
    }
}
