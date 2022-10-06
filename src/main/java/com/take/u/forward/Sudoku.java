package com.take.u.forward;

public class Sudoku {
    public static boolean solveSudoku(int[][] puzzle) {

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if (puzzle[i][j] == 0) {
                    for (char c = 1; c <= 9; c++) {
                        if (isValid(i, j, puzzle, c)) {
                            puzzle[i][j] = c;
                            if (solveSudoku(puzzle))
                                return true;
                            else
                                puzzle[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isValid(int row, int col, int[][] puzzle, char c) {

        for (int i = 0; i < puzzle.length; i++) {
            // System.out.println("Horizontal Index " + row + "," + i);
            if (puzzle[row][i] == c) {
                return false;
            }
            //System.out.println("Vertical Index " + i + "," + col);
            if (puzzle[i][col] == c) {
                return false;
            }
            // System.out.println("Box Index " + (3 * (row / 3) + i / 3) + "," + (3 * (col / 3) + i % 3));
            if (puzzle[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false;
            }
        }

        return true;
    }

    public static void displaySudokuSolution(int[][] s) {
        int n = s.length;
        System.out.println("Solution Found and It is ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(s[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        int[][] s = {{7, 0, 0, 0, 0, 0, 2, 0, 0},
                {4, 0, 2, 0, 0, 0, 0, 0, 3},
                {0, 0, 0, 2, 0, 1, 0, 0, 0},
                {3, 0, 0, 1, 8, 0, 0, 9, 7},
                {0, 0, 9, 0, 7, 0, 6, 0, 0},
                {6, 5, 0, 0, 3, 2, 0, 0, 1},
                {0, 0, 0, 4, 0, 9, 0, 0, 0},
                {5, 0, 0, 0, 0, 0, 1, 0, 6},
                {0, 0, 6, 0, 0, 0, 0, 0, 8}
        };

        if (solveSudoku(s)) {
            displaySudokuSolution(s);
            //System.out.println(Arrays.deepToString(s));
        } else {
            System.out.println("No Solution Found");
        }
    }
}


