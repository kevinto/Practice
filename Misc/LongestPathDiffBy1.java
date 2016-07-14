import java.util.ArrayList;

/**
 * Created by Kevin on 7/14/16.
 */
public class LongestPathDiffBy1 {
    public static String findLongest(LongDiffNode root) {
        if (root == null) return "";

        ArrayList<String> paths = new ArrayList<>();
        findLongest(root, "", paths, 0);

        return "";
    }

    public static void findLongest(LongDiffNode root, String path, ArrayList<String> paths, int prevVal) {
        boolean diffby1 = Math.abs(prevVal - root.val) != 1;
        if (!diffby1) {
            if (!path.isEmpty()) paths.add(path);
            path = "";
        }
        path += root.val;

        if (root.left == null && root.right == null) {
            if (diffby1 && !path.isEmpty()) {
                paths.add(path);
            }
            return;
        }
    }
}

class LongDiffNode {
    LongDiffNode left;
    LongDiffNode right;
    int val;
}
