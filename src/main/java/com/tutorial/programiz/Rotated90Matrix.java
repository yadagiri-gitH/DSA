package com.tutorial.programiz;

import java.util.Scanner;

public class Rotated90Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of rows and columns for the matrix
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();

        // Create the matrix
        int[][] matrix = new int[rows][columns];

        // Get the matrix elements from the user
        System.out.println("Enter the matrix elements:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Rotate the matrix 90 degrees clockwise
        int[][] rotatedMatrix = rotateMatrixClockwise(matrix);

        // Print the rotated matrix
        System.out.println("Rotated matrix:");
        for (int i = 0; i < rotatedMatrix.length; i++) {
            for (int j = 0; j < rotatedMatrix[0].length; j++) {
                System.out.print(rotatedMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to rotate the matrix 90 degrees clockwise
    public static int[][] rotateMatrixClockwise(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] rotatedMatrix = new int[columns][rows];

        int cs = 0;
        for (int i = 0; i < columns; i++) {
            int rs = rows - 1;
            for (int j = 0; j < rows; j++) {
                rotatedMatrix[i][j] = matrix[rs - j][cs + i];
            }
        }

        return rotatedMatrix;
    }
}
