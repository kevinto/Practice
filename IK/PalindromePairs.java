import java.util.*;

/**
 * Created by kevinto on 12/20/16.
 */
public class PalindromePairs {
    public static void main(String[] args) {
        String[] words1 = {"bat", "tab", "hello"};
        List<List<Integer>> result = palindromePairsUsingMap(words1);
        System.out.println(result);

        result = palindromePairsUsingTrie(words1);
        System.out.println(result);

        palindromePairsUsingMapPrint(words1);
        return;
    }

    public static void palindromePairsUsingMapPrint(String[] words) {
        if (words == null || words.length < 2) {
            return;
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            for (int idx = 0; idx <= words[i].length(); idx++) {
                String str1 = words[i].substring(0, idx);
                String str2 = words[i].substring(idx);
                if (isPal(str1)) {
                    String str2Reversed = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2Reversed) && map.get(str2Reversed) != i) {
                        System.out.println("Found: (" + i + ", " + map.get(str2Reversed) + ")");
                        return;
                    }
                }

                if (isPal(str2)) {
                    String str1Reversed = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1Reversed) && map.get(str1Reversed) != i) {
                        System.out.println("Found: (" + i + ", " + map.get(str1Reversed) + ")");
                        return;
                    }
                }
            }
        }
    }

    private static boolean isPal(String str) {
        int i = 0;
        int j = str.length() - 1;

        while (i <= j) {
            if (str.charAt(i++) != str.charAt(j--)) {
                return false;
            }
        }

        return true;
    }

    // --------- This solution uses TrieNodes ------------------------------
    static class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;

        TrieNode() {
            next = new TrieNode[26];
            index = -1; // This represents the index of the word in the word array. if this is not -1, then we have a word ending here.

            // the list will record the indices of all words satisfying the following two conditions:
            // each word has a suffix represented by the current Trie node; the rest of the word forms a palindrome.
            list = new ArrayList<>();
        }
    }

    public static List<List<Integer>> palindromePairsUsingTrie(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        // Root will be like an empty head.
        TrieNode root = new TrieNode();
        for (int wordIdx = 0; wordIdx < words.length; wordIdx++) {
            addWord(root, words[wordIdx], wordIdx);
        }

        for (int wordIdx = 0; wordIdx < words.length; wordIdx++) {
            search(words, wordIdx, root, result);
        }

        return result;
    }

    private static void addWord(TrieNode root, String word, int index) {
        // Add word from the end to the beginning
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';
            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }

            // Why are we adding the palindrome here?
            // - Because we only need to find a matching non-palindrome part.
            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }

            root = root.next[j];
        }

        // When we are done adding the word, we add the index.
        root.list.add(index); // If we are at the end of a word the next blank space is a palindrome.
        root.index = index;
    }

    private static void search(String[] words, int wordIdx, TrieNode root, List<List<Integer>> result) {
        for (int j = 0; j < words[wordIdx].length(); j++) {
            if (root.index >= 0 && root.index != wordIdx && isPalindrome(words[wordIdx], j, words[wordIdx].length() - 1)) {
                result.add(Arrays.asList(wordIdx, root.index));
            }

            root = root.next[words[wordIdx].charAt(j) - 'a'];
            if (root == null) return;
        }

        for (int j : root.list) {
            if (wordIdx == j) continue;
            result.add(Arrays.asList(wordIdx, j));
        }
    }

    private static boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }

        return true;
    }

    // Finds all palindrome pairs ---------- Uses HashMap
    public static List<List<Integer>> palindromePairsUsingMap(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) {
            return result;
        }

        // Create a string to index map.
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            // System.out.println(words[i]);

            // Go through the letters of the chosen words.
            for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                // Break the word into two pieces, we are going to try all two piece breaks
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    // If the frontal cut is a palindrome, then we need to find another
                    // word that is the reverse of the second part.
                    String str2Reversed = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2Reversed) && map.get(str2Reversed) != i) {
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(str2Reversed));
                        list.add(i);
                        result.add(list);
                    }
                }
                if (isPalindrome(str2)) {
                    // If the ending cut is a palindrome, then we need to find another
                    // word that is the reverse of the first part.
                    String str1Reversed = new StringBuilder(str1).reverse().toString();
                    // check "str.length() != 0" to avoid duplicates. when str2 len is 0,
                    // the previous str1 already took care of the reverse. When j is at the
                    // end of a word, str1 already takes care of the reverse of the entire word.
                    if (map.containsKey(str1Reversed) && map.get(str1Reversed) != i && str2.length() != 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(str1Reversed));
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    private static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) !=  str.charAt(right--)) return false;
        }
        return true;
    }
}
