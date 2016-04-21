import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.HashMap;

/**
 * Created by Kevin on 4/20/16.
 */
public class TrieClass {
    public static void main(String[] args) {
        Trie t = new Trie();

        System.out.println("Inserting 'He' and 'Hello' and 'Able' into the trie...");
        t.insert("He");
        t.insert("Hell");
        t.insert("Able");
        t.insert("Abled");
        System.out.println("");

        System.out.println("Search tests...");
        System.out.println("He exists: " + t.search("He"));
        System.out.println("Hel exists: " + t.search("Hel"));
        System.out.println("Hell exists: " + t.search("Hell"));
        System.out.println("");

        System.out.println("Deletion tests...");
        t.delete("He");
        t.delete("Able");
        t.delete("Nan"); // We are trying to delete a non-existant value here. Works.
        System.out.println("Does delete 'He' still exist? : " + t.search("He"));
        System.out.println("Does delete 'Able' still exist? : " + t.search("Able"));
        System.out.println("Does 'Hell' still exist? : " + t.search("Hell"));
        System.out.println("Does 'Abled' still exist? : " + t.search("Abled"));
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

    public boolean search(String key) {
        int level;
        int length = key.length();
        TrieNode crawl = root;

        for (level = 0; level < length; level++) {
            HashMap<Character, TrieNode> child = crawl.getChildren();
            char ch = key.charAt(level);

            if (!child.containsKey(ch)) {
                return false;
            }

            crawl = child.get(ch);
        }

        return (crawl != null && crawl.isEnd());
    }

    public void delete (String key) {
        // Link: http://www.geeksforgeeks.org/trie-delete/
        /*
        Need to take care of all these delete scenarios:
            1. Key may not be there in trie. Delete operation should not modify trie.

            2. Key present as unique key (no part of key contains another key (prefix),
               nor the key itself is prefix of another key in trie). Delete all the nodes.

            3. Key is prefix key of another long key in trie. Unmark the leaf node.

            4. Key present in trie, having atleast one other key as prefix key. Delete
               nodes from end of key until first leaf node of longest prefix key.
         */

        int len = key.length();
        if (len > 0) {
            deleteHelper(root, key, 0, len);
        }
    }

    private boolean deleteHelper(TrieNode node, String key, int level, int len) {
        if (node != null) {
            // Base case
            if (level == len) {
                if (node.isEnd()) {
                    node.setIsEnd(false);
                    return hasNoChildren(node);
                }
            }
            else { // Recursive case
                HashMap<Character, TrieNode> children = node.getChildren();
                char ch = key.charAt(level);

                if (deleteHelper(children.get(ch), key, level + 1, len)) {
                    children.remove(ch);

                    // Delete this current node if it is not the end
                    // and it has no children. We check if there is
                    // an ending if there is a pre-existing prefix.
                    return ( !node.isEnd() && hasNoChildren(node) );
                }
            }
        }

        return false;
    }

    private boolean hasNoChildren(TrieNode node) {
        HashMap<Character, TrieNode> children = node.getChildren();
        return children.isEmpty();
    }
}

class TrieNode {
    private char value;
    private HashMap<Character, TrieNode> children;
    private boolean bIsEnd;

    TrieNode(char ch) {
        value = ch;
        children = new HashMap<>();
        bIsEnd = false;
    }

    HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public char getValue() {
        return value;
    }

    void setIsEnd(boolean val) {
        bIsEnd = val;
    }

    boolean isEnd() {
        return bIsEnd;
    }
}
