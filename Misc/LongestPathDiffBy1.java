import java.util.ArrayList;

/**
 * Created by Kevin on 7/14/16.
 */
public class LongestPathDiffBy1 {
    private static ArrayList<Integer> longestSubStr;
    public static void main(String[] args) {
        longestSubStr = new ArrayList<>();

        LongDiffNode root = new LongDiffNode(9);
        root.left = new LongDiffNode(8);
        root.right = new LongDiffNode(10);

        root.left.left = new LongDiffNode(6);
        root.left.left.left = new LongDiffNode(5);
        root.left.left.left.left = new LongDiffNode(4);

        System.out.println(findLongest(root));
        return;
    }

    public static String findLongest(LongDiffNode root) {
        if (root == null) return "";

        findLongest(root, new ArrayList<>(), Integer.MAX_VALUE);
        return longestSubStr.toString();
    }

    public static void findLongest(LongDiffNode root, ArrayList<Integer> path, int prevVal) {
        if (root == null) return;
        boolean diffby1 = Math.abs(prevVal - root.val) == 1;
        if (!diffby1 && !path.isEmpty()) {
            if (path.size() > longestSubStr.size()) longestSubStr = new ArrayList<>(path);
            path = new ArrayList<>();
        }
        path.add(root.val);

        if (root.left == null && root.right == null) {
            if (path.size() > longestSubStr.size()) longestSubStr = new ArrayList<>(path);
            path.remove(path.size() - 1);
            return;
        }

        findLongest(root.left, path, root.val);
        findLongest(root.right, path, root.val);
        path.remove(path.size() - 1);
    }
}

class LongDiffNode {
    LongDiffNode left;
    LongDiffNode right;
    int val;
    LongDiffNode (int v) {
        this.val = v;
    }
}
