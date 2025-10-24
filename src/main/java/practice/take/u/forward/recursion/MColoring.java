package practice.take.u.forward.recursion;

import java.util.Arrays;
import java.util.List;

public class MColoring {

    public static boolean graphColoring(List<Integer>[] graph, int noOfColors) {
        int nodeSize = graph.length;
        int[] colors = new int[nodeSize];

        return isPossibleMColoring(1, graph, nodeSize, noOfColors, colors);
    }

    public static boolean isPossibleMColoring(int nodeNum, List<Integer>[] graph, int n, int noOfColors, int[] colors) {
        if (nodeNum == n) {
            return true;
        }

        for (int i = 0; i < noOfColors; i++) {
            if (isValidColorFilling(nodeNum, graph, i, colors)) {
                colors[i] = i;
                if (isPossibleMColoring(nodeNum + 1, graph, n, noOfColors, colors)) {
                    return true;
                } else {
                    colors[i] = 0;
                }
            }
        }

        return false;
    }

    public static boolean isValidColorFilling(int nodeNum, List<Integer>[] graph, int color, int[] colors) {
        for (int num : graph[nodeNum]) {
            if (colors[num] == color) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer>[] graphEdges = new List[]{Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4), Arrays.asList(4, 1), Arrays.asList(1, 3)};
        int noOfColors = 3;//2
        if (graphColoring(graphEdges, noOfColors)) {
            System.out.println("Given Graph is possible with " + noOfColors + " coloring");
        } else {
            System.out.println("Given Graph is not possible with " + noOfColors + " coloring");
        }
    }
}
