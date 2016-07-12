package ch4TreesAndGraphs;

/**
 * Created by kevint on 6/28/2016.
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {
        LCAnode root = new LCAnode(1);
        root.left = new LCAnode(2);
        root.right = new LCAnode(3);
        System.out.println(findLca(root, 3, 100).val);
    }

    private static LCAnode findLca(LCAnode root, int n1, int n2) {
        if (root == null) {
            return null;
        }

        /*
        * There is a bug in here where you find one node but not the other.
        * If either n1 or n2 matches with root's key, report the presence
        * by returning root (Note that if a key is ancestor of other,
        * then the ancestor of other, then the ancestor key becomes LCA
        * */
        if (root.val == n1 || root.val == n2) {
            return root;
        }

        // Look for the keys in the left and right subtrees
        LCAnode leftLca = findLca(root.left, n1, n2);
        LCAnode rightLca = findLca(root.right, n1, n2);

        /*
        * If both of the above calls return non-null, then one key
         * is present in one subtree and other is present in the other.
         * So this node is the LCA
        * */
        if (leftLca != null && rightLca != null) {
            return root;
        }

        // Otherwise check if left subtree or right subtree is LCA
        return (leftLca != null) ? leftLca : rightLca;
    }
}

class LCAnode {
    LCAnode left;
    LCAnode right;
    int val;

    LCAnode (int x) {
            val = x;
    }
}
