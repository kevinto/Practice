import java.util.ArrayList;

/**
 * Created by Kevin on 7/14/16.
 */
public class LongestPathDiffBy1 {
    public static void main(String[] args) {
        LongDiffNode root = new LongDiffNode(9);
        root.left = new LongDiffNode(8);
        root.right = new LongDiffNode(10);

        root.left.left = new LongDiffNode(6);
        root.left.left.left = new LongDiffNode(5);
        root.left.left.left.left = new LongDiffNode(4);

        findLongest(root);
        return;
    }

    public static String findLongest(LongDiffNode root) {
        if (root == null) return "";

        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        findLongest(root, new ArrayList<>(), paths, Integer.MAX_VALUE);
        // TODO: we can use a heap here to keep track if the longest path.
        return "";
    }

    public static void findLongest(LongDiffNode root, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths, int prevVal) {
        if (root == null) return;
        boolean diffby1 = Math.abs(prevVal - root.val) == 1;
        if (!diffby1 && !path.isEmpty()) {
            paths.add(new ArrayList<>(path));
            path = new ArrayList<>(); // if i convert to arraylist then i lose track of upstream
        }
        path.add(root.val);

        if (root.left == null && root.right == null) {
            if (diffby1 && path.size() > 1) {
                paths.add(new ArrayList<>(path));
            }
            path.remove(path.size() - 1);
            return;
        }

        findLongest(root.left, path, paths, root.val);
        findLongest(root.right, path, paths, root.val);
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
