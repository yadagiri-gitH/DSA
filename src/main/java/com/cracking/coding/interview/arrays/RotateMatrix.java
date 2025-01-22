package com.cracking.coding.interview.arrays;

public class RotateMatrix {
    public static void main(String[] args) {
        int[][] m = new int[][]{{61, 62, 63, 64, 65, 66}, {77, 78, 79, 80, 81, 82}, {13, 14, 15, 16, 17, 18}, {19, 20, 21, 22, 23, 24}, {25, 26, 27, 28, 29, 30}, {31, 32, 33, 34, 35, 36}};
        rotateWithBF(m);
        rotate(m);
        rotateMatrix(m);
    }

    public static void rotateWithBF(int[][] m) {//With extra space N2

        if (m.length == 0 || m.length != m[0].length) {
            System.out.println("Given Matrix is not NXN (SQUARE), So Cannot rotate");
            return;
        }

        System.out.println("Printing Given Input Matrix");
        print(m);

        int n = m.length;
        int[][] r = new int[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                r[i][j] = m[n - 1 - j][i];
            }
        }

        System.out.println("Printing Matrix in 90 degrees Using BF");
        print(r);

    }

    public static void rotate(int[][] m) {

        if (m.length == 0 || m.length != m[0].length) {
            System.out.println("Given Matrix is not NXN (SQUARE), So Cannot rotate");
            return;
        }

        System.out.println("Printing Given Input Matrix");
        print(m);

        int length = m.length;

        for (int layer = 0; layer < length / 2; layer++) {//traverse from outer layer to inner layer
            int first = layer;
            int last = length - 1 - layer;//0 to 5
            for (int i = first; i < last; i++) {
                int offset = i - first;//this is important factor to move this program ,it ranging from 0 to 4,0 to 3,0 to 2,will use this instead of i in finding last index based on layer
                int left = m[i][first];
                //bottom>>left
                m[i][first] = m[last][i];
                //right>>bottom
                m[last][i] = m[last - offset][last];
                //top>>right
                m[last - offset][last] = m[first][last - offset];
                //left>>top
                m[first][last - offset] = left;
            }
        }

        System.out.println("Printing Matrix in 90 degrees without extra space");
        print(m);

    }

    public static void rotateMatrix(int[][] m) {//going with top as per gayle laakmann

        if (m.length == 0 || m.length != m[0].length) {
            System.out.println("Given Matrix is not NXN (SQUARE), So Cannot rotate");
            return;
        }

        int n = m.length;

        System.out.println("Printing Given Input Matrix");
        print(m);

        for (int layer = 0; layer < n / 2; layer++) {//traverse from outer layer to inner layer
            int first = layer;
            int last = n - 1 - layer;//0 to 5
            for (int i = first; i < last; i++) {
                int offset = i - first;//this is important factor to move this program ,it ranging from 0 to 4,0 to 3,0 to 2,will use this instead of i in finding last index based on layer
                int top = m[first][i];
                //left>>top
                m[first][i] = m[last - offset][first];
                //bottom>>left
                m[last - offset][first] = m[last][last - offset];
                //right>>bottom
                m[last][last - offset] = m[i][last];
                //top>>right
                m[i][last] = top;
            }
        }

        System.out.println("Printing Matrix in 90 degrees without extra space");
        print(m);

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
