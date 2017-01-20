import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
/**
 * Created by kevinto on 1/19/17.
 */

public class BoggleMostOptimal {
        private static final int N = 3;
        private static char[][] board = new char[N][N];
        private static DictNode root;

        private static class DictNode {
            public final char letter;
            public DictNode[] nextNodes = new DictNode[26];
            public boolean wordEnd;

            public DictNode(final char letter) {
                this.letter = letter;
            }

            public void insert(final String word) {
                DictNode node = root;
                char[] letters = word.toCharArray();
                for (int i = 0; i < letters.length; i++) {
                    if (node.nextNodes[letters[i] - 'a'] == null) {
                        node.nextNodes[letters[i] - 'a'] = new DictNode(letters[i]);

                        if (i == letters.length-1) {
                            node.nextNodes[letters[i] - 'a'].wordEnd = true;
                        }
                    }

                    node = node.nextNodes[letters[i] - 'a'];
                }
            }

            public boolean contains(final String word) {
                DictNode node = root;
                char[] letters = word.toCharArray();
                int i = 0;
                while (i < letters.length && node.nextNodes[letters[i] - 'a'] != null) {
                    node = node.nextNodes[letters[i] - 'a'];
                    i++;
                }

                return (i == letters.length) && node.wordEnd;

            }
        }

        public static boolean isInBoard(final String word) {
            int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
            int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

            boolean[][][] dp = new boolean[50][N][N];
            char[] letters = word.toCharArray();

            for (int k = 0; k < letters.length; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (k == 0) {
                            dp[k][i][j] = true;
                        }
                        else {
                            for (int l = 0; l < 8; l++) {
                                int x = i + dx[l];
                                int y = j + dy[l];

                                if ((x >= 0) && (x < N) && (y >= 0) && (y < N)
                                        && (dp[k - 1][x][y]) && (board[i][j] == letters[k])) {
                                    dp[k][i][j] = true;
                                    if (k == letters.length-1) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return false;
        }

        public static void boggleTrieDynamic(DictNode node, char[] currentBranch, int currentHeight) {
            if (node == null) {
                return;
            }

            if (node.wordEnd) {
                String word = new String(currentBranch, 0, currentHeight);
                boolean inBoard = isInBoard(word);
                if (inBoard) {
                    System.out.println(word);
                }
            }

            for (int i = 0; i < node.nextNodes.length; i++) {
                if (node.nextNodes[i] != null) {
                    currentBranch[currentHeight] = (char) (i + 'a');
                    boggleTrieDynamic(node.nextNodes[i], currentBranch, currentHeight + 1);
                }
            }
        }

        public static void readBoard() throws IOException {
            board = new char[][] {
                    {'g', 'i', 'z'},
                    {'u', 'e', 'k'},
                    {'q', 's', 'e'}
            };
        }

        public static void readDict() throws IOException {
            List<String> words = new ArrayList<>();
            words.add("geeks");
            words.add("for");
            words.add("quiz");
            words.add("go");

            root = new DictNode('\0');
            for (String word : words) {
                root.insert(word);
            }
        }

        public static void main(String[] args) throws IOException {
            readDict();

            readBoard();

            long start = System.currentTimeMillis();

            boggleTrieDynamic(root, new char[50], 0);

            long end = System.currentTimeMillis();

            System.out.println("Total time spent = " + (end - start) + " milli seconds.");

            System.out.println("Done...");
        }
    }

