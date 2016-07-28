import java.util.ArrayList;

/**
 * Created by Kevin on 7/24/16.
 */
public class CaptureRegions {
    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> board = new ArrayList<>();
        ArrayList<Character> r1 = new ArrayList<>();
        r1.add('X');
        r1.add('X');
        r1.add('X');
        r1.add('X');
        board.add(r1);

        ArrayList<Character> r2 = new ArrayList<>();
        r2.add('X');
        r2.add('O');
        r2.add('O');
        r2.add('X');
        board.add(r2);

        ArrayList<Character> r3 = new ArrayList<>();
        r3.add('X');
        r3.add('X');
        r3.add('O');
        r3.add('X');
        board.add(r3);

        ArrayList<Character> r4 = new ArrayList<>();
        r4.add('X');
        r4.add('O');
        r4.add('X');
        r4.add('X');
        board.add(r4);

        new CaptureRegions().solve(board);
        return;
    }

    public void solve(ArrayList<ArrayList<Character>> board) {
        if (board == null || board.size() == 0) return;

        int totalRows = board.size();
        int totalCols = board.get(0).size();

        // Traverse left and right boarders and change O to #
        for (int row = 0; row < totalRows; row++) {
            if (board.get(row).get(0) == 'O') {
                changeOtoHash(board, row, 0);
            }

            if (board.get(row).get(totalCols - 1) == 'O') {
                changeOtoHash(board, row, totalCols - 1);
            }
        }

        // Traverse top and bottom boarders and change O to #
        for (int col = 0; col < totalCols; col++) {
            if (board.get(0).get(col) == 'O') {
                changeOtoHash(board, 0, col);
            }

            if (board.get(totalRows - 1).get(col) == 'O') {
                changeOtoHash(board, totalRows - 1, col);
            }
        }

        // change all leftever O to X and hash back to O
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalCols; col++) {
                if (board.get(row).get(col) == 'O') {
                    board.get(row).set(col, 'X');
                }
                if (board.get(row).get(col) == '#') {
                    board.get(row).set(col, 'O');
                }
            }
        }
    }

    public void changeOtoHash(ArrayList<ArrayList<Character>> board, int row, int col) {
        if (row < 0 || col < 0 || row >= board.size() || col >= board.get(0).size()) {
            return;
        }
        if (board.get(row).get(col) != 'O') {
            return;
        }

        board.get(row).set(col, '#');
        changeOtoHash(board, row - 1, col);
        changeOtoHash(board, row + 1, col);
        changeOtoHash(board, row, col + 1);
        changeOtoHash(board, row, col - 1);
    }
}
