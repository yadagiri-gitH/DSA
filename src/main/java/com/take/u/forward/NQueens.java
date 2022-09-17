package com.take.u.forward;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    public static boolean isSafePlace(char[][] m, int row, int col) {
        int r = row;
        int c = col;

        while (c >= 0) {
            if (m[r][c] == 'Q')
                return false;
            c--;
        }

        r = row;
        c = col;

        while (r >= 0 && c >= 0) {
            if (m[r][c] == 'Q')
                return false;
            r--;
            c--;
        }

        r = row;
        c = col;

        while (r < m.length && c >= 0) {
            if (m[r][c] == 'Q')
                return false;
            r++;
            c--;
        }

        return true;
    }

    public static void setQueens(char[][] board, int col, List<List<String>> result) {
        if (col == board.length) {
            result.add(construct(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (isSafePlace(board, row, col)) {
                board[row][col] = 'Q';
                setQueens(board, col + 1, result);
                board[row][col] = '.';
            }
        }

    }

    public static List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            result.add(new String(board[i]));
        }
        return result;
    }


    public static void solveNQueens(int n) {
        char[][] board = new char[4][4];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                board[row][col] = '.';
            }
        }

        List<List<String>> boardResult = new ArrayList<>();
        setQueens(board, 0, boardResult);
        System.out.println(boardResult);

    }

    public static void main(String[] args) {
        solveNQueens(4);
    }

}
