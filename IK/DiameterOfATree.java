import java.util.ArrayList;

/**
 * Created by kevinto on 12/29/16.
 */
public class DiameterOfATree {
    public static void main(String[] args) {
    }

    public static int diameter(TreeNode tree) {
        if (tree == null)
            return -1;

        return diameterRecursion(tree).diameter;
    }

    private static DiameterReturnValue diameterRecursion(TreeNode root) {
        if (root == null) {
            return new DiameterReturnValue(0, 0);
        } else if (root.sons == null || root.sons.size() == 0) {
            return new DiameterReturnValue(root.distanceFromFather, 0);
        }

        int mostDistantSon = Integer.MIN_VALUE;
        int secondMostDistantSon = Integer.MIN_VALUE;
        DiameterReturnValue rootRetObj = new DiameterReturnValue(-1, 0);
        for (TreeNode son : root.sons) {
            DiameterReturnValue sonRetObj = diameterRecursion(son);
            rootRetObj.diameter = Math.max(rootRetObj.diameter, sonRetObj.diameter);
            if (sonRetObj.distanceToMostDistantLeaf > mostDistantSon) {
                secondMostDistantSon = mostDistantSon;
                mostDistantSon = sonRetObj.distanceToMostDistantLeaf;
            } else if (sonRetObj.distanceToMostDistantLeaf > secondMostDistantSon) {
                secondMostDistantSon = sonRetObj.distanceToMostDistantLeaf;
            }
        }

        rootRetObj.distanceToMostDistantLeaf = mostDistantSon + root.distanceFromFather;
        rootRetObj.diameter = Math.max(mostDistantSon + secondMostDistantSon, rootRetObj.diameter);

        return rootRetObj;
    }

    private static class DiameterReturnValue {
        int diameter, distanceToMostDistantLeaf;

        DiameterReturnValue(int dia, int dis) {
            this.diameter = dia;
            this.distanceToMostDistantLeaf = dis;
        }
    }

    private static class TreeNode {
        int distanceFromFather;
        ArrayList<TreeNode> sons;
    }
}
