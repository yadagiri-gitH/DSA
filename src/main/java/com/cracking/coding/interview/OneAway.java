package com.cracking.coding.interview;

public class OneAway {

    public static boolean isOneAway(String str1, String str2) {
        if (str1.length() == str2.length()) {
            return isOneEditAway(str1, str2);
        }
        return isOneInsertOrRemoveAway(str1, str2);
    }

    public static boolean isOneEditAway(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        int i = 0;

        boolean isDiffFound = false;

        while (i < str1.length()) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (isDiffFound) {
                    return false;
                }
                isDiffFound = true;
            }
            i++;
        }

        return true;
    }

    public static boolean isOneInsertOrRemoveAway(String str1, String str2) {
        if (!isOneAwayString(str1, str2))
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

    private static boolean isOneAwayString(String str1, String str2) {
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

    public static void main(String[] args) {
        System.out.println(isOneAway("pale", "page"));
        System.out.println(isOneAway("pale", "ple"));
        System.out.println(isOneAway("pales", "pale"));
        System.out.println(isOneAway("pale", "bake"));
    }
}
