import java.util.HashMap;

/**
 * Created by Kevin on 7/16/16.
 */
public class SmallestSubString {
    public static void main(String[] args) {
//        String test1 = "xyyzyzyx";
//        char[] arr1 = { 'x', 'z', 'y' };
//        System.out.println(getShortestUniqueSubString(arr1, test1));

//        String test2 = "xyz";
//        char[] arr2 = { 'x', 'z', 'y' };
//        System.out.println(getShortestUniqueSubString(arr2, test2));

//        String test3 = "axxxbcxa";
//        char[] arr3 = { 'a', 'b', 'c' };
//        System.out.println(getShortestUniqueSubString(arr3, test3));

        String test4 = "axxxbcxxxxxxxxx";
        char[] arr4 = { 'a', 'b', 'c' };
        System.out.println(getShortestUniqueSubString(arr4, test4));
    }

    public static String getShortestUniqueSubString(char[] arr, String str) {
        int tailIdx = 0;
        String result = "";
        int unqiueCounter = 0;
        HashMap<Character, Integer> countMap = new HashMap<>();

        for (char c : arr) {
            countMap.put(c, 0);
        }

        // Will the tail ever get ahead of the head?
        // It should not because head will go all the way until
        // we find our first subsequence. Tail will move until we
        // reach in invalid subsequence. Then we will move the head
        // to a valid subsequence again.
        for (int headIdx = 0; headIdx < str.length(); headIdx++) {
            // Handle the new head -- This block will run until we
            // found the FIRST substring that satisfies our condition.
            char head = str.charAt(headIdx);
            if (!countMap.containsKey(head)) continue;
            int headCt = countMap.get(head);
            if (headCt == 0) unqiueCounter++;
            countMap.put(head, headCt + 1);

            // Push tail forward -- Going to push tail forward until we
            // no longer have a valid substring
            while (unqiueCounter == arr.length) {
                int tempLength = headIdx - tailIdx + 1;
                if (tempLength == arr.length) {
                    return str.substring(tailIdx, headIdx + 1);
                }

                if (result.length() == 0 || tempLength < result.length()) {
                    result = str.substring(tailIdx, headIdx + 1);
                }

                char tail = str.charAt(tailIdx);
                if (countMap.containsKey(tail)) {
                    int tailCt = countMap.get(tail) - 1;
                    if (tailCt == 0) unqiueCounter--;
                    countMap.put(tail, tailCt);
                }
                tailIdx++;
            }
        }
        return result;
    }
}
