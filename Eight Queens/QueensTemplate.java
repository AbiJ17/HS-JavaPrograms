public class QueensTemplate {

    private final static int N = 8; // Number of queens and dimensions of the chess board
    private static int[][] queenBoard = new int[N][N]; // Chess board to track placements of queens

    public static void main(String[] args) {
        // Initialize the board
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                queenBoard[row][col] = 0;
            }
        }

        // Start solving the problem from the first column
        if (solve(0)) {
            printQueens();
        } else {
            System.out.println("No solution exists.");
        }
    }

    private static boolean solve(int col) {
        if (col >= N) {
            return true; // All queens have been placed
        }

        for (int row = 0; row < N; row++) {
            if (isSafe(row, col)) {
                queenBoard[row][col] = 1; // Place queen

                // Recur to place rest of the queens
                if (solve(col + 1)) {
                    return true;
                }

                // If placing queen in row, col leads to a solution then return true
                queenBoard[row][col] = 0; // BACKTRACK
            }
        }

        // If the queen cannot be placed in any row in this column col then return false
        return false;
    }

    // Check if a queen can be placed on board[row][col]
    private static boolean isSafe(int row, int col) {
        int i, j;

        // Check this row on left side
        for (i = 0; i < col; i++) {
            if (queenBoard[row][i] == 1)
                return false;
        }

        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (queenBoard[i][j] == 1)
                return false;
        }

        // Check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (queenBoard[i][j] == 1)
                return false;
        }

        return true;
    }

    // Print out an N-by-N display of the board
    private static void printQueens() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (queenBoard[i][j] == 1) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}
