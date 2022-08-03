package com.cracking.coding.interview;

public class ZeroMatrix {
    public static void main(String[] args) {
        int[][] m = new int[][]{{61, 62, 63, 64, 65, 66}, {77, 78, 79, 80, 81, 82}, {13, 14, 15, 16, 17, 18}, {19, 20, 21, 22, 23, 24}, {25, 26, 27, 28, 29, 30}, {31, 32, 33, 0, 35, 36}};
        setZeros(m);
        setZero(m);
    }

    public static void setZeros(int[][] m) {//it occupies O(n) space

        boolean[] row = new boolean[m.length];
        boolean[] col = new boolean[m[0].length];

        System.out.println("Printing Given Input Matrix");
        print(m);

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                nullifyRows(m, i);
            }
        }

        for (int j = 0; j < col.length; j++) {
            if (col[j]) {
                nullifyColumns(m, j);
            }
        }

        System.out.println("Printing ZERO Matrix");
        print(m);

    }

    public static void setZero(int[][] m)//it occupies O(1) space
    {
        System.out.println("Printing Given Input Matrix");
        print(m);

        boolean rowHasZero = false;
        boolean colHasZero = false;

        for (int i = 0; i < m[0].length; i++) {
            if (m[0][i] == 0)
                rowHasZero = true;
        }

        for (int j = 0; j < m.length; j++) {
            if (m[j][0] == 0)
                colHasZero = true;
        }

        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[0].length; j++) {
                if (m[i][j] == 0) {
                    m[i][0] = 0;
                    m[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m.length; i++) {
            if (m[i][0] == 0) {
                nullifyRows(m, i);
            }
        }

        for (int j = 1; j < m[0].length; j++) {
            if (m[0][j] == 0) {
                nullifyColumns(m, j);
            }
        }

        if (rowHasZero) nullifyRows(m, 0);
        if (colHasZero) nullifyColumns(m, 0);

        System.out.println("Printing ZERO Matrix");
        print(m);

    }

    public static void nullifyRows(int[][] matrix, int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = 0;
        }
    }

    public static void nullifyColumns(int[][] matrix, int column) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][column] = 0;
        }
    }

    public static void print(int[][] matrix) {
        int row_length = matrix.length;
        int col_length = matrix[0].length;

        for (int i = 0; i < row_length; i++) {
            System.out.print("|");
            for (int j = 0; j < col_length; j++) {
                System.out.print(matrix[i][j]);
                System.out.print("   ");
            }
            System.out.print("|");
            System.out.println("\n");
        }
    }
}
