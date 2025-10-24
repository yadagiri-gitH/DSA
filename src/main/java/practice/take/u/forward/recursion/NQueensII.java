package practice.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueensII {
    public static void solveNQueens(int boardSize) {

        char[][] board = new char[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> result = new ArrayList();
        boolean[] leftRow = new boolean[boardSize];
        boolean[] lowerDiagonal = new boolean[2 * boardSize - 1];//2*8-1=15
        boolean[] upperDiagonal = new boolean[2 * boardSize - 1];//2*8-1=15
        setNQueens(0, board, leftRow, lowerDiagonal, upperDiagonal, result);
        System.out.println(result);

    }

    public static void setNQueens(int col, char[][] board, boolean[] leftRow, boolean[] lowerDiagonalRow, boolean[] upperDiagonalRow, List<List<String>> result) {
        if (col == board.length) {
            result.add(convertToList(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (!leftRow[row] && !lowerDiagonalRow[row + col] && !upperDiagonalRow[board.length - 1 + (col - row)]) {
                board[row][col] = 'Q';
                leftRow[row]=true;
                lowerDiagonalRow[row + col]=true;
                upperDiagonalRow[board.length - 1 + (col - row)]=true;
                setNQueens(col + 1, board, leftRow, lowerDiagonalRow, upperDiagonalRow, result);
                board[row][col] = '.';
                leftRow[row]=false;
                lowerDiagonalRow[row + col]=false;
                upperDiagonalRow[board.length - 1 + (col - row)]=false;
            }
        }

    }

    public static List<String> convertToList(char[][] board) {
        List<String> list = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            list.add(new String(board[row]));
        }
        return list;
    }

    public static void main(String[] args) {
        solveNQueens(4);
    }
}