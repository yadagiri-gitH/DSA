package com.take.u.forward;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public static void solveNQueens(int boardSize) {

        char[][] board = new char[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> result = new ArrayList();
        setNQueens(0, board, result);
        System.out.println(result);

    }

    public static void setNQueens(int col, char[][] board, List<List<String>> result) {
        if (col == board.length) {
            result.add(convertToList(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (isSafePlaceForQueen(board, row, col)) {
                board[row][col] = 'Q';
                setNQueens(col + 1, board, result);
                board[row][col] = '.';
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

    public static boolean isSafePlaceForQueen(char[][] board, int row, int col) {

        int row1 = row;
        int col1 = col;

        while (col >= 0) {
            if (board[row][col] == 'Q') {
                return false;
            }
            col--;
        }

        row = row1;
        col = col1;

        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q') {
                return false;
            }
            row--;
            col--;
        }

        row = row1;
        col = col1;

        while (row < board.length && col >= 0) {
            if (board[row][col] == 'Q') {
                return false;
            }
            row++;
            col--;
        }

        return true;
    }


    public static void main(String[] args) {

        solveNQueens(8);

    }
}