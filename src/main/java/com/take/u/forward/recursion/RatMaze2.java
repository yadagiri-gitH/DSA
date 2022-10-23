package com.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class RatMaze2 {

    public static void ratMazeRoute(int i, int j, int[][] maze, int n, int[][] register, String route, List<String> routes, int[] lIndex, int[] rIndex) {

        if (i == n - 1 && j == n - 1) {
            routes.add(route);
            return;
        }

        String direction = "DLRU";

        for (int k = 0; k < 4; k++) {//k<lIndex.length//4 directions

            int nextI = lIndex[k] + i;
            int nextJ = rIndex[k] + j;

            if ((nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < n) && register[nextI][nextJ] != 1 && maze[nextI][nextJ] != 0) {
                register[nextI][nextJ] = 1;
                ratMazeRoute(nextI, nextJ, maze, n, register, route + direction.charAt(k), routes, lIndex, rIndex);
                register[nextI][nextJ] = 0;
            }

        }
    }

    public static void main(String[] args) {
       /* int n = 4;
        int maze[][] = {{1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}};
         int register[][] = {{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};*/
        int n = 2;
        int maze[][] = {{1, 1}, {1, 1}};
        // should put register[0][0]=1 so that it doesn't travel twice through (0,0). In case of maze=[[1,1],[1,1]] we shouldn't accept DURD, RLDR
        int register[][] = {{1, 0}, {0, 0}};
        if (maze[0][0] != 1) {
            System.out.println("Rat can't move from (0,0) position and not possible to reach (" + (n - 1) + "," + (n - 1) + ") position");
            return;
        }

        List<String> mazeRoutes = new ArrayList<>();

        int[] lIndex = {1, 0, 0, -1};
        int[] rIndex = {0, -1, 1, 0};


        ratMazeRoute(0, 0, maze, n, register, "", mazeRoutes, lIndex, rIndex);

        System.out.println(mazeRoutes);
    }
}
