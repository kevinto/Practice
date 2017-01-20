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
        char[][] board = new char[][] {
                    {'g', 'i', 'z'},
                    {'u', 'e', 'k'},
                    {'q', 's', 'e'}
        };

    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[256];
        boolean isWord;

        TrieNode() {
            children = new TrieNode[256];
            isWord = false;
        }
    }
}
