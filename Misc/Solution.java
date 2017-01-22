import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        String[] words1 = {"cat"};
        palindromePairsUsingMap(words1);
    }

    public static void palindromePairsUsingMap(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            int sum = 0;
            for (int j = 0; j <= words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                System.out.println("str1: " + str1 + ", str2: " + str2);
                sum += 1;
            }
            System.out.println("sumFirst: " + sum);
            System.out.println();

            sum = 0;
            for (int j = 1; j <= words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                System.out.println("str1: " + str1 + ", str2: " + str2);
                sum += 1;
            }
            System.out.println("sumSecond: " + sum);
            System.out.println();
        }
    }
}

