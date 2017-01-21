import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by kevinto on 1/19/17.
 While the queue is not empty:
     Dequeue a triple (x, y, s)
     For each square (x', y') with letter c adjacent to (x, y):
         If s+c is a word, output s+c
         If s+c is a prefix of a word, insert (x', y', s+c) into the queue

 Lets try doing this with DFS instead.
 - Generate a trie from a given word array
 - Need to iterate through all positions in board. DFS using that position as the start position.
 - DFS:
    Base case:
        if out of bounds return
        if current node has isWord, print path so far.
        if current char doesnt have a matching entry in root.children, return.

    Recursive case:
        - Add current node to visited set
        - Add current char to path
        - recurse on all possible paths passing in next coordinates, path, visited set
        - remove current node from visited set
 */
public class BoggleSecondestOptimal {
    public static void main(String[] args) {
        String[] dict = {"geeks", "for",                                "quiz", "go"};
        String[][] board = new String[][] {
                {"g", "i", "z"},
                {"u", "e", "k"},
                {"q", "s", "e"}
        };

        String[] res = findWords(dict, board);
        System.out.println(Arrays.toString(res));
    }

    public static String[] findWords(String[] dict, String[][] board) {
        if (dict == null || board == null || dict.length == 0 || board.length == 0) {
            return new String[0];
        }

        TrieNode root = new TrieNode('-');
        root.isRoot = true;
        generateTrie(root, dict);

        HashSet<Point> visited = new HashSet<>();
        StringBuilder sbPath = new StringBuilder();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; i < board[0].length; j++) {
                findWordsHelper(i, j, visited, sbPath, res);
            }
        }

        return null;
    }

    private static void findWordsHelper(int row, int col, HashSet<Point> visited, StringBuilder path, List<String> res) {
        // TODO: Implement this.
    }

    static class Point {
        int row;
        int col;

        Point(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    private static void generateTrie(TrieNode root, String[] dict) {
        for (String word : dict) {
            root.insert(word, 0, root);
        }
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
        boolean isRoot;
        char val;

        TrieNode(char v) {
            this.val = v;
            children = new TrieNode[26];
            isWord = false;
            isRoot = false;
        }

        public void insert(String word, int pos, TrieNode root) {
            if (pos >= word.length()) {
                root.isWord = true;
                return;
            }

            int curr = word.charAt(pos) - 'a';
            if (root.children[curr] == null) {
                root.children[curr] = new TrieNode(word.charAt(pos));
            }

            insert(word, pos + 1, root.children[curr]);
            //System.out.println("isRoot: " + root.isRoot);
        }
    }


}
