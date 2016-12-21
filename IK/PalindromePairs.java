import java.util.*;

/**
 * Created by kevinto on 12/20/16.
 */
public class PalindromePairs {
    public static void main(String[] args) {
        String[] words1 = {"bat", "tab", "hello"};
        List<List<Integer>> result = palindromePairs(words1);
        return;
    }

    // --------- This solution uses TrieNodes ------------------------------
    class TrieNode {
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

    public List<List<Integer>> palindromePairsUsingTrie(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(words, i, root, result);
        }

        return result;
    }

    private void addWord(TrieNode root, String word, int index) {
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
        root.list.add(index);
        root.index = index;
    }

    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j++) {
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }

            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) return;
        }

        for (int j : root.list) {
            if (i == j) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }

        return true;
    }

    // Finds all palindrome pairs ---------- Uses HashMap
    public static List<List<Integer>> palindromePairs(String[] words) {
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
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2Reversed = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2Reversed) && map.get(str2Reversed) != i) {
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(str2Reversed));
                        list.add(i);
                        result.add(list);
                    }
                }
                if (isPalindrome(str2)) {
                    String str1Reversed = new StringBuilder(str1).reverse().toString();
                    // check "str.length() != 0" to avoid duplicates
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
