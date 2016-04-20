import java.util.HashMap;

/**
 * Created by Kevin on 4/20/16.
 */
public class TrieClass {
    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("He");
        System.out.println("Done");
        // TODO: Debug through the search implementation
    }
}

class Trie {
    // Sample implementation here: http://www.geeksforgeeks.org/longest-prefix-matching-a-trie-based-solution-in-java/
    private TrieNode root;

    public Trie() {
        root = new TrieNode((char)0);
    }

    public void insert(String word) {
        int length = word.length();
        TrieNode crawl = root;

        for (int level = 0; level < length; level++) {
            HashMap<Character, TrieNode> child = crawl.getChildren();
            char ch = word.charAt(level);

            if (child.containsKey(ch)) {
                crawl = child.get(ch);
            }
            else {
                TrieNode temp = new TrieNode(ch);
                child.put(ch, temp);
                crawl = temp;
            }
        }
        crawl.setIsEnd(true);
    }

    public String getMatchingPrefix(String input) {
        // TODO: finish trie seach implementation
        return "";
    }
}

class TrieNode {
    private char value;
    private HashMap<Character, TrieNode> children;
    private boolean bIsEnd;

    public TrieNode(char ch) {
        value = ch;
        children = new HashMap<>();
        bIsEnd = false;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public char getValue() {
        return value;
    }

    public void setIsEnd(boolean val) {
        bIsEnd = val;
    }

    public boolean isEnd() {
        return bIsEnd;
    }
}
