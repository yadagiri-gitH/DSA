package com.cracking.coding.interview.arrays;

public class OneAway {

    public static boolean isOneEditAway(String str1, String str2) {
        if (str1.length() == str2.length()) {
            return isOneReplaceAway(str1, str2);
        }
        return isOneInsertOrRemoveAway(str1, str2);
    }

    public static boolean isOneReplaceAway(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        int i = 0;

        boolean foundDifferences = false;

        while (i < str1.length()) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (foundDifferences) {
                    return false;
                }
                foundDifferences = true;
            }
            i++;
        }

        return true;
    }

    public static boolean isOneInsertOrRemoveAway(String str1, String str2) {
        if (!isJustOneCharAway(str1, str2))
            return false;

        int i = 0;
        int j = 0;
        int count = 0;

        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                count++;
                i++;
                j++;
            } else if (str1.length() > str2.length()) {
                i++;
            } else {
                j++;
            }
        }

        return str1.length() < str2.length() ? str2.length() - count == 1 : str1.length() - count == 1;
    }

    private static boolean isJustOneCharAway(String str1, String str2) {
        if (str1.length() == str2.length()) {
            return false;
        }

        int diff;

        if (str1.length() > str2.length()) {
            diff = str1.length() - str2.length();
        } else {
            diff = str2.length() - str1.length();
        }

        if (diff > 1)
            return false;

        return true;
    }

    public static boolean isOneCharEditAway(String str1, String str2) {//refactored code of isOneAway
        if (Math.abs(str1.length() - str2.length()) > 1) {
            return false;
        }

        String s1 = str1.length() < str2.length() ? str1 : str2;
        String s2 = str1.length() < str2.length() ? str2 : str1;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifferences = false;

        while (index1 < s1.length() && index2 < s2.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (foundDifferences) {
                    return false;
                }
                foundDifferences = true;
                if (s1.length() == s2.length()) {
                    index1++;
                }
                // index2++;
            } else {
                index1++;
                // index2++;
            }
            index2++;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isOneEditAway("pale", "page"));
        System.out.println(isOneEditAway("pale", "ple"));
        System.out.println(isOneEditAway("pales", "pale"));
        System.out.println(isOneEditAway("pale", "bake"));
        System.out.println(isOneCharEditAway("pale", "page"));
        System.out.println(isOneCharEditAway("pale", "ple"));
        System.out.println(isOneCharEditAway("pales", "pale"));
        System.out.println(isOneCharEditAway("pale", "bake"));
    }
}
