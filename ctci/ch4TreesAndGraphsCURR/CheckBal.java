package ch4TreesAndGraphsCURR;

/**
 * Created by Kevin on 6/26/16.
 */
public class CheckBal {
    public static void main(String[] args) {
//        BalNode root = new BalNode(5);
//        root.left = new BalNode(6);
//        root.left.left = new BalNode(7);
//        root.left.left.left = new BalNode(8);
//        root.right = new BalNode(9);

        BalNode root = new BalNode(1);
        root.right = new BalNode(2);
        root.right.right = new BalNode(3);
        System.out.println(balanced(root));
    }

    // This implementation is wrong we need to check if each subtree is balanced also.!!!
    private static boolean balanced(BalNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = balHelper(root.left) + 1;
        int rightHeight = balHelper(root.right) + 1;
        return Math.abs(leftHeight - rightHeight) <= 1;
    }

    private static int balHelper(BalNode root) {
        if (root == null ) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 0;
        }

        int leftHeight = balHelper(root.left) + 1;
        int rightHeight = balHelper(root.right) + 1;
        return Math.max(leftHeight, rightHeight);
    }
}

class BalNode {
    int val;
    BalNode left;
    BalNode right;

    BalNode(int x) {
        this.val = x;
    }
}
