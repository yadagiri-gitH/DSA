package com.cracking.coding.interview.arrays;

public class StringRotation {
    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";

        System.out.print(s2 + " Rotation of " + s1 + " ? : " + isRotation(s1, s2));

    }

    public static boolean isRotation(String s1, String s2) {
        if (s1.length() == s2.length() && s1.length() > 0) {
            String s1s1 = s1 + s1;
            return isSubstring(s1s1, s2);
        }
        return false;
    }

    private static boolean isSubstring(String s1s1, String s2) {
        int length = s2.length();
        int counter = 0;

        for (int i = 0; i < s1s1.length(); i++) {
            if (s2.charAt(counter) == s1s1.charAt(i)) {
                counter++;
                if (counter >= length)
                    break;
            } else {
                counter = 0;
            }
        }

        return counter >= length;
    }
}
