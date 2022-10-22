package com.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class RatMaze {
    public static void main(String[] args) {
        int n = 4;
        int m[][] = {{1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}};
        int vis[][] = {{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        List<String> mazeRoutes = new ArrayList<>();
        if (m[0][0] != 1) {
            System.out.println("Rat cannot start from 0 and 0 and not possible to go to " + (n - 1) + " and " + (n - 1) + " position");
            return;
        }
        mazeRoutes(m, n, vis, 0, 0, "", mazeRoutes);
        System.out.println(mazeRoutes);
    }

    public static void mazeRoutes(int[][] m, int n, int[][] v, int row, int col, String s, List<String> mazeRoutes) {

        if (row == n - 1 && col == n - 1) {
            mazeRoutes.add(s);
            return;
        }
        //D
        if (row + 1 < n && m[row + 1][col] != 0 && v[row][col] != 1) {
            v[row][col] = 1;
            mazeRoutes(m, n, v, row + 1, col, s + "D", mazeRoutes);
            v[row][col] = 0;
        }
        //L
        if (col - 1 >= 0 && m[row][col - 1] != 0 && v[row][col] != 1) {
            v[row][col] = 1;
            mazeRoutes(m, n, v, row, col - 1, s + "L", mazeRoutes);
            v[row][col] = 0;
        }
        //R
        if (col + 1 < n && m[row][col + 1] != 0 && v[row][col] != 1) {
            v[row][col] = 1;
            mazeRoutes(m, n, v, row, col + 1, s + "R", mazeRoutes);
            v[row][col] = 0;
        }
        //U
        if (row - 1 >= 0 && m[row - 1][col] != 0 && v[row][col] != 1) {
            v[row][col] = 1;
            mazeRoutes(m, n, v, row - 1, col, s + "U", mazeRoutes);
            v[row][col] = 0;
        }
    }
}
