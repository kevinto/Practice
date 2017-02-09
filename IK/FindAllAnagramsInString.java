import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

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
//                    map[search.charAt(left)]++; // Why is putting it in here WRONG?
                }
                map[search.charAt(left)]++;
                left++;
            }
        }

        return res;
    }

    // Taken from the Leetcode template here:
    // https://leetcode.com/problems/find-all-anagrams-in-a-string/?tab=Solutions
    public List<Integer> findAnagramsUsingLeetCodeTemplate(String s, String t) {
        List<Integer> result = new LinkedList();
        if(t.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;


        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;

            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == t.length()){
                    result.add(begin);
                }
                begin++;
            }

        }
        return result;
    }

    // The below implementation was for a coding exercise. Took me 40 minutes to arrive at.
    public static List<Integer> findAnagramStartsCodeExercise(String str, String pat) {
        // TODO: Input checking...

        int strLen = str.length();
        int patLen = pat.length();
        int[] map = genFreqMap(pat);
        List<Integer> result = new ArrayList<>();
        int count = 0;
        int left = 0, right = 0;

        while (right < strLen) {
            char currRight = str.charAt(right);
            map[currRight]--;
            if (map[currRight] >= 0) {
                count++;
            }
            right++;

            if (count == patLen) {
                while (count == patLen) {
                    char currLeft = str.charAt(left);
                    if (map[currLeft] + 1 == 1) {
                        break;
                    }
                    map[currLeft]++;
                    left++;
                }

                if (right - left == patLen) {
                    result.add(left);
                }
            }
        }

        return result;
    }

    private static int[] genFreqMap(String str) {
        int[] map = new int[256];
        int len = str.length();

        for (int i = 0; i < len; i++) {
            map[str.charAt(i)]++;
        }

        return map;
    }
}
