package com.cracking.coding.interview;

public class ZeroMatrix {
    public static void main(String[] args) {
        int[][] m = new int[][]{{61, 62, 63, 64, 65, 66}, {77, 78, 79, 80, 81, 82}, {0, 14, 15, 16, 17, 18}, {19, 0, 21, 22, 23, 24}, {25, 26, 27, 28, 29, 30}, {31, 32, 33, 0, 35, 36}};
        setZeros(m);
    }

    public static void setZeros(int[][] m) {

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
