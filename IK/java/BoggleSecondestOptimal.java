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
        String[] dict = {"geeks", "for", "quiz", "go"};
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
            for (int j = 0; j < board[0].length; j++) {
                if (root.children[board[i][j].charAt(0) - 'a'] != null) {
                    findWordsHelper(i, j, visited, sbPath, res, board, root.children[board[i][j].charAt(0) - 'a']);
                }
            }
        }

        String[] finalResult = new String[res.size()];
        int i = 0;
        for (String str : res) {
            finalResult[i++] = str;
        }

        return finalResult;
    }

    private static void findWordsHelper(int row, int col, HashSet<Point> visited, StringBuilder path, List<String> res,
                                        String[][] board, TrieNode root) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || root == null) {
            return;
        } else if (visited.contains(new Point(row, col))) {
            return;
        } else if (root.isWord) {
            path.append(root.val);
            res.add(path.toString());
            path.deleteCharAt(path.length() - 1);
            return;
        }

//        TrieNode matchingNode = root.children[board[row][col].charAt(0) - 'a'];
        Point currPoint = new Point(row, col);
        visited.add(currPoint);
        path.append(board[row][col].charAt(0));

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
                        || (i == row && j == col)) {
                    continue;
//                } else if (matchingNode.children[board[i][j].charAt(0) - 'a'] != null) {
                } else if (root.children[board[i][j].charAt(0) - 'a'] != null) {
                    findWordsHelper(i, j, visited, path, res, board, root.children[board[i][j].charAt(0) - 'a']);
//                    findWordsHelper(i, j, visited, path, res, board, matchingNode);
                }
            }
        }

        path.deleteCharAt(path.length() - 1);
        visited.remove(currPoint);
    }

    static class Point {
        int row;
        int col;

        Point(int r, int c) {
            this.row = r;
            this.col = c;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + this.row;
            result = prime * result + this.col;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj == null) {
                return false;
            } else if (this.getClass() != obj.getClass()) {
                return false;
            }

            Point other = (Point)obj;
            if (this.row == other.row && this.col == other.col) {
                return true;
            } else {
                return false;
            }
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
