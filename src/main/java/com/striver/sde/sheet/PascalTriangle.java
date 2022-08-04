package com.striver.sde.sheet;

import java.util.ArrayList;

public class PascalTriangle {
    public static ArrayList<ArrayList<Long>> printPascal(int n) {
        ArrayList<ArrayList<Long>> pascalList = new ArrayList();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ArrayList<Long> first = new ArrayList();
                first.add(1L);
                pascalList.add(first);
            } else if (i == 1) {
                ArrayList<Long> second = new ArrayList();
                second.add(1L);
                second.add(1L);
                pascalList.add(second);
            } else {
                ArrayList<Long> others = getPascal(pascalList, i);
                pascalList.add(others);
            }
        }
        return pascalList;
    }

    public static ArrayList<Long> getPascal(ArrayList<ArrayList<Long>> pascalList, int n) {
        ArrayList<Long> lastPascal = pascalList.get(n - 1);
        ArrayList<Long> currentPascal = new ArrayList();
        currentPascal.add(lastPascal.get(0));

        for (int i = 1; i < lastPascal.size(); i++) {
            currentPascal.add(lastPascal.get(i - 1) + lastPascal.get(i));
        }

        currentPascal.add(lastPascal.get(lastPascal.size() - 1));

        return currentPascal;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Long>> pascalList = printPascal(6);
        for (ArrayList pascal : pascalList){
             System.out.println(pascal.toString());
        }
    }
}
