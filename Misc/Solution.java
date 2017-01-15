import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public static void main(String[] args) {
        HashSet<Tile> visited = new HashSet<>();
        visited.add(new Tile(1, 1));
        if (visited.contains(new Tile(1, 1))) {
            System.out.println("passed");
        } else {
            System.out.println("failed");
        }
    }

    private static class Tile {
        int row;
        int col;
        int moves;

        Tile(int row, int col) {
            this.row = row;
            this.col = col;
        }

        Tile(int row, int col, int moves) {
            this.row = row;
            this.col = col;
            this.moves = moves;
        }

        public boolean equals(Tile t2) {
            return this.row == t2.row && this.col == t2.col;
        }

        public int hashCode() {
            return 0;
        }
    }
}

